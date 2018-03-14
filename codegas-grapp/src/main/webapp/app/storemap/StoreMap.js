(function() {
   "use strict";

   angular.module("App")
      .controller("StoreMap", StoreMap)
      .directive("storeMap", StoreMapDirective);

   function StoreMapDirective() {
      return {
         restrict: "E",
         controller: "StoreMap",
         controllerAs: "storeMapVM",
         templateUrl: "app/storemap/StoreMap.html",
         scope: {},
         bindToController: {
            mapControl: "=",
            location: "=?",
            storeLayout: "=",
            editable: "=?"
         }
      };
   }

   StoreMap.$inject = ["uiGmapUtil"];
   function StoreMap(uiGmapUtil) {
      var storeMapVM = this;
      storeMapVM.mapSettings = null;
      storeMapVM.events = null;
      storeMapVM.controls = null;
      storeMapVM.storeOutlines = null;
      storeMapVM.storeFeatures = null;
      storeMapVM.storeNodes = null;
      storeMapVM.window = null;
      storeMapVM.showWindow = showWindow;

      var mapControl = null;
      var location = null;
      var storeLayout = null;
      var editable = false;

      this.$onInit = function() { initialize(this); };

      ////////////////////

      function initialize(binder) {
         mapControl = binder.mapControl;
         location = binder.location || { lat: 0, lng: 0 };
         storeLayout = binder.storeLayout;
         editable = binder.editable ? binder.editable : false;

         initializeMapSettings(location);
         initializeControls();
         initializeLayoutObjects(storeLayout);
         initializeNodeWindow();
         initializeEvents();
      }

      function initializeMapSettings(location) {
         storeMapVM.mapSettings = {
            center: _.convertLocationToPosition(location),
            zoom: 18,
            enableWindow: !editable,
            options: {
               draggableCursor: "pointer",
               draggingCursor: "pointer",
               disableDefaultUI: true,
               zoomControl: true,
               drawingControl: false,
               polygonOptions: {
                  strokeWeight: 1,
                  fillColor: "#00332e",
                  fillOpacity: 1,
                  zIndex: 3,
                  clickable: true,
                  editable: false,
                  draggable: editable
               },
               markerOptions: {
                  zIndex: 4,
                  draggable: editable
               }
            }
         };
      }

      function initializeControls() {
         storeMapVM.controls = {
            map: {},
            drawingManager: {},
            outline: {},
            feature: {},
            node: {}
         };
         mapControl.setControls(storeMapVM.controls);
      }

      function initializeLayoutObjects(layout) {
         storeMapVM.storeOutlines = [
            createGMapPolygonFromModel(layout.outerOutline, {color: "#00332e", opacity: 1}, 0, false, true),
            createGMapPolygonFromModel(layout.innerOutline, {color: "#00ffe6", opacity: 1}, 1, false, false)
         ];

         storeMapVM.storeFeatures = layout.getFeatures().map(function(polygon) {
            return createGMapPolygonFromModel(polygon, {color: "#00332e", opacity: 1}, 2, true, false);
         });

         storeMapVM.storeNodes = layout.getNodes().map(function(node) {
            return createGMapMarkerFromNodeModel(node);
         });
      }

      function createGMapPolygonFromModel(polygonModel, fill, zIndex, isFeature, fit) {
         return {
            id: polygonModel.id,
            path: (polygonModel.polygon || polygonModel).vertices.map(_.convertLocationToPosition),
            fill: fill,
            zIndex: zIndex,
            fit: fit,
            clickable: isFeature,
            editable: false,
            draggable: editable && isFeature
         };
      }

      function createGMapMarkerFromNodeModel(nodeModel) {
         return {
            id: nodeModel.id,
            position: _.convertLocationToPosition(nodeModel.location),
            icon: nodeModel.type.iconUrl,
            node: nodeModel,
            options: _.mergeLeft({title: nodeModel.type.isSingleton ? nodeModel.type.displayString : nodeModel.name}, storeMapVM.mapSettings.options.markerOptions)
         };
      }

      function initializeNodeWindow() {
         storeMapVM.window = {
            show: false,
            coords: null,
            templateUrl: "app/storemap/NodeWindow.html",
            templateParameter: null,
            options: {
               disableAutoPan: true,
               pixelOffset: new google.maps.Size(0, -20)
            }
         };
      }

      function initializeEvents() {
         storeMapVM.events = {
            map: {
               click: function(map, eventName, args) { mapControl.handleGObjectMouseEvent("mapClicked", "map", map, args[0]); },
               rightclick: function(map, eventName, args) { mapControl.handleGObjectMouseEvent("mapRightClicked", "map", map, args[0]); }
            },
            drawingManager: {
               polygoncomplete: function(drawingManager, eventName, model, args) { mapControl.polygonComplete(args[0]); }
            },
            polygon: {
               click: function(polygon, eventName, model, args) { mapControl.handleGObjectMouseEvent("polygonClicked", model.id, polygon, args[0]); },
               rightclick: function(polygon, eventName, model, args) { mapControl.handleGObjectMouseEvent("polygonRightClicked", model.id, polygon, args[0]); },
               dragend: function(polygon, eventName, model, args) { mapControl.handleGObjectMouseEvent("polygonDragEnd", model.id, polygon, args[0]); }
            },
            marker: {
               click: function(marker, eventName, model, args) {
                  updateWindowForNode(model);
                  mapControl.handleGObjectMouseEvent("markerClicked", model.id, marker, args[0]);
               },
               rightclick: function(marker, eventName, model, args) { mapControl.handleGObjectMouseEvent("markerRightClicked", model.id, marker, args[0]); },
               dragend: function(marker, eventName, model, args) { mapControl.handleGObjectMouseEvent("markerDragEnd", model.id, marker, args[0]); }
            }
         };
      }

      function updateWindowForNode(model) {
         storeMapVM.window.coords = model.position;
         storeMapVM.window.templateParameter = model.node;
         showWindow(true);
      }

      function showWindow(show) {
         uiGmapUtil.onNextAngularTurn().then(function() {
            storeMapVM.window.show = show;
         });
      }
   }
})();