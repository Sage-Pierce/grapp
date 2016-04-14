(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMapFeatures", MainStoreMapFeatures);

   MainStoreMapFeatures.$inject = ["grappStoreLayout", "mapControl", "BaseEventHandler", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditFeatureEventHandler", "CopyFeatureEventHandler"];
   function MainStoreMapFeatures(grappStoreLayout, mapControl, BaseEventHandler, EditOutlineEventHandler, DrawFeatureEventHandler, EditFeatureEventHandler, CopyFeatureEventHandler) {
      var mainStoreMapFeaturesVM = this;
      mainStoreMapFeaturesVM.outlineRadioEventHandlerModels = null;
      mainStoreMapFeaturesVM.featureRadioEventHandlerModels = null;
      mainStoreMapFeaturesVM.radioModel = null;
      mainStoreMapFeaturesVM.radioChanged = radioChanged;

      var baseEventHandler = new BaseEventHandler(mapControl, grappStoreLayout);

      initialize();

      ////////////////////

      function initialize() {
         mapControl.setEventHandler(baseEventHandler);

         mainStoreMapFeaturesVM.outlineRadioEventHandlerModels = [
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, grappStoreLayout, grappStoreLayout.getOuterOutline()), "Outer"),
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, grappStoreLayout, grappStoreLayout.getInnerOutline()), "Inner")
         ];

         mainStoreMapFeaturesVM.featureRadioEventHandlerModels = [
            new RadioEventHandlerModel(new DrawFeatureEventHandler(mapControl, grappStoreLayout), "Draw"),
            new RadioEventHandlerModel(new EditFeatureEventHandler(mapControl, grappStoreLayout), "Edit"),
            new RadioEventHandlerModel(new CopyFeatureEventHandler(mapControl, grappStoreLayout), "Copy")
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