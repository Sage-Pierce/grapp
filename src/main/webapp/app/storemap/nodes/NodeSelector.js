(function() {
   "use strict";

   angular.module("Grapp")
      .value("NodeSelector", NodeSelector);

   function NodeSelector(mapControl, nodeSelectionHandler) {
      var self = this;
      self.select = select;
      self.deselect = deselect;
      self.isANodeSelected = isANodeSelected;
      self.getSelectedNode = getSelectedNode;

      var selectedNode = null;

      ////////////////////

      function select(node) {
         if (selectedNode) {
            deselect();
         }
         selectedNode = node;
         mapControl.setNodeOptions(selectedNode.id, {icon: "content/img/marker_yellow.png"});
         if (nodeSelectionHandler && nodeSelectionHandler.nodeSelected) {
            nodeSelectionHandler.nodeSelected(selectedNode);
         }
      }

      function deselect() {
         if (selectedNode) {
            mapControl.setNodeOptions(selectedNode.id, {icon: selectedNode.type.iconUrl});
            if (nodeSelectionHandler && nodeSelectionHandler.nodeDeselected) {
               nodeSelectionHandler.nodeDeselected(selectedNode);
            }
            selectedNode = null;
         }
      }

      function isANodeSelected() {
         return selectedNode != null;
      }

      function getSelectedNode() {
         return selectedNode;
      }
   }
})();