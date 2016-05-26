(function() {
   "use strict";

   angular.module("App")
      .controller("MainStores", MainStores);

   MainStores.$inject = ["$uibModal", "$state", "storeManager", "Confirmation"];
   function MainStores($uibModal, $state, storeManager, Confirmation) {
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
         openModalStore().then(function(result) {
            mainStoresVM.isLoading = true;
            storeManager.addStore(result.name, result.location).finally(function() {
               mainStoresVM.isLoading = false;
            });
         });
      }

      function updateSelectedStore() {
         var selectedStore = mainStoresVM.selectedStore;
         openModalStore(selectedStore).then(selectedStore.setAttributes);
      }

      function openModalStore(store) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/stores/ModalStore.html",
            controller: "ModalStore",
            controllerAs: "modalStoreVM",
            resolve: {
               store: function() { return store; }
            }
         }).result;
      }

      function editSelectedStore() {
         $state.go("main.storeLayout.features", {storeId: mainStoresVM.selectedStore.id});
      }

      function deleteSelectedStore() {
         var selectedStore = mainStoresVM.selectedStore;
         Confirmation.showModalDialog("Delete Store", "Are you sure you want to delete the Store " + selectedStore.name + "?")
            .then(function(result) {
               if (result) {
                  selectedStore.delete().then(function() {
                     _.remove(mainStoresVM.stores, mainStoresVM.selectedStore);
                  });
               }
            });
      }
   }
})();
