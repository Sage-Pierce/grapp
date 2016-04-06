(function() {
   "use strict";

   angular.module("Grapp")
      .value("BaseEventHandler", BaseEventHandler);

   function BaseEventHandler(mapControl, grappStoreLayout) {
      var self = this;
      self.markerRightClicked = markerRightClicked;
      self.markerDragEnd = markerDragEnd;
      self.polygonRightClicked = polygonRightClicked;
      self.polygonDragEnd = polygonDragEnd;

      ////////////////////

      function markerRightClicked(modelId, gMapMarker, mouseEvent) {
         grappStoreLayout.removeNodeById(modelId);
         mapControl.removeNodeById(modelId);
      }

      function markerDragEnd(modelId, gMapMarker, mouseEvent) {
         grappStoreLayout.getNodeById(modelId).commitLocation(_.convertPositionToLocation(mouseEvent.latLng));
      }

      function polygonRightClicked(modelId, gMapPolygon, polyMouseEvent) {
         grappStoreLayout.removeFeatureById(modelId);
         mapControl.removeFeatureById(modelId);
      }

      function polygonDragEnd(modelId, gMapPolygon) {
         grappStoreLayout.getFeatureById(modelId).commitVertices(_.extractVerticesFromGMapPolygon(gMapPolygon));
      }
   }
})();