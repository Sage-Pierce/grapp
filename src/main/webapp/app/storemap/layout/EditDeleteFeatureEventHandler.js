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