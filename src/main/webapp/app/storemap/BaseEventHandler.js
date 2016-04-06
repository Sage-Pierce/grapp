(function() {
   "use strict";

   angular.module("Grapp")
      .value("BaseEventHandler", BaseEventHandler);

   function BaseEventHandler(mapControl, grappStoreLayout) {
      var self = this;
      self.markerDragEnd = markerDragEnd;

      ////////////////////

      function markerDragEnd(modelId, gMapMarker, mouseEvent) {
         grappStoreLayout.getNodeById(modelId).commitLocation(_.convertPositionToLocation(mouseEvent.latLng));
      }
   }
})();