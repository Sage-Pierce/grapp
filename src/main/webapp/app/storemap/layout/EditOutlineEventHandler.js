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
            storeOutlinePartialModel.commitVertices(_.extractVerticesFromGMapPolygon(gMapPolygon));
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
   }
})();