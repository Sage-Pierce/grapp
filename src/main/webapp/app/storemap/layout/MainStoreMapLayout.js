(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapLayout", MainStoreMapLayout);

   MainStoreMapLayout.$inject = ["grappStoreLayout", "mapControl", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditDeleteFeatureEventHandler", "CopyMoveDeleteFeatureEventHandler"];
   function MainStoreMapLayout(grappStoreLayout, mapControl, EditOutlineEventHandler, DrawFeatureEventHandler, EditDeleteFeatureEventHandler, CopyMoveDeleteFeatureEventHandler) {
      var mainStoreMapLayoutVM = this;
      mainStoreMapLayoutVM.radioModel = null;
      mainStoreMapLayoutVM.radioChanged = radioChanged;

      ////////////////////

      function radioChanged() {
         var mode = mainStoreMapLayoutVM.radioModel;
         if (mode === "outer" || mode === "inner") {
            mapControl.setEventHandler(new EditOutlineEventHandler(mapControl, mode === "outer" ? grappStoreLayout.outerOutline : grappStoreLayout.innerOutline));
         }
         else if (mode === "draw") {
            mapControl.setEventHandler(new DrawFeatureEventHandler(mapControl, grappStoreLayout));
         }
         else if (mode === "editDelete") {
            mapControl.setEventHandler(new EditDeleteFeatureEventHandler(mapControl, grappStoreLayout));
         }
         else if (mode === "copyMove") {
            mapControl.setEventHandler(new CopyMoveDeleteFeatureEventHandler(mapControl, grappStoreLayout));
         }
         else {
            mapControl.setEventHandler(null);
         }
      }
   }
})();