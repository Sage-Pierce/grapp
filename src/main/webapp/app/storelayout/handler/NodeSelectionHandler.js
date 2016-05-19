(function() {
   "use strict";

   angular.module("App")
      .value("NodeSelectionHandler", NodeSelectionHandler);

   function NodeSelectionHandler(mapControl, nodeSelectionHandler) {
      var self = this;
      self.select = select;
      self.deselect = deselect;
      self.isANodeSelected = isANodeSelected;
      self.getSelectedNode = getSelectedNode;

      var selectedNode = null;

      ////////////////////

      function select(node) {
         if (selectedNode !== node) {
            quietlySelect(node);
            if (nodeSelectionHandler && nodeSelectionHandler.nodeSelected) {
               nodeSelectionHandler.nodeSelected(selectedNode);
            }
         }
      }

      function deselect() {
         if (isANodeSelected()) {
            quietlyDeselect();
            if (nodeSelectionHandler && nodeSelectionHandler.nodeDeselected) {
               nodeSelectionHandler.nodeDeselected();
            }
         }
      }

      function isANodeSelected() {
         return selectedNode != null;
      }

      function getSelectedNode() {
         return selectedNode;
      }

      function quietlySelect(node) {
         quietlyDeselect();
         mapControl.setNodeOptions(node.id, {icon: "content/img/marker_yellow.png"});
         selectedNode = node;
      }

      function quietlyDeselect() {
         if (selectedNode) {
            mapControl.setNodeOptions(selectedNode.id, {icon: selectedNode.type.iconUrl});
            selectedNode = null;
         }
      }
   }
})();