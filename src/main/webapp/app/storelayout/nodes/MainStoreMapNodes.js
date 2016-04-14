(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapNodes", MainStoreMapNodes);

   MainStoreMapNodes.$inject = ["storeLayout", "mapControl", "NodeSelector", "NodeType", "NodeEventHandler", "Item"];
   function MainStoreMapNodes(storeLayout, mapControl, NodeSelector, NodeType, NodeEventHandler, Item) {
      var mainStoreMapNodesVM = this;
      mainStoreMapNodesVM.nodeTypes = _.values(NodeType);
      mainStoreMapNodesVM.radioModel = NodeType.REGULAR;
      mainStoreMapNodesVM.selectedNodeName = null;
      mainStoreMapNodesVM.selectedNodeItems = [{name: "Test 1"}, {name: "Test 2"}];
      mainStoreMapNodesVM.generalItems = [];
      mainStoreMapNodesVM.radioChanged = radioChanged;
      mainStoreMapNodesVM.nodeNameChanged = nodeNameChanged;
      mainStoreMapNodesVM.addNodeItem = addNodeItem;
      mainStoreMapNodesVM.removeNodeItem = removeNodeItem;
      mainStoreMapNodesVM.isANodeSelected = function() { return false; };

      var nodeSelector = new NodeSelector(mapControl, {nodeSelected: nodeSelected, nodeDeselected: nodeDeselected});
      var nodeEventHandler = new NodeEventHandler(mapControl, storeLayout, nodeSelector, mainStoreMapNodesVM.radioModel);

      initialize();

      ////////////////////

      function initialize() {
         mainStoreMapNodesVM.isANodeSelected = nodeSelector.isANodeSelected;
         mapControl.setEventHandler(nodeEventHandler);
         Item.loadAllGeneral().then(function(generalItems) {
            mainStoreMapNodesVM.generalItems = generalItems;
         });
      }

      function radioChanged() {
         nodeEventHandler.setNodeType(mainStoreMapNodesVM.radioModel);
      }

      function nodeNameChanged() {
         if (mainStoreMapNodesVM.isANodeSelected()) {
            nodeSelector.getSelectedNode().commitName(mainStoreMapNodesVM.selectedNodeName);
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
         mainStoreMapNodesVM.selectedNodeName = node.name;
      }

      function nodeDeselected() {
         mainStoreMapNodesVM.selectedNodeName = null;
      }
   }
})();