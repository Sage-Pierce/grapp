(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalUpdateStore", ModalUpdateStore);

   ModalUpdateStore.$inject = ["$uibModalInstance", "$timeout", "storeName", "storeLocation"];
   function ModalUpdateStore($uibModalInstance, $timeout, storeName, storeLocation) {
      var modalUpdateStoreVM = this;
      modalUpdateStoreVM.title = storeName ? "Update Store" : "Create New Store";
      modalUpdateStoreVM.storeName = storeName ? storeName : "";
      modalUpdateStoreVM.storeLocation = storeLocation ? storeLocation : null;
      modalUpdateStoreVM.map = null;
      modalUpdateStoreVM.searchBox = null;
      modalUpdateStoreVM.storeLocationMarkerOptions = null;
      modalUpdateStoreVM.isDataValid = isDataValid;
      modalUpdateStoreVM.finish = finish;
      modalUpdateStoreVM.cancel = cancel;

      initialize();

      ////////////////////

      function initialize() {
         modalUpdateStoreVM.map = {
            center: {
               latitude: 0,
               longitude: 0
            },
            zoom: 0,
            options: {
               draggableCursor: "pointer"
            },
            events: {
               click: function(source, eventName, args) {
                  $timeout(function() { setStoreLocation(args[0].latLng); });
               }
            },
            control: {}
         };

         modalUpdateStoreVM.searchBox = {
            template: "app/stores/SearchBox.html",
            events: {
               places_changed: function(searchBox) {
                  var places = searchBox.getPlaces();
                  if (places && places != 'undefined' || places.length > 0) {
                     repositionMapByPlace(places[0]);
                  }
               }
            }
         };

         modalUpdateStoreVM.storeLocationMarkerOptions = {
            draggable: true
         };

         $timeout(function() {
            google.maps.event.trigger(modalUpdateStoreVM.map.control.getGMap(), "resize");
            modalUpdateStoreVM.map.center = {
               latitude: storeLocation ? storeLocation.latitude : 0,
               longitude: storeLocation ? storeLocation.longitude : 0
            };
            modalUpdateStoreVM.map.zoom = storeLocation ? 16 : 2;
         });
      }

      function isDataValid() {
         return modalUpdateStoreVM.storeName && modalUpdateStoreVM.storeName.length > 0 && modalUpdateStoreVM.storeLocation;
      }

      function finish() {
         $uibModalInstance.close({name: modalUpdateStoreVM.storeName, location: modalUpdateStoreVM.storeLocation});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }

      function setStoreLocation(latLng) {
         modalUpdateStoreVM.storeLocation = {latitude: latLng.lat(), longitude: latLng.lng()};
      }

      function repositionMapByPlace(place) {
         modalUpdateStoreVM.map.center = {
            latitude: place.geometry.location.lat(),
            longitude: place.geometry.location.lng()
         };
         modalUpdateStoreVM.map.zoom = 16;
      }
   }
})();
