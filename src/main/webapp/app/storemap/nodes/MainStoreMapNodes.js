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
      mainStoreMapNodesVM.selectedNodeItems = [];
      mainStoreMapNodesVM.radioChanged = radioChanged;
      mainStoreMapNodesVM.nodeNameChanged = nodeNameChanged;
      mainStoreMapNodesVM.isANodeSelected = function() { return false; };

      var nodeSelector = new NodeSelector(mapControl, {nodeSelected: nodeSelected});
      var nodeEventHandler = new NodeEventHandler(mapControl, grappStoreLayout, nodeSelector, mainStoreMapNodesVM.radioModel);

      initialize();

      ////////////////////

      function initialize() {
         mainStoreMapNodesVM.isANodeSelected = nodeSelector.isANodeSelected;
         mapControl.setEventHandler(nodeEventHandler);
      }

      function radioChanged() {
         nodeEventHandler.setNodeType(mainStoreMapNodesVM.radioModel);
      }

      function nodeNameChanged() {
         if (mainStoreMapNodesVM.isANodeSelected()) {
            nodeSelector.getSelectedNode().commitName(mainStoreMapNodesVM.selectedNodeName);
         }
      }

      function nodeSelected(node) {
         mainStoreMapNodesVM.selectedNodeName = node.name;
      }
   }
})();