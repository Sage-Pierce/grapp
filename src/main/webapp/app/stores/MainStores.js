(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStores", MainStores);

   MainStores.$inject = ["$uibModal", "$state", "Store"];
   function MainStores($uibModal, $state, Store) {
      var mainStoresVM = this;
      mainStoresVM.stores = [];
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
         Store.loadAll().then(function(stores) {
            mainStoresVM.stores = stores;
         });
      }

      function areAnyStoresExistent() {
         return mainStoresVM.stores.length > 0;
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
            Store.create(result.name, result.location).then(function(store) {
               mainStoresVM.stores.push(store);
            }).finally(function() {
               mainStoresVM.isLoading = false;
            });
         });
      }

      function updateSelectedStoreName() {
         var selectedStore = mainStoresVM.selectedStore;
         openModalUpdateStore(selectedStore.name, selectedStore.location).then(selectedStore.commitAttributes);
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
         $state.go("main.storeLayout.features", {storeId: mainStoresVM.selectedStore.id});
      }

      function deleteSelectedStore() {
         mainStoresVM.selectedStore.delete().then(function() {
            mainStoresVM.stores = _.without(mainStoresVM.stores, mainStoresVM.selectedStore);
         });
      }
   }
})();
