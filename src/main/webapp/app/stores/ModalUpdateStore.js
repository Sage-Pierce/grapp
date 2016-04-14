(function() {
   "use strict";

   angular.module("App")
      .controller("ModalUpdateStore", ModalUpdateStore);

   ModalUpdateStore.$inject = ["$uibModalInstance", "uiGmapUtil", "storeName", "storeLocation"];
   function ModalUpdateStore($uibModalInstance, uiGmapUtil, storeName, storeLocation) {
      var modalUpdateStoreVM = this;
      modalUpdateStoreVM.title = storeName ? "Update Store" : "Create New Store";
      modalUpdateStoreVM.storeName = storeName || "";
      modalUpdateStoreVM.storePosition = storeLocation && _.convertLocationToPosition(storeLocation);
      modalUpdateStoreVM.mapSettings = null;
      modalUpdateStoreVM.mapControl = {};
      modalUpdateStoreVM.searchBox = null;
      modalUpdateStoreVM.isDataValid = isDataValid;
      modalUpdateStoreVM.finish = finish;
      modalUpdateStoreVM.cancel = cancel;

      initialize();

      ////////////////////

      function initialize() {
         initializeMapSettings();
         initializeSearchBox();
         uiGmapUtil.afterGmapsAreReady().then(function() {
            google.maps.event.trigger(modalUpdateStoreVM.mapControl.getGMap(), "resize");
            repositionMapByLocation(storeLocation);
         });
      }

      function initializeMapSettings() {
         modalUpdateStoreVM.mapSettings = {
            center: {latitude: 0, longitude: 0},
            zoom: 2,
            options: {
               draggableCursor: "pointer"
            },
            events: {
               click: function (source, eventName, args) {
                  uiGmapUtil.onNextAngularTurn().then(function () {
                     modalUpdateStoreVM.storePosition = _.convertLocationToPosition(args[0].latLng);
                  });
               }
            }
         };
      }

      function initializeSearchBox() {
         modalUpdateStoreVM.searchBox = {
            template: "app/stores/SearchBox.html",
            options: {},
            events: {
               places_changed: function (searchBox) {
                  var places = searchBox.getPlaces();
                  if (places && places != 'undefined' || places.length > 0) {
                     repositionMapByLocation(places[0].geometry.location);
                  }
               }
            }
         };
      }

      function isDataValid() {
         return modalUpdateStoreVM.storeName && modalUpdateStoreVM.storeName.length > 0 && modalUpdateStoreVM.storePosition;
      }

      function finish() {
         $uibModalInstance.close({name: modalUpdateStoreVM.storeName, location: _.convertPositionToLocation(modalUpdateStoreVM.storePosition)});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }

      function repositionMapByLocation(location) {
         var gMap = modalUpdateStoreVM.mapControl.getGMap();
         var gMapOptions = gMap.getOptions();
         gMap.setCenter(location || gMapOptions.center);
         gMap.setZoom(location ? 16 : gMapOptions.zoom);
      }
   }
})();
