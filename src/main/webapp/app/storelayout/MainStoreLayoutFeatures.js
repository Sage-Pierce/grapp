(function() {
   "use strict";

   angular.module("App")
      .controller("MainStoreLayoutFeatures", MainStoreLayoutFeatures);

   MainStoreLayoutFeatures.$inject = ["storeLayout", "mapControl", "NodeType", "BaseEventHandler", "EditOutlineEventHandler", "DrawFeatureEventHandler", "EditFeatureEventHandler", "CopyFeatureEventHandler", "NodeEventHandler"];
   function MainStoreLayoutFeatures(storeLayout, mapControl, NodeType, BaseEventHandler, EditOutlineEventHandler, DrawFeatureEventHandler, EditFeatureEventHandler, CopyFeatureEventHandler, NodeEventHandler) {
      var mainStoreLayoutFeaturesVM = this;
      mainStoreLayoutFeaturesVM.outlineRadioEventHandlerModels = null;
      mainStoreLayoutFeaturesVM.featureRadioEventHandlerModels = null;
      mainStoreLayoutFeaturesVM.nodeRadioEventHandlerModels = null;
      mainStoreLayoutFeaturesVM.radioModel = null;
      mainStoreLayoutFeaturesVM.radioChanged = radioChanged;

      var baseEventHandler = new BaseEventHandler(mapControl, storeLayout);

      initialize();

      ////////////////////

      function initialize() {
         mapControl.setEventHandler(baseEventHandler);

         mainStoreLayoutFeaturesVM.outlineRadioEventHandlerModels = [
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, storeLayout, storeLayout.outerOutline), "Outer"),
            new RadioEventHandlerModel(new EditOutlineEventHandler(mapControl, storeLayout, storeLayout.innerOutline), "Inner")
         ];

         mainStoreLayoutFeaturesVM.featureRadioEventHandlerModels = [
            new RadioEventHandlerModel(new DrawFeatureEventHandler(mapControl, storeLayout), "Draw"),
            new RadioEventHandlerModel(new EditFeatureEventHandler(mapControl, storeLayout), "Edit"),
            new RadioEventHandlerModel(new CopyFeatureEventHandler(mapControl, storeLayout), "Copy")
         ];

         mainStoreLayoutFeaturesVM.nodeRadioEventHandlerModels = [
            new RadioEventHandlerModel(new NodeEventHandler(mapControl, storeLayout, NodeType.ENTRANCE), NodeType.ENTRANCE.displayString),
            new RadioEventHandlerModel(new NodeEventHandler(mapControl, storeLayout, NodeType.CHECKOUT), NodeType.CHECKOUT.displayString)
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