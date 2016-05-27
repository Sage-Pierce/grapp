(function() {
   "use strict";

   angular.module("App")
      .factory("NodeEventHandler", NodeEventHandler);

   NodeEventHandler.$inject = ["BaseEventHandler"];
   function NodeEventHandler(BaseEventHandler) {
      return function(mapControl, storeLayout, nodeType, nodeSelectionHandler) {
         var base = new BaseEventHandler(mapControl, storeLayout);
         var self = angular.extend(this, base);
         self.start = start;
         self.finish = finish;
         self.mapClicked = mapClicked;
         self.mapRightClicked = mapRightClicked;
         self.markerClicked = markerClicked;
         self.markerRightClicked = markerRightClicked;
         self.setNodeType = setNodeType;

         ////////////////////

         function start() {
            mapControl.applyToFeatures(function(feature) { feature.setDraggable(false); });
         }

         function finish() {
            if (nodeSelectionHandler) {
               nodeSelectionHandler.deselect();
            }
            mapControl.applyToFeatures(function(feature) { feature.setDraggable(true); });
         }

         function mapClicked(modelId, map, mouseEvent) {
            storeLayout.addNode(nodeType, _.convertPositionToLocation(mouseEvent.latLng))
               .then(function(result) {
                  mapControl.addNode(result.node.id, createGMapMarker(map.markerOptions, mouseEvent.latLng));
                  if (result.affectedNodes) {
                     result.affectedNodes.forEach(updateNode);
                  }
               });
         }

         function mapRightClicked(modelId, map, mouseEvent) {
            if (nodeSelectionHandler) {
               nodeSelectionHandler.deselect();
            }
         }

         function markerClicked(modelId, gMapMarker, mouseEvent) {
            if (nodeSelectionHandler) {
               nodeSelectionHandler.select(storeLayout.getNodeById(modelId));
            }
         }

         function markerRightClicked(modelId, gMapMarker, mouseEvent) {
            if (nodeSelectionHandler && nodeSelectionHandler.getSelectedNode().id === modelId) {
               nodeSelectionHandler.deselect();
            }
            base.markerRightClicked(modelId, gMapMarker, mouseEvent);
         }

         function setNodeType(nt) {
            nodeType = nt;
         }

         function createGMapMarker(options, position) {
            return new google.maps.Marker(_.merge({position: position, icon: nodeType.iconUrl}, options));
         }

         function updateNode(node) {
            mapControl.setNodeOptions(node.id, {
               icon: node.type.iconUrl
            });
         }
      };
   }
})();