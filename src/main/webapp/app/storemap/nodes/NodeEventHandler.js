(function() {
   "use strict";

   angular.module("Grapp")
      .value("NodeEventHandler", NodeEventHandler);

   function NodeEventHandler(mapControl, grappStoreLayout, grappStoreNodeType) {
      var self = this;
      self.mapClicked = mapClicked;
      self.markerRightClicked = markerRightClicked;
      self.markerDragEnd = markerDragEnd;
      self.setNodeType = setNodeType;

      var nodeType = grappStoreNodeType;

      ////////////////////

      function mapClicked(map, mouseEvent) {
         grappStoreLayout.addNode(mouseEvent.latLng)
            .then(function(model) {
               mapControl.addNode(model.id, createGMapMarker(map.markerOptions, mouseEvent.latLng));
            });
      }

      function markerRightClicked(modelId, gMapMarker, mouseEvent) {
         grappStoreLayout.removeNodeById(modelId);
         mapControl.removeNodeById(modelId);
      }

      function markerDragEnd(modelId, gMapMarker, mouseEvent) {
         grappStoreLayout.getNodeById(modelId).commitLocation(mouseEvent.latLng)
      }

      function setNodeType(grappStoreNodeType) {
         nodeType = grappStoreNodeType;
      }

      function createGMapMarker(options, position) {
         var fullMarkerOptions = _.clone(options);
         fullMarkerOptions.position = position;
         return new google.maps.Marker(fullMarkerOptions);
      }
   }
})();