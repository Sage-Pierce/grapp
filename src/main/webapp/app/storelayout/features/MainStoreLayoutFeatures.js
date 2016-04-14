(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreLayoutFeatures", MainStoreLayoutFeatures);

   MainStoreLayoutFeatures.$inject = ["storeLayout", "mapControl", "BaseEventHandler", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditFeatureEventHandler", "CopyFeatureEventHandler"];
   function MainStoreLayoutFeatures(storeLayout, mapControl, BaseEventHandler, EditOutlineEventHandler, DrawFeatureEventHandler, EditFeatureEventHandler, CopyFeatureEventHandler) {
      var mainStoreLayoutFeaturesVM = this;
      mainStoreLayoutFeaturesVM.outlineRadioEventHandlerModels = null;
      mainStoreLayoutFeaturesVM.featureRadioEventHandlerModels = null;
      mainStoreLayoutFeaturesVM.radioModel = null;
      mainStoreLayoutFeaturesVM.radioChanged = radioChanged;

      var baseEventHandler = new BaseEventHandler(mapControl, storeLayout);

      initialize();

      ////////////////////

      function initialize() {
         mapControl.setEventHandler(baseEventHandler);

         mainStoreLayoutFeaturesVM.outlineRadioEventHandlerModels = [
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, storeLayout, storeLayout.getOuterOutline()), "Outer"),
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, storeLayout, storeLayout.getInnerOutline()), "Inner")
         ];

         mainStoreLayoutFeaturesVM.featureRadioEventHandlerModels = [
            new RadioEventHandlerModel(new DrawFeatureEventHandler(mapControl, storeLayout), "Draw"),
            new RadioEventHandlerModel(new EditFeatureEventHandler(mapControl, storeLayout), "Edit"),
            new RadioEventHandlerModel(new CopyFeatureEventHandler(mapControl, storeLayout), "Copy")
         ];
      }

      function radioChanged() {
         mapControl.setEventHandler(mainStoreLayoutFeaturesVM.radioModel && mainStoreLayoutFeaturesVM.radioModel.eventHandler || baseEventHandler);
      }

      function RadioEventHandlerModel(eventHandler, displayString) {
         this.eventHandler = eventHandler;
         this.displayString = displayString;
      }
   }
})();