(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreLayoutNodes", MainStoreLayoutNodes);

   MainStoreLayoutNodes.$inject = ["storeLayout", "mapControl", "NodeSelector", "NodeType", "NodeEventHandler", "Item"];
   function MainStoreLayoutNodes(storeLayout, mapControl, NodeSelector, NodeType, NodeEventHandler, Item) {
      var mainStoreLayoutNodesVM = this;
      mainStoreLayoutNodesVM.nodeTypes = _.values(NodeType);
      mainStoreLayoutNodesVM.radioModel = NodeType.REGULAR;
      mainStoreLayoutNodesVM.selectedNodeName = null;
      mainStoreLayoutNodesVM.selectedNodeItems = [{name: "Test 1"}, {name: "Test 2"}];
      mainStoreLayoutNodesVM.generalItems = [];
      mainStoreLayoutNodesVM.radioChanged = radioChanged;
      mainStoreLayoutNodesVM.nodeNameChanged = nodeNameChanged;
      mainStoreLayoutNodesVM.addNodeItem = addNodeItem;
      mainStoreLayoutNodesVM.removeNodeItem = removeNodeItem;
      mainStoreLayoutNodesVM.isANodeSelected = function() { return false; };

      var nodeSelector = new NodeSelector(mapControl, {nodeSelected: nodeSelected, nodeDeselected: nodeDeselected});
      var nodeEventHandler = new NodeEventHandler(mapControl, storeLayout, nodeSelector, mainStoreLayoutNodesVM.radioModel);

      initialize();

      ////////////////////

      function initialize() {
         mainStoreLayoutNodesVM.isANodeSelected = nodeSelector.isANodeSelected;
         mapControl.setEventHandler(nodeEventHandler);
         Item.loadAllGeneral().then(function(generalItems) {
            mainStoreLayoutNodesVM.generalItems = generalItems;
         });
      }

      function radioChanged() {
         nodeEventHandler.setNodeType(mainStoreLayoutNodesVM.radioModel);
      }

      function nodeNameChanged() {
         if (mainStoreLayoutNodesVM.isANodeSelected()) {
            nodeSelector.getSelectedNode().commitName(mainStoreLayoutNodesVM.selectedNodeName);
         }
      }

      function addNodeItem(itemNode) {
         console.log("ADD MEEEE: ");
         console.log(itemNode.$modelValue);
      }

      function removeNodeItem(item) {
         console.log("REMOVE MEEEE: ");
         console.log(item);
      }

      function nodeSelected(node) {
         mainStoreLayoutNodesVM.selectedNodeName = node.name;
      }

      function nodeDeselected() {
         mainStoreLayoutNodesVM.selectedNodeName = null;
      }
   }
})();