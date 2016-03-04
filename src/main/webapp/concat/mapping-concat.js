(function() {
   "use strict";

   angular.module("Grapp")
      .controller("GrappMap", GrappMap)
      .directive("grappMap", GrappMapDirective);

   function GrappMapDirective() {
      return {
         restrict: "E",
         controller: "GrappMap",
         controllerAs: "grappMapVM",
         templateUrl: "app/mapping/GrappMap.html",
         scope: {},
         bindToController: {
            control: "=",
            grappStoreLocation: "=?",
            grappStoreLayout: "="
         }
      };
   }

   GrappMap.$inject = [];
   function GrappMap() {
      var grappMapVM = this;
      grappMapVM.center = null;
      grappMapVM.zoom = 3;
      grappMapVM.options = null;
      grappMapVM.mapEvents = null;
      grappMapVM.drawingManagerEvents = null;
      grappMapVM.polygonEvents = null;
      grappMapVM.mapControl = {};
      grappMapVM.drawingManagerControl = {};
      grappMapVM.outlineControl = {};
      grappMapVM.featureControl = {};
      grappMapVM.nodeControl = {};
      grappMapVM.storeOutlines = null;
      grappMapVM.storeFeatures = null;
      grappMapVM.storeNodes = null;

      var control = this.control;
      var grappStoreLocation = this.grappStoreLocation;
      var grappStoreLayout = this.grappStoreLayout;

      initialize();

      ////////////////////

      function initialize() {
         grappMapVM.center = {
            latitude: grappStoreLocation ? grappStoreLocation.latitude : 0,
            longitude: grappStoreLocation ? grappStoreLocation.longitude : 0
         };

         grappMapVM.zoom = grappStoreLocation ? 18 : 3;

         grappMapVM.options = {
            draggableCursor: "pointer",
            draggingCursor: "pointer",
            disableDefaultUI: true,
            zoomControl: true,
            drawingControl: false,
            polygonOptions: {
               strokeWeight: 1,
               fillColor: "#194d4d",
               fillOpacity: 1,
               zIndex: 3,
               clickable: true,
               editable: false,
               draggable: false
            },
            markerOptions: {
               zIndex: 4,
               draggable: true
            }
         };

         grappMapVM.mapEvents = {
            click: function(map, eventName, args) {
               control.mapClicked(map, args[0]);
            }
         };

         grappMapVM.drawingManagerEvents = {
            polygoncomplete: function(drawingManager, eventName, model, args) { control.polygonComplete(args[0]); }
         };

         grappMapVM.polygonEvents = {
            click: function(polygon, eventName, model, args) { control.polygonClicked(model.id, polygon, args[0]); },
            rightclick: function(polygon, eventName, model, args) { control.polygonRightClicked(model.id, polygon, args[0]); },
            dragend: function(polygon, eventName, model, args) { control.polygonDragEnd(model.id, polygon, args[0]); }
         };

         grappMapVM.markerEvents = {
            click: function(marker, eventName, model, args) { control.markerClicked(model.id, marker, args[0]); },
            rightclick: function(marker, eventName, model, args) { control.markerRightClicked(model.id, marker, args[0]); },
            dragend: function(marker, eventName, model, args) { control.markerDragEnd(model.id, marker, args[0]); }
         };

         control.setMapControl(grappMapVM.mapControl);
         control.setDrawingManagerControl(grappMapVM.drawingManagerControl);
         control.setOutlineControl(grappMapVM.outlineControl);
         control.setFeatureControl(grappMapVM.featureControl);
         control.setNodeControl(grappMapVM.nodeControl);

         grappMapVM.storeOutlines = [
            convertGrappPolygonToGMapPolygon(grappStoreLayout.outerOutline, {color: "#194d4d", opacity: 1}, 0, true),
            convertGrappPolygonToGMapPolygon(grappStoreLayout.innerOutline, {color: "#b3e5e6", opacity: 1}, 1, false)
         ];

         grappMapVM.storeFeatures = grappStoreLayout.features.map(function(grappPolygon) {
            return convertGrappPolygonToGMapPolygon(grappPolygon, {color: "#194d4d", opacity: 1}, 2);
         });

         grappMapVM.storeNodes = grappStoreLayout.nodes.map(function(node) {
            return convertGrappNodeToGMapMarker(node);
         });
      }

      function convertGrappPolygonToGMapPolygon(grappPolygon, fill, zIndex, fit) {
         return {
            id: grappPolygon.id,
            path: convertGrappPolygonVerticesToGMapPolygonPath(grappPolygon.vertices),
            fill: fill,
            zIndex: zIndex,
            fit: fit,
            clickable: grappPolygon.isFeature,
            editable: false,
            draggable: false
         };
      }

      function convertGrappPolygonVerticesToGMapPolygonPath(vertices) {
         return vertices.map(function(vertex) {
            return {
               latitude: vertex.lat,
               longitude: vertex.lng
            };
         });
      }

      function convertGrappNodeToGMapMarker(node) {
         return {
            id: node.id,
            position: {
               latitude: node.location.lat,
               longitude: node.location.lng
            },
            options: grappMapVM.options.markerOptions
         };
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .value("GrappMapControl", GrappMapControl);

   function GrappMapControl() {
      var self = this;
      self.getOutlineById = getOutlineById;
      self.setOutlinePolygon = setOutlinePolygon;
      self.addFeature = addFeature;
      self.removeFeatureById = removeFeatureById;
      self.applyToFeatures = applyToFeatures;
      self.addNode = addNode;
      self.removeNodeById = removeNodeById;
      self.mapClicked = mapClicked;
      self.polygonComplete = polygonComplete;
      self.polygonClicked = polygonClicked;
      self.polygonRightClicked = polygonRightClicked;
      self.polygonDragEnd = polygonDragEnd;
      self.markerClicked = markerClicked;
      self.markerRightClicked = markerRightClicked;
      self.markerDragEnd = markerDragEnd;
      self.setMapControl = setMapControl;
      self.setDrawingManagerControl = setDrawingManagerControl;
      self.setOutlineControl = setOutlineControl;
      self.setFeatureControl = setFeatureControl;
      self.setNodeControl = setNodeControl;
      self.setEventHandler = setEventHandler;
      self.setDrawingMode = setDrawingMode;

      var mapControl = null;
      var drawingManagerControl = null;
      var outlineControl = null;
      var featureControl = null;
      var nodeControl = null;
      var eventHandler = null;

      ////////////////////

      function getOutlineById(outlineId) {
         return getOutlinePluralById(outlineId).gObject;
      }

      function setOutlinePolygon(outlineId, gMapPolygon) {
         var gMapPolygonPlural = getOutlinePluralById(outlineId);
         gMapPolygonPlural.gObject = gMapPolygon;
         gMapPolygon.setOptions({
            fillColor: gMapPolygonPlural.model.fill.color,
            zIndex: gMapPolygonPlural.model.zIndex,
            clickable: gMapPolygonPlural.model.clickable
         });
         gMapPolygon.setEditable(true);
         postProcessAddedGMapPolygon(outlineId, gMapPolygon);
      }

      function addFeature(featureId, gMapPolygon) {
         featureControl.getPlurals().put(featureId, {gObject: gMapPolygon});
         postProcessAddedGMapPolygon(featureId, gMapPolygon);
      }

      function removeFeatureById(featureId) {
         var featurePlurals = featureControl.getPlurals();
         var gMapPolygonPlural = featurePlurals.get(featureId);
         var gMapPolygon = gMapPolygonPlural.gObject;
         featurePlurals.remove(featureId);
         google.maps.event.clearInstanceListeners(gMapPolygon);
         gMapPolygon.setOptions({clickable: false});
         gMapPolygon.setMap(null);
      }

      function applyToFeatures(operator) {
         featureControl.getPlurals().values().forEach(function(plural) {
            operator(plural.gObject);
         });
      }

      function addNode(nodeId, gMapMarker) {
         nodeControl.getPlurals().put(nodeId, {gObject: gMapMarker});
         postProcessAddedGMapMarker(nodeId, gMapMarker);
      }

      function removeNodeById(nodeId) {
         var nodePlurals = nodeControl.getPlurals();
         var gMapMarkerPlural = nodePlurals.get(nodeId);
         var gMapMarker = gMapMarkerPlural.gObject;
         nodePlurals.remove(nodeId);
         google.maps.event.clearInstanceListeners(gMapMarker);
         gMapMarker.setMap(null);
      }

      function mapClicked(map, mouseEvent) {
         if (eventHandler && eventHandler.mapClicked) {
            eventHandler.mapClicked(map, mouseEvent);
         }
      }

      function polygonComplete(gMapPolygon) {
         // This is done to represent the fact that this
         // GMapPolygon is just a representation of something
         // to process. it is up to the handler to handle
         // its post-processing. For instance, if it just
         // needs to add this representation to the Map, it
         // should set the Map itself; however there won't be
         // any Events attached to it and no associated ID
         // for those Events. If this GMapPolygon is a
         // Feature, then it needs to be added like other
         // Features through GrappMapControl.addFeature() so
         // that Event Handlers are attached with appropriate
         // ID and it is added to the Plurals
         gMapPolygon.setMap(null);
         // -------------------------------------------------

         if (eventHandler && eventHandler.polygonComplete) {
            eventHandler.polygonComplete(gMapPolygon);
         }
      }

      function polygonClicked(modelId, gMapPolygon, polyMouseEvent) {
         if (eventHandler && eventHandler.polygonClicked) {
            eventHandler.polygonClicked(modelId, gMapPolygon, polyMouseEvent);
         }
      }

      function polygonRightClicked(modelId, gMapPolygon, polyMouseEvent) {
         if (gMapPolygon.getMap() && eventHandler && eventHandler.polygonRightClicked) {
            eventHandler.polygonRightClicked(modelId, gMapPolygon, polyMouseEvent);
         }
      }

      function polygonDragEnd(modelId, gMapPolygon, mouseEvent) {
         if (gMapPolygon.getMap() && eventHandler && eventHandler.polygonDragEnd) {
            eventHandler.polygonDragEnd(modelId, gMapPolygon, mouseEvent);
         }
      }

      function markerClicked(modelId, gMapMarker, mouseEvent) {
         if (eventHandler && eventHandler.markerClicked) {
            eventHandler.markerClicked(modelId, gMapMarker, mouseEvent);
         }
      }

      function markerRightClicked(modelId, gMapMarker, mouseEvent) {
         if (gMapMarker.getMap() && eventHandler && eventHandler.markerRightClicked) {
            eventHandler.markerRightClicked(modelId, gMapMarker, mouseEvent);
         }
      }

      function markerDragEnd(modelId, gMapMarker, mouseEvent) {
         if (gMapMarker.getMap() && eventHandler && eventHandler.markerDragEnd) {
            eventHandler.markerDragEnd(modelId, gMapMarker, mouseEvent);
         }
      }

      function setMapControl(mc) {
         mapControl = mc;
      }

      function setDrawingManagerControl(dmc) {
         drawingManagerControl = dmc;
      }

      function setOutlineControl(oc) {
         outlineControl = oc;
      }

      function setFeatureControl(fc) {
         featureControl = fc;
      }

      function setNodeControl(nc) {
         nodeControl = nc;
      }

      function setEventHandler(eh) {
         if (eventHandler && eventHandler.finish) {
            eventHandler.finish();
         }

         eventHandler = eh;
         if (eventHandler && eventHandler.start) {
            eventHandler.start();
         }
      }

      function setDrawingMode(drawingMode) {
         var drawingManager = drawingManagerControl.getDrawingManager();
         if (drawingMode === "POLYGON") {
            drawingManager.setDrawingMode(google.maps.drawing.OverlayType.POLYGON);
         }
         else {
            drawingManager.setDrawingMode(null);
         }
      }

      function getOutlinePluralById(outlineId) {
         return outlineControl.getPlurals().get(outlineId);
      }

      function postProcessAddedGMapPolygon(modelId, gMapPolygon) {
         google.maps.event.clearInstanceListeners(gMapPolygon);
         google.maps.event.addListener(gMapPolygon, "click", function(polyMouseEvent) { self.polygonClicked(modelId, gMapPolygon, polyMouseEvent); });
         google.maps.event.addListener(gMapPolygon, "rightclick", function(polyMouseEvent) { self.polygonRightClicked(modelId, gMapPolygon, polyMouseEvent); });
         google.maps.event.addListener(gMapPolygon, "dragend", function(mouseEvent) { self.polygonDragEnd(modelId, gMapPolygon, mouseEvent); });
         gMapPolygon.setMap(mapControl.getGMap());
      }

      function postProcessAddedGMapMarker(modelId, gMapMarker) {
         google.maps.event.clearInstanceListeners(gMapMarker);
         google.maps.event.addListener(gMapMarker, "click", function(mouseEvent) { self.markerClicked(modelId, gMapMarker, mouseEvent); });
         google.maps.event.addListener(gMapMarker, "rightclick", function(mouseEvent) { self.markerRightClicked(modelId, gMapMarker, mouseEvent); });
         google.maps.event.addListener(gMapMarker, "dragend", function(mouseEvent) { self.markerDragEnd(modelId, gMapMarker, mouseEvent); });
         gMapMarker.setMap(mapControl.getGMap());
      }
   }
})();