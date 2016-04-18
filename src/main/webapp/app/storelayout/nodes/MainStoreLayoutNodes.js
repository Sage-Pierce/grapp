(function() {
   "use strict";

   angular.module("App")
      .controller("MainStoreLayoutNodes", MainStoreLayoutNodes);

   MainStoreLayoutNodes.$inject = ["storeLayout", "mapControl", "NodeSelector", "NodeType", "NodeEventHandler", "Item"];
   function MainStoreLayoutNodes(storeLayout, mapControl, NodeSelector, NodeType, NodeEventHandler, Item) {
      var mainStoreLayoutNodesVM = this;
      mainStoreLayoutNodesVM.nodeTypes = _.values(NodeType);
      mainStoreLayoutNodesVM.radioModel = NodeType.REGULAR;
      mainStoreLayoutNodesVM.selectedNodeName = null;
      mainStoreLayoutNodesVM.selectedNodeItems = [];
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
         var itemModel = itemNode.$modelValue;
         nodeSelector.getSelectedNode().addItem(itemModel.id, itemModel.name)
            .then(function(result) {
               mainStoreLayoutNodesVM.selectedNodeItems.push(result.item);
            });
      }

      function removeNodeItem(item) {
         nodeSelector.getSelectedNode().removeItemById(item.id)
            .then(function() {
               _.remove(mainStoreLayoutNodesVM.selectedNodeItems, function(otherItem) { return item === otherItem; })
            });
      }

      function nodeSelected(node) {
         mainStoreLayoutNodesVM.selectedNodeName = node.name;
         mainStoreLayoutNodesVM.selectedNodeItems = node.getItems();
      }

      function nodeDeselected() {
         mainStoreLayoutNodesVM.selectedNodeName = null;
         mainStoreLayoutNodesVM.selectedNodeName = [];
      }
   }
})();