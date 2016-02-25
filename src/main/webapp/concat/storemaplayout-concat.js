(function() {
   "use strict";

   angular.module("Grapp")
      .value("CopyMoveDeleteFeatureEventHandler", CopyMoveDeleteFeatureEventHandler);

   function CopyMoveDeleteFeatureEventHandler(mapControl, grappStoreLayout) {
      var self = this;
      self.start = start;
      self.finish = finish;
      self.mapClicked = mapClicked;
      self.polygonClicked = polygonClicked;
      self.polygonRightClicked = polygonRightClicked;
      self.polygonDragEnd = polygonDragEnd;

      var gMapPolygonCopyModel;

      ////////////////////

      function start() {
         enableDragging(true);
      }

      function finish() {
         if (gMapPolygonCopyModel) {
            gMapPolygonCopyModel.deselect();
         }
         enableDragging(false);
      }

      function mapClicked(map, mouseEvent) {
         if (gMapPolygonCopyModel) {
            var gMapPolygon = gMapPolygonCopyModel.copyToLatLng(mouseEvent.latLng);
            grappStoreLayout.addFeature(gMapPolygon.getPath().getArray())
               .then(function(model) {
                  gMapPolygon.setDraggable(true);
                  mapControl.addFeature(model.id, gMapPolygon);
               });
         }
      }

      function polygonClicked(modelId, gMapPolygon, polyMouseEvent) {
         if (gMapPolygonCopyModel) {
            gMapPolygonCopyModel.deselect();
         }
         gMapPolygonCopyModel = new GMapPolygonCopyModel(gMapPolygon, polyMouseEvent.latLng);
         gMapPolygonCopyModel.select();
      }

      function polygonRightClicked(modelId, gMapPolygon) {
         if (gMapPolygonCopyModel && gMapPolygonCopyModel.isForgMapPolygon(gMapPolygon)) {
            gMapPolygonCopyModel.deselect();
            gMapPolygonCopyModel = null;
         }
         grappStoreLayout.removeFeatureById(modelId);
         mapControl.removeFeatureById(modelId, gMapPolygon);
      }

      function polygonDragEnd(modelId, gMapPolygon) {
         grappStoreLayout.getFeatureById(modelId).commitPath(gMapPolygon.getPath().getArray());
      }

      function enableDragging(enable) {
         mapControl.applyToFeatures(function(gMapPolygon) { gMapPolygon.setDraggable(enable); });
      }

      function GMapPolygonCopyModel(gMapPolygon, copyLatLng) {
         var self = this;
         self.select = select;
         self.deselect = deselect;
         self.isForgMapPolygon = isForgMapPolygon;
         self.copyToLatLng = copyToLatLng;

         var copyOffsets = null;
         var originalStroke = null;

         initialize();

         ////////////////////

         function initialize() {
            copyOffsets = gMapPolygon.getPath().getArray().map(function(latLng) {
               return {
                  latOffset: latLng.lat() - copyLatLng.lat(),
                  lngOffset: latLng.lng() - copyLatLng.lng()
               };
            });
         }

         function select() {
            originalStroke = createStrokeOptions(gMapPolygon.strokeColor, gMapPolygon.strokeWeight);
            gMapPolygon.setOptions(createStrokeOptions("#ffffff", 4));
         }

         function deselect() {
            gMapPolygon.setOptions(originalStroke);
         }

         function copyToLatLng(latLng) {
            return new google.maps.Polygon({
               paths: copyOffsets.map(function(offset) {
                  return {
                     lat: latLng.lat() + offset.latOffset,
                     lng: latLng.lng() + offset.lngOffset
                  };
               }),
               strokeColor: originalStroke.strokeColor,
               strokeWeight: originalStroke.strokeWeight,
               fillColor: gMapPolygon.fillColor,
               fillOpacity: gMapPolygon.fillOpacity,
               draggable: gMapPolygon.draggable,
               zIndex: gMapPolygon.zIndex
            });
         }

         function isForgMapPolygon(othergMapPolygon) {
            return gMapPolygon === othergMapPolygon;
         }

         function createStrokeOptions(color, weight) {
            return {
               strokeColor: color,
               strokeWeight: weight
            };
         }
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .value("DrawFeatureEventHandler", DrawFeatureEventHandler);

   function DrawFeatureEventHandler(mapControl, grappStoreLayout) {
      var self = this;
      self.start = start;
      self.finish = finish;
      self.polygonComplete = polygonComplete;

      ////////////////////

      function start() {
         mapControl.setDrawingMode("POLYGON");
      }

      function finish() {
         mapControl.setDrawingMode(null);
      }

      function polygonComplete(gMapPolygon) {
         grappStoreLayout.addFeature(gMapPolygon.getPath().getArray())
            .then(function(model) {
               mapControl.addFeature(model.id, gMapPolygon);
            });
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .value("EditDeleteFeatureEventHandler", EditDeleteFeatureEventHandler);

   function EditDeleteFeatureEventHandler(mapControl, grappStoreLayout) {
      var self = this;
      self.finish = finish;
      self.polygonClicked = polygonClicked;
      self.polygonRightClicked = polygonRightClicked;

      var selectedGMapPolygonId = null;

      ////////////////////

      function finish() {
         setSelectedGMapPolygon(null);
      }

      function polygonClicked(modelId, gMapPolygon) {
         setSelectedGMapPolygon(new GMapPolygonModelId(modelId, gMapPolygon));
      }

      function polygonRightClicked(modelId, gMapPolygon, polyMouseEvent) {
         if (selectedGMapPolygonId.polygon === gMapPolygon && polyMouseEvent.hasOwnProperty("vertex") && gMapPolygon.getPath().getArray().length > 3) {
            gMapPolygon.getPath().removeAt(polyMouseEvent.vertex);
         }
         else {
            if (selectedGMapPolygonId.polygon === gMapPolygon) {
               selectedGMapPolygonId = null;
            }
            grappStoreLayout.removeFeatureById(modelId);
            mapControl.removeFeatureById(modelId);
         }
      }

      function setSelectedGMapPolygon(gMapPolygon) {
         if (selectedGMapPolygonId) {
            selectedGMapPolygonId.polygon.setEditable(false);
            grappStoreLayout.getFeatureById(selectedGMapPolygonId.id).commitPath(selectedGMapPolygonId.polygon.getPath().getArray());
         }

         selectedGMapPolygonId = gMapPolygon;
         if (selectedGMapPolygonId) {
            selectedGMapPolygonId.polygon.setEditable(true);
         }
      }

      function GMapPolygonModelId(modelId, gMapPolygon) {
         this.id = modelId;
         this.polygon = gMapPolygon;
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .value("EditOutlineEventHandler", EditOutlineEventHandler);

   function EditOutlineEventHandler(mapControl, storeOutlinePartialModel) {
      var self = this;
      self.start = start;
      self.finish = finish;
      self.polygonComplete = polygonComplete;
      self.polygonRightClicked = polygonRightClicked;

      ////////////////////

      function start() {
         var gMapPolygon = mapControl.getOutlineById(storeOutlinePartialModel.id);
         if (gMapPolygon) {
            gMapPolygon.setEditable(true);
         }
         else {
            mapControl.setDrawingMode("POLYGON");
         }
      }

      function finish() {
         var gMapPolygon = mapControl.getOutlineById(storeOutlinePartialModel.id);
         if (gMapPolygon) {
            gMapPolygon.setEditable(false);
            storeOutlinePartialModel.commitPath(gMapPolygon.getPath().getArray());
         }
      }

      function polygonComplete(gMapPolygon) {
         mapControl.setDrawingMode(null);
         mapControl.setOutlinePolygon(storeOutlinePartialModel.id, gMapPolygon);
      }

      function polygonRightClicked(modelId, gMapPolygon, polyMouseEvent) {
         if (polyMouseEvent.vertex !== null && gMapPolygon.getPath().getArray().length > 3) {
            gMapPolygon.getPath().removeAt(polyMouseEvent.vertex);
         }
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapLayout", MainStoreMapLayout);

   MainStoreMapLayout.$inject = ["grappStoreLayout", "mapControl", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditDeleteFeatureEventHandler", "CopyMoveDeleteFeatureEventHandler"];
   function MainStoreMapLayout(grappStoreLayout, mapControl, EditOutlineEventHandler, DrawFeatureEventHandler, EditDeleteFeatureEventHandler, CopyMoveDeleteFeatureEventHandler) {
      var mainStoreMapLayoutVM = this;
      mainStoreMapLayoutVM.radioModel = null;
      mainStoreMapLayoutVM.radioChanged = radioChanged;

      ////////////////////

      function radioChanged() {
         var mode = mainStoreMapLayoutVM.radioModel;
         if (mode === "outer" || mode === "inner") {
            mapControl.setEventHandler(new EditOutlineEventHandler(mapControl, mode === "outer" ? grappStoreLayout.outerOutline : grappStoreLayout.innerOutline));
         }
         else if (mode === "draw") {
            mapControl.setEventHandler(new DrawFeatureEventHandler(mapControl, grappStoreLayout));
         }
         else if (mode === "editDelete") {
            mapControl.setEventHandler(new EditDeleteFeatureEventHandler(mapControl, grappStoreLayout));
         }
         else if (mode === "copyMove") {
            mapControl.setEventHandler(new CopyMoveDeleteFeatureEventHandler(mapControl, grappStoreLayout));
         }
         else {
            mapControl.setEventHandler(null);
         }
      }
   }
})();