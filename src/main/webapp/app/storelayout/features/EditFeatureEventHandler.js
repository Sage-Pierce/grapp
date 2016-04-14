(function() {
   "use strict";

   angular.module("Grapp")
      .factory("EditFeatureEventHandler", EditFeatureEventHandler);

   EditFeatureEventHandler.$inject = ["BaseEventHandler"];
   function EditFeatureEventHandler(BaseEventHandler) {
      return function(mapControl, storeLayout) {
         var self = angular.extend(this, new BaseEventHandler(mapControl, storeLayout));
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
            if (selectedGMapPolygonId.polygon === gMapPolygon && polyMouseEvent.hasOwnProperty("vertex") && _.extractPathFromGMapPolygon(gMapPolygon).length > 3) {
               gMapPolygon.getPath().removeAt(polyMouseEvent.vertex);
            }
            else {
               if (selectedGMapPolygonId.polygon === gMapPolygon) {
                  selectedGMapPolygonId = null;
               }
               storeLayout.removeFeatureById(modelId);
               mapControl.removeFeatureById(modelId);
            }
         }

         function setSelectedGMapPolygon(gMapPolygon) {
            if (selectedGMapPolygonId) {
               selectedGMapPolygonId.polygon.setEditable(false);
               storeLayout.getFeatureById(selectedGMapPolygonId.id).commitVertices(_.extractVerticesFromGMapPolygon(selectedGMapPolygonId.polygon));
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
      };
   }
})();