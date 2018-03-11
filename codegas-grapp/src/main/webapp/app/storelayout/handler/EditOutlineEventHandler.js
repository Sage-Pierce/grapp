(function() {
   "use strict";

   angular.module("App")
      .factory("EditOutlineEventHandler", EditOutlineEventHandler);

   EditOutlineEventHandler.$inject = ["BaseEventHandler"];
   function EditOutlineEventHandler(BaseEventHandler) {
      return function(mapControl, storeLayout, storeOutlinePartialModel) {
         var self = angular.extend(this, new BaseEventHandler(mapControl, storeLayout));
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
               storeOutlinePartialModel.setVertices(_.extractVerticesFromGMapPolygon(gMapPolygon));
            }
         }

         function polygonComplete(gMapPolygon) {
            mapControl.setDrawingMode(null);
            mapControl.setOutlinePolygon(storeOutlinePartialModel.id, gMapPolygon);
         }

         function polygonRightClicked(modelId, gMapPolygon, polyMouseEvent) {
            if (polyMouseEvent.hasOwnProperty("vertex") && _.extractPathFromGMapPolygon(gMapPolygon).length > 3) {
               gMapPolygon.getPath().removeAt(polyMouseEvent.vertex);
            }
         }
      };
   }
})();