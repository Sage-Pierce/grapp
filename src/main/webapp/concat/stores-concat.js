(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappStore", GrappStore);

   GrappStore.$inject = ["GrappRoot"];
   function GrappStore(GrappRoot) {
      var self = this;
      self.loadByID = loadByID;
      self.load = load;

      ////////////////////

      function loadByID(grappStoreID) {
         return GrappRoot.loadResourceModelByID("store", grappStoreID, createModel);
      }

      function load(grappStoreRsc) {
         return GrappRoot.mergeResourceIntoModel(grappStoreRsc, createModel(grappStoreRsc));
      }

      function createModel(grappStoreRsc) {
         return new GrappStoreModel(grappStoreRsc);
      }

      function GrappStoreModel(grappStoreRsc) {
         var self = this;
         self.location = convertGrappPointTogMapPoint(grappStoreRsc.location);

         self.updateName = function(name) {
            return grappStoreRsc.$put("updateName", {name: name}).then(function(resource) {
               self.name = resource.name;
            });
         };

         self.updateLocation = function(gMapPoint) {
            return grappStoreRsc.$put("updateLocation", {location: JSON.stringify(convertgMapPointToGrappPoint(gMapPoint))}).then(function(resource) {
               self.location = convertGrappPointTogMapPoint(resource.location);
            });
         };

         self.delete = function() {
            return grappStoreRsc.$del("self");
         };

         function convertGrappPointTogMapPoint(grappPoint) {
            return grappPoint ? {latitude: grappPoint.lat, longitude: grappPoint.lng} : null;
         }

         function convertgMapPointToGrappPoint(gMapPoint) {
            return {lat: gMapPoint.latitude || gMapPoint.lat(), lng: gMapPoint.longitude || gMapPoint.lng()};
         }
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStores", MainStores);

   MainStores.$inject = ["$uibModal", "$state", "grappUser"];
   function MainStores($uibModal, $state, grappUser) {
      var mainStoresVM = this;
      mainStoresVM.grappStores = [];
      mainStoresVM.selectedStore = null;
      mainStoresVM.areAnyStoresExistent = areAnyStoresExistent;
      mainStoresVM.setSelectedStore = setSelectedStore;
      mainStoresVM.isAStoreSelected = isAStoreSelected;
      mainStoresVM.createStore = createStore;
      mainStoresVM.updateSelectedStoreName = updateSelectedStoreName;
      mainStoresVM.editSelectedStore = editSelectedStore;
      mainStoresVM.deleteSelectedStore = deleteSelectedStore;

      initialize();

      ////////////////////

      function initialize() {
         grappUser.loadStores().then(function(grappStores) {
            mainStoresVM.grappStores = grappStores;
         });
      }

      function areAnyStoresExistent() {
         return mainStoresVM.grappStores.length > 0;
      }

      function setSelectedStore(selectedStore) {
         mainStoresVM.selectedStore = selectedStore;
      }

      function isAStoreSelected() {
         return mainStoresVM.selectedStore !== null;
      }

      function createStore() {
         openModalUpdateStore(null, null).then(function(result) {
            mainStoresVM.isLoading = true;
            grappUser.createStore(result.name, result.location).then(function(grappStore) {
               mainStoresVM.grappStores.push(grappStore);
            }).finally(function() {
               mainStoresVM.isLoading = false;
            });
         });
      }

      function updateSelectedStoreName() {
         var selectedStore = mainStoresVM.selectedStore;
         openModalUpdateStore(selectedStore.name, selectedStore.location).then(function(result) {
            selectedStore.updateName(result.name)
               .then(function() {
                  selectedStore.updateLocation(result.location);
               });
         });
      }

      function openModalUpdateStore(storeName, storeLocation) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/stores/ModalUpdateStore.html",
            controller: "ModalUpdateStore",
            controllerAs: "modalUpdateStoreVM",
            resolve: {
               storeName: function() { return storeName; },
               storeLocation: function() { return storeLocation; }
            }
         }).result;
      }

      function editSelectedStore() {
         $state.go("main.storeMap.layout", {storeID: mainStoresVM.selectedStore.id});
      }

      function deleteSelectedStore() {
         mainStoresVM.selectedStore.delete().then(function() {
            removeObjectFromArray(mainStoresVM.grappStores, mainStoresVM.selectedStore);
         });
      }

      function removeObjectFromArray(array, object) {
         array.splice(array.indexOf(object), 1);
      }
   }
})();

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

(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
      $stateProvider
         .state("main.stores", {
            url: "stores",
            resolve: {
               grappUser: ["GrappLogIn", function(GrappLogIn) {
                  return GrappLogIn.afterLogIn();
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/stores/MainStores.html",
                  controller: "MainStores",
                  controllerAs: "mainStoresVM"
               }
            }
         });
   }
})();