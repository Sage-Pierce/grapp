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
         mainStoresVM.selectedStore.remove().then(function() {
            mainStoresVM.grappStores = _.without(mainStoresVM.grappStores, mainStoresVM.selectedStore);
         });
      }
   }
})();
