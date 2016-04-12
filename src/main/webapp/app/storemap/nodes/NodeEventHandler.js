(function() {
   "use strict";

   angular.module("Grapp")
      .factory("NodeEventHandler", NodeEventHandler);

   NodeEventHandler.$inject = ["BaseEventHandler"];
   function NodeEventHandler(BaseEventHandler) {
      return function(mapControl, grappStoreLayout, nodeSelector, grappStoreNodeType) {
         var base = new BaseEventHandler(mapControl, grappStoreLayout);
         var self = angular.extend(this, base);
         self.finish = finish;
         self.mapClicked = mapClicked;
         self.mapRightClicked = mapRightClicked;
         self.markerClicked = markerClicked;
         self.markerRightClicked = markerRightClicked;
         self.setNodeType = setNodeType;

         var nodeType = grappStoreNodeType;

         ////////////////////

         function finish() {
            nodeSelector.deselect()
         }

         function mapClicked(modelId, map, mouseEvent) {
            grappStoreLayout.addNode(nodeType, _.convertPositionToLocation(mouseEvent.latLng))
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

         function mapRightClicked(modelId, map, mouseEvent) {
            nodeSelector.deselect();
         }

         function markerClicked(modelId, gMapMarker, mouseEvent) {
            nodeSelector.select(grappStoreLayout.getNodeById(modelId));
         }

         function markerRightClicked(modelId, gMapMarker, mouseEvent) {
            if (nodeSelector.getSelectedNode().id === modelId) {
               nodeSelector.deselect();
            }
            base.markerRightClicked(modelId, gMapMarker, mouseEvent);
         }

         function setNodeType(grappStoreNodeType) {
            nodeType = grappStoreNodeType;
         }

         function createGMapMarker(options, position) {
            return new google.maps.Marker(_.merge({position: position, icon: nodeType.iconUrl}, options));
         }
      };
   }
})();