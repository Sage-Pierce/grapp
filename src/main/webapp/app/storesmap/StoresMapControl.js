(function() {
   "use strict";

   angular.module("App")
      .factory("StoresMapControl", StoresMapControl);

   StoresMapControl.$inject = ["uiGmapUtil"];
   function StoresMapControl(uiGmapUtil) {
      return function() {
         var self = this;
         self.setMapBounds = setMapBounds;
         self.setStoreOptions = setStoreOptions;
         self.handleGObjectMouseEvent = handleGObjectMouseEvent;
         self.setEventHandler = setEventHandler;
         self.setControls = setControls;

         var eventHandler = null;
         var mapControl = null;
         var storeControl = null;

         ////////////////////

         function setMapBounds(bounds) {
            uiGmapUtil.afterGmapsAreReady().then(function() {
               mapControl.getGMap().fitBounds(bounds);
            });
         }

         function setStoreOptions(storeId, gMapMarkerOptions) {
            var storePlural = storeControl.getPlurals().get(storeId);
            if (storePlural) {
               storePlural.gObject.setOptions(gMapMarkerOptions);
            }
         }

         function handleGObjectMouseEvent(eventName, modelId, gObject, event) {
            // This Event is valid if it has occurred on:
            // 1) A Map; or
            // 2) A GObject with a non-null Map (guard against "ghost" Events)
            var isValidGObjectEvent = modelId === "map" || gObject.getMap();
            if (isValidGObjectEvent && eventHandler && eventHandler[eventName]) {
               uiGmapUtil.onNextAngularTurn().then(function() {
                  eventHandler[eventName](modelId, gObject, event);
               });
            }
         }

         function setEventHandler(eh) {
            uiGmapUtil.afterGmapsAreReady().then(function() {
               if (eventHandler && eventHandler.finish) {
                  eventHandler.finish();
               }

               eventHandler = eh;
               if (eventHandler && eventHandler.start) {
                  eventHandler.start();
               }
            });
         }

         function setControls(controls) {
            mapControl = controls.map;
            storeControl = controls.store;
         }
      };
   }
})();