(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapLayout", MainStoreMapLayout);

   MainStoreMapLayout.$inject = ["grappStoreLayout", "mapControl", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditDeleteFeatureEventHandler", "CopyMoveDeleteFeatureEventHandler"];
   function MainStoreMapLayout(grappStoreLayout, mapControl, EditOutlineEventHandler, DrawFeatureEventHandler, EditDeleteFeatureEventHandler, CopyMoveDeleteFeatureEventHandler) {
      var mainStoreMapLayoutVM = this;
      mainStoreMapLayoutVM.outlineRadioEventHandlerModels = null;
      mainStoreMapLayoutVM.featureRadioEventHandlerModels = null;
      mainStoreMapLayoutVM.radioModel = null;
      mainStoreMapLayoutVM.radioChanged = radioChanged;

      initialize();

      ////////////////////

      function initialize() {
         mapControl.setEventHandler(null);

         mainStoreMapLayoutVM.outlineRadioEventHandlerModels = [
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, grappStoreLayout, grappStoreLayout.getOuterOutline()), "Outer"),
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, grappStoreLayout, grappStoreLayout.getInnerOutline()), "Inner")
         ];

         mainStoreMapLayoutVM.featureRadioEventHandlerModels = [
            new RadioEventHandlerModel(new DrawFeatureEventHandler(mapControl, grappStoreLayout), "Draw"),
            new RadioEventHandlerModel(new EditDeleteFeatureEventHandler(mapControl, grappStoreLayout), "Edit | Delete"),
            new RadioEventHandlerModel(new CopyMoveDeleteFeatureEventHandler(mapControl, grappStoreLayout), "Copy/Move | Delete")
         ];
      }

      function radioChanged() {
         mapControl.setEventHandler(mainStoreMapLayoutVM.radioModel && mainStoreMapLayoutVM.radioModel.eventHandler);
      }

      function RadioEventHandlerModel(eventHandler, displayString) {
         this.eventHandler = eventHandler;
         this.displayString = displayString;
      }
   }
})();