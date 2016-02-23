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