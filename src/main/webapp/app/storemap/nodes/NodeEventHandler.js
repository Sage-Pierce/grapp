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

      function mapClicked(modelId, map, mouseEvent) {
         grappStoreLayout.addNode(nodeType, mouseEvent.latLng)
            .then(function(result) {
               mapControl.addNode(result.node.id, createGMapMarker(map.markerOptions, mouseEvent.latLng));
               if (result.affectedNodes) {
                  result.affectedNodes.forEach(function(affectedNode) {
                     mapControl.setNodeOptions(affectedNode.id, {
                        icon: affectedNode.type.iconUrl
                     });
                  });
               }
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
         fullMarkerOptions.icon = nodeType.iconUrl;
         return new google.maps.Marker(fullMarkerOptions);
      }
   }
})();