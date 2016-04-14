(function() {
   "use strict";

   angular.module("App")
      .value("BaseEventHandler", BaseEventHandler);

   function BaseEventHandler(mapControl, storeLayout) {
      var self = this;
      self.markerRightClicked = markerRightClicked;
      self.markerDragEnd = markerDragEnd;
      self.polygonRightClicked = polygonRightClicked;
      self.polygonDragEnd = polygonDragEnd;

      ////////////////////

      function markerRightClicked(modelId, gMapMarker, mouseEvent) {
         storeLayout.removeNodeById(modelId);
         mapControl.removeNodeById(modelId);
      }

      function markerDragEnd(modelId, gMapMarker, mouseEvent) {
         storeLayout.getNodeById(modelId).commitLocation(_.convertPositionToLocation(mouseEvent.latLng));
      }

      function polygonRightClicked(modelId, gMapPolygon, polyMouseEvent) {
         storeLayout.removeFeatureById(modelId);
         mapControl.removeFeatureById(modelId);
      }

      function polygonDragEnd(modelId, gMapPolygon) {
         storeLayout.getFeatureById(modelId).commitVertices(_.extractVerticesFromGMapPolygon(gMapPolygon));
      }
   }
})();