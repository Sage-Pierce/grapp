(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalUpdateStore", ModalUpdateStore);

   ModalUpdateStore.$inject = ["$uibModalInstance", "$timeout", "storeName", "storeLocation"];
   function ModalUpdateStore($uibModalInstance, $timeout, storeName, storeLocation) {
      var modalUpdateStoreVM = this;
      modalUpdateStoreVM.title = storeName ? "Update Store" : "Create New Store";
      modalUpdateStoreVM.storeName = storeName || "";
      modalUpdateStoreVM.storePosition = storeLocation && convertLocationToPosition(storeLocation);
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
         $timeout(function() {
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
                  $timeout(function () {
                     modalUpdateStoreVM.storePosition = convertLocationToPosition(args[0].latLng);
                  });
               }
            }
         };
      }

      function initializeSearchBox() {
         modalUpdateStoreVM.searchBox = {
            template: "app/stores/SearchBox.html",
            options: {

            },
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
         $uibModalInstance.close({name: modalUpdateStoreVM.storeName, location: convertPositionToGrappLocation(modalUpdateStoreVM.storePosition)});
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

      function convertLocationToPosition(location) {
         return {latitude: _.isFunction(location.lat) ? location.lat() : location.lat, longitude: _.isFunction(location.lng) ? location.lng() : location.lng};
      }

      function convertPositionToGrappLocation(position) {
         return {lat: position.latitude || position.lat(), lng: position.longitude || position.lng()};
      }
   }
})();
