(function() {
   "use strict";

   angular.module("App")
      .controller("MainStoreLayoutNodes", MainStoreLayoutNodes);

   MainStoreLayoutNodes.$inject = ["storeLayout", "mapControl", "NodeSelectionHandler", "NodeType", "NodeEventHandler", "Item"];
   function MainStoreLayoutNodes(storeLayout, mapControl, NodeSelectionHandler, NodeType, NodeEventHandler, Item) {
      var mainStoreLayoutNodesVM = this;
      mainStoreLayoutNodesVM.loadingPromise = null;
      mainStoreLayoutNodesVM.selectedNodeName = null;
      mainStoreLayoutNodesVM.selectedNodeItems = [];
      mainStoreLayoutNodesVM.generalItems = [];
      mainStoreLayoutNodesVM.itemTreeOptions = null;
      mainStoreLayoutNodesVM.nodeNameChanged = nodeNameChanged;
      mainStoreLayoutNodesVM.addNodeItem = addNodeItem;
      mainStoreLayoutNodesVM.removeNodeItem = removeNodeItem;
      mainStoreLayoutNodesVM.isANodeSelected = function() { return false; };

      var nodeSelectionHandler = new NodeSelectionHandler(mapControl, {nodeSelected: nodeSelected, nodeDeselected: nodeDeselected});
      var nodeEventHandler = new NodeEventHandler(mapControl, storeLayout, NodeType.REGULAR, nodeSelectionHandler);
      var nodeItemMap = createNodeItemMap(storeLayout.getNodes());

      initialize();

      ////////////////////

      function initialize() {
         initializeItemTreeOptions();
         mainStoreLayoutNodesVM.isANodeSelected = nodeSelectionHandler.isANodeSelected;
         mapControl.setEventHandler(nodeEventHandler);
         mainStoreLayoutNodesVM.loadingPromise = Item.loadAllGeneral().then(function(generalItems) { mainStoreLayoutNodesVM.generalItems = generalItems; });
      }

      function nodeNameChanged() {
         if (mainStoreLayoutNodesVM.isANodeSelected()) {
            nodeSelectionHandler.getSelectedNode().setAttributes({name: mainStoreLayoutNodesVM.selectedNodeName});
         }
      }

      function addNodeItem(itemNode) {
         var item = itemNode.$modelValue;
         var selectedNode = nodeSelectionHandler.getSelectedNode();
         storeLayout.addNodeItem(selectedNode.id, {code: item.primaryCode, name: item.name})
            .then(function(nodeItem) {
               mainStoreLayoutNodesVM.selectedNodeItems.push(nodeItem.item);
               nodeItemMap[nodeItem.item.code] = selectedNode;
            });
      }

      function removeNodeItem(nodeItem) {
         nodeSelectionHandler.getSelectedNode().removeItemById(nodeItem.id)
            .then(function() {
               _.remove(mainStoreLayoutNodesVM.selectedNodeItems, nodeItem);
               delete nodeItemMap[nodeItem.code];
            });
      }

      function nodeSelected(node) {
         mainStoreLayoutNodesVM.selectedNodeName = node.name;
         mainStoreLayoutNodesVM.selectedNodeItems = node.getItems();
      }

      function nodeDeselected() {
         mainStoreLayoutNodesVM.selectedNodeName = null;
         mainStoreLayoutNodesVM.selectedNodeItems = [];
      }

      function createNodeItemMap(nodes) {
         var nodeItemMap = {};
         nodes.forEach(function(node) {
            node.getItems().forEach(function(item) {
               nodeItemMap[item.code] = node;
            });
         });
         return nodeItemMap;
      }

      function initializeItemTreeOptions() {
         mainStoreLayoutNodesVM.itemTreeOptions = {
            itemTemplateUrl: 'app/storelayout/NodeItemTemplate.html',
            scope: {
               isItemMapped: isItemMapped,
               getNodeNameMappingForItem: getNodeNameMappingForItem
            },
            treeClass: 'height-200',
            filterable: true,
            compact: true
         };
      }

      function isItemMapped(item) {
         return item.primaryCode in nodeItemMap;
      }

      function getNodeNameMappingForItem(item) {
         return nodeItemMap[item.primaryCode] ? "Located at " + nodeItemMap[item.primaryCode].name : "Not Mapped";
      }
   }
})();