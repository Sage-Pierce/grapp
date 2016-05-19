(function() {
   "use strict";

   angular.module("App")
      .controller("MainStoreLayoutNodes", MainStoreLayoutNodes);

   MainStoreLayoutNodes.$inject = ["storeLayout", "mapControl", "NodeSelectionHandler", "NodeType", "NodeEventHandler", "Item"];
   function MainStoreLayoutNodes(storeLayout, mapControl, NodeSelectionHandler, NodeType, NodeEventHandler, Item) {
      var mainStoreLayoutNodesVM = this;
      mainStoreLayoutNodesVM.selectedNodeName = null;
      mainStoreLayoutNodesVM.selectedNodeItems = [];
      mainStoreLayoutNodesVM.generalItems = [];
      mainStoreLayoutNodesVM.nodeNameChanged = nodeNameChanged;
      mainStoreLayoutNodesVM.addNodeItem = addNodeItem;
      mainStoreLayoutNodesVM.removeNodeItem = removeNodeItem;
      mainStoreLayoutNodesVM.isANodeSelected = function() { return false; };

      var nodeSelectionHandler = new NodeSelectionHandler(mapControl, {nodeSelected: nodeSelected, nodeDeselected: nodeDeselected});
      var nodeEventHandler = new NodeEventHandler(mapControl, storeLayout, NodeType.REGULAR, nodeSelectionHandler);

      initialize();

      ////////////////////

      function initialize() {
         mainStoreLayoutNodesVM.isANodeSelected = nodeSelectionHandler.isANodeSelected;
         mapControl.setEventHandler(nodeEventHandler);
         Item.loadAllGeneral().then(function(generalItems) {
            mainStoreLayoutNodesVM.generalItems = generalItems;
         });
      }

      function nodeNameChanged() {
         if (mainStoreLayoutNodesVM.isANodeSelected()) {
            nodeSelectionHandler.getSelectedNode().setAttributes({name: mainStoreLayoutNodesVM.selectedNodeName});
         }
      }

      function addNodeItem(itemNode) {
         var item = itemNode.$modelValue;
         storeLayout.addNodeItem(nodeSelectionHandler.getSelectedNode().id, {code: item.primaryCode, name: item.name})
            .then(function(nodeItem) {
               mainStoreLayoutNodesVM.selectedNodeItems.push(nodeItem.item);
            });
      }

      function removeNodeItem(nodeItem) {
         nodeSelectionHandler.getSelectedNode().removeItemById(nodeItem.id)
            .then(function() {
               _.remove(mainStoreLayoutNodesVM.selectedNodeItems, nodeItem);
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