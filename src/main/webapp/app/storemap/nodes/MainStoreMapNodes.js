(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapNodes", MainStoreMapNodes);

   MainStoreMapNodes.$inject = ["grappStoreLayout", "mapControl", "GrappStoreNodeType", "NodeEventHandler"];
   function MainStoreMapNodes(grappStoreLayout, mapControl, GrappStoreNodeType, NodeEventHandler) {
      var mainStoreMapNodesVM = this;
      mainStoreMapNodesVM.nodeTypes = GrappStoreNodeType.values();
      mainStoreMapNodesVM.radioModel = GrappStoreNodeType.REGULAR;
      mainStoreMapNodesVM.radioChanged = radioChanged;

      var nodeEventHandler = new NodeEventHandler(mapControl, grappStoreLayout, mainStoreMapNodesVM.radioModel);

      initialize();

      ////////////////////

      function initialize() {
         mapControl.setEventHandler(nodeEventHandler);
      }

      function radioChanged() {
         nodeEventHandler.setNodeType(mainStoreMapNodesVM.radioModel);
      }
   }
})();