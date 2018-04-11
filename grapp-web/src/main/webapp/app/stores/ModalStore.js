(function() {
   "use strict";

   angular.module("App")
      .controller("ModalStore", ModalStore);

   ModalStore.$inject = ["$uibModalInstance", "uiGmapUtil", "store", "AddressLookup"];
   function ModalStore($uibModalInstance, uiGmapUtil, store, AddressLookup) {
      var modalStoreVM = this;
      modalStoreVM.title = store ? "Update Store" : "Create New Store";
      modalStoreVM.store = _.mergeLeft({position: store && _.convertLocationToPosition(store.location)}, store || {name: ""});
      modalStoreVM.mapSettings = null;
      modalStoreVM.mapControl = {};
      modalStoreVM.markerEvents = null;
      modalStoreVM.searchBox = null;
      modalStoreVM.isDataValid = isDataValid;
      modalStoreVM.finish = finish;
      modalStoreVM.cancel = cancel;

      initialize();

      ////////////////////

      function initialize() {
         initializeMapSettings();
         initializeSearchBox();
         initializeMarkerEvents();
         uiGmapUtil.afterGmapsAreReady().then(function() {
            google.maps.event.trigger(modalStoreVM.mapControl.getGMap(), "resize");
            repositionMapByLocation(store && store.location);
         });
      }

      function initializeMapSettings() {
         modalStoreVM.mapSettings = {
            center: {latitude: 0, longitude: 0},
            zoom: 2,
            options: {
               draggableCursor: "pointer"
            },
            events: {
               click: function (source, eventName, args) {
                  uiGmapUtil.onNextAngularTurn().then(function() {
                     updateStorePositionFromLocation(args[0].latLng);
                  });
               }
            }
         };
      }

      function initializeSearchBox() {
         modalStoreVM.searchBox = {
            template: "app/stores/SearchBox.html",
            options: {},
            events: {
               places_changed: function(searchBox) {
                  var places = searchBox.getPlaces();
                  if (places && places != 'undefined' || places.length > 0) {
                     var location = places[0].geometry.location;
                     repositionMapByLocation(location);
                     updateStorePositionFromLocation(location);
                  }
               }
            }
         };
      }

      function initializeMarkerEvents() {
         modalStoreVM.markerEvents = {
            dragend: function(marker, eventName, model, args) {
               updateStorePositionFromLocation(args[0].latLng);
            }
         };
      }

      function isDataValid() {
         return modalStoreVM.store.name.length > 0 && modalStoreVM.store.position;
      }

      function finish() {
         $uibModalInstance.close(_.mergeLeft({location: _.convertPositionToLocation(modalStoreVM.store.position)}, modalStoreVM.store));
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }

      function updateStorePositionFromLocation(location) {
         modalStoreVM.store.position = _.convertLocationToPosition(location);
         AddressLookup.lookupLocation(_.convertPositionToLocation(modalStoreVM.store.position)).then(function(result) {
            modalStoreVM.store.approximateAddress = result;
         });
      }

      function repositionMapByLocation(location) {
         var gMap = modalStoreVM.mapControl.getGMap();
         var gMapOptions = gMap.getOptions();
         gMap.setCenter(location || gMapOptions.center);
         gMap.setZoom(location ? 16 : gMapOptions.zoom);
      }
   }
})();
