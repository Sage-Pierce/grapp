(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapFeatures", MainStoreMapFeatures);

   MainStoreMapFeatures.$inject = ["storeLayout", "mapControl", "BaseEventHandler", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditFeatureEventHandler", "CopyFeatureEventHandler"];
   function MainStoreMapFeatures(storeLayout, mapControl, BaseEventHandler, EditOutlineEventHandler, DrawFeatureEventHandler, EditFeatureEventHandler, CopyFeatureEventHandler) {
      var mainStoreMapFeaturesVM = this;
      mainStoreMapFeaturesVM.outlineRadioEventHandlerModels = null;
      mainStoreMapFeaturesVM.featureRadioEventHandlerModels = null;
      mainStoreMapFeaturesVM.radioModel = null;
      mainStoreMapFeaturesVM.radioChanged = radioChanged;

      var baseEventHandler = new BaseEventHandler(mapControl, storeLayout);

      initialize();

      ////////////////////

      function initialize() {
         mapControl.setEventHandler(baseEventHandler);

         mainStoreMapFeaturesVM.outlineRadioEventHandlerModels = [
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, storeLayout, storeLayout.getOuterOutline()), "Outer"),
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, storeLayout, storeLayout.getInnerOutline()), "Inner")
         ];

         mainStoreMapFeaturesVM.featureRadioEventHandlerModels = [
            new RadioEventHandlerModel(new DrawFeatureEventHandler(mapControl, storeLayout), "Draw"),
            new RadioEventHandlerModel(new EditFeatureEventHandler(mapControl, storeLayout), "Edit"),
            new RadioEventHandlerModel(new CopyFeatureEventHandler(mapControl, storeLayout), "Copy")
         ];
      }

      function radioChanged() {
         mapControl.setEventHandler(mainStoreMapFeaturesVM.radioModel && mainStoreMapFeaturesVM.radioModel.eventHandler || baseEventHandler);
      }

      function RadioEventHandlerModel(eventHandler, displayString) {
         this.eventHandler = eventHandler;
         this.displayString = displayString;
      }
   }
})();