(function() {
   "use strict";

   angular.module("App")
      .controller("MainStores", MainStores);

   MainStores.$inject = ["$uibModal", "$state", "storeManager"];
   function MainStores($uibModal, $state, storeManager) {
      var mainStoresVM = this;
      mainStoresVM.stores = storeManager.stores;
      mainStoresVM.selectedStore = null;
      mainStoresVM.areAnyStoresExistent = areAnyStoresExistent;
      mainStoresVM.setSelectedStore = setSelectedStore;
      mainStoresVM.isAStoreSelected = isAStoreSelected;
      mainStoresVM.createStore = createStore;
      mainStoresVM.updateSelectedStore = updateSelectedStore;
      mainStoresVM.editSelectedStore = editSelectedStore;
      mainStoresVM.deleteSelectedStore = deleteSelectedStore;

      ////////////////////

      function areAnyStoresExistent() {
         return mainStoresVM.stores.length > 0;
      }

      function setSelectedStore(store) {
         mainStoresVM.selectedStore = store;
      }

      function isAStoreSelected() {
         return mainStoresVM.selectedStore !== null;
      }

      function createStore() {
         openModalUpdateStore(null, null).then(function(result) {
            mainStoresVM.isLoading = true;
            storeManager.addStore(result.name, result.location).finally(function() {
               mainStoresVM.isLoading = false;
            });
         });
      }

      function updateSelectedStore() {
         var selectedStore = mainStoresVM.selectedStore;
         openModalUpdateStore(selectedStore.name, selectedStore.location).then(selectedStore.setAttributes);
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
            _.remove(mainStoresVM.stores, mainStoresVM.selectedStore);
         });
      }
   }
})();
