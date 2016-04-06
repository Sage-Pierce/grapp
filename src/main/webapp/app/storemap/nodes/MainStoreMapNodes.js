(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapNodes", MainStoreMapNodes);

   MainStoreMapNodes.$inject = ["grappStoreLayout", "mapControl", "NodeSelector", "GrappStoreNodeType", "NodeEventHandler"];
   function MainStoreMapNodes(grappStoreLayout, mapControl, NodeSelector, GrappStoreNodeType, NodeEventHandler) {
      var mainStoreMapNodesVM = this;
      mainStoreMapNodesVM.nodeTypes = _.values(GrappStoreNodeType);
      mainStoreMapNodesVM.radioModel = GrappStoreNodeType.REGULAR;
      mainStoreMapNodesVM.selectedNodeName = null;
      mainStoreMapNodesVM.radioChanged = radioChanged;
      mainStoreMapNodesVM.nodeNameChanged = nodeNameChanged;
      mainStoreMapNodesVM.isANodeSelected = isANodeSelected;

      var selectedNode = null;
      var nodeSelector = new NodeSelector(mapControl);
      var nodeEventHandler = new NodeEventHandler(mapControl, grappStoreLayout, nodeSelector, mainStoreMapNodesVM.radioModel);

      initialize();

      ////////////////////

      function initialize() {
         nodeSelector.setNodeSelectionHandler({nodeSelected: nodeSelected});
         mapControl.setEventHandler(nodeEventHandler);
      }

      function radioChanged() {
         nodeEventHandler.setNodeType(mainStoreMapNodesVM.radioModel);
      }

      function nodeNameChanged() {
         if (isANodeSelected()) {
            selectedNode.commitName(mainStoreMapNodesVM.selectedNodeName);
         }
      }

      function isANodeSelected() {
         return selectedNode !== null;
      }

      function nodeSelected(node) {
         selectedNode = node;
         mainStoreMapNodesVM.selectedNodeName = node.name;
      }
   }
})();