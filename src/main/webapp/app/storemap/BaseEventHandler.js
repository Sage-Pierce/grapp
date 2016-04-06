(function() {
   "use strict";

   angular.module("Grapp")
      .value("BaseEventHandler", BaseEventHandler);

   function BaseEventHandler(mapControl, grappStoreLayout) {
      var self = this;
      self.markerDragEnd = markerDragEnd;
      self.polygonDragEnd = polygonDragEnd;

      ////////////////////

      function markerDragEnd(modelId, gMapMarker, mouseEvent) {
         grappStoreLayout.getNodeById(modelId).commitLocation(_.convertPositionToLocation(mouseEvent.latLng));
      }

      function polygonDragEnd(modelId, gMapPolygon) {
         grappStoreLayout.getFeatureById(modelId).commitVertices(_.extractVerticesFromGMapPolygon(gMapPolygon));
      }
   }
})();