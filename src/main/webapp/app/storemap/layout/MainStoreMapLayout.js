(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapLayout", MainStoreMapLayout);

   MainStoreMapLayout.$inject = ["grappStoreLayout", "mapControl", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditFeatureEventHandler", "CopyFeatureEventHandler"];
   function MainStoreMapLayout(grappStoreLayout, mapControl, EditOutlineEventHandler, DrawFeatureEventHandler, EditFeatureEventHandler, CopyFeatureEventHandler) {
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
            new RadioEventHandlerModel(new EditFeatureEventHandler(mapControl, grappStoreLayout), "Edit"),
            new RadioEventHandlerModel(new CopyFeatureEventHandler(mapControl, grappStoreLayout), "Copy")
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