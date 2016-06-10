(function() {
   "use strict";

   angular.module("App")
      .controller("MainStores", MainStores);

   MainStores.$inject = ["$uibModal", "$state", "StoreManager", "Messaging"];
   function MainStores($uibModal, $state, StoreManager, Messaging) {
      var mainStoresVM = this;
      mainStoresVM.transitionPromise = null;
      mainStoresVM.stores = [];
      mainStoresVM.selectedStore = null;
      mainStoresVM.areThereAnyStores = areThereAnyStores;
      mainStoresVM.setSelectedStore = setSelectedStore;
      mainStoresVM.isAStoreSelected = isAStoreSelected;
      mainStoresVM.createStore = createStore;
      mainStoresVM.updateSelectedStore = updateSelectedStore;
      mainStoresVM.editSelectedStore = editSelectedStore;
      mainStoresVM.deleteSelectedStore = deleteSelectedStore;

      var storeManager = null;

      initialize();

      ////////////////////

      function initialize() {
         mainStoresVM.transitionPromise = StoreManager.load().then(handleStoreManagerModel);
      }

      function areThereAnyStores() {
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
            storeManager.addStore(result.name, result.location);
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
         mainStoresVM.transitionPromise = $state.go("main.storeLayout.features", {storeId: mainStoresVM.selectedStore.id});
      }

      function deleteSelectedStore() {
         Messaging.requestConfirmation("Delete Store", "Are you sure you want to delete the Store " + mainStoresVM.selectedStore.name + "?")
            .then(mainStoresVM.selectedStore.delete)
            .then(function() { _.remove(mainStoresVM.stores, mainStoresVM.selectedStore); })
            .then(function() { mainStoresVM.selectedStore = null; });
      }

      function handleStoreManagerModel(storeManagerModel) {
         storeManager = storeManagerModel;
         mainStoresVM.stores = storeManager.stores;
      }
   }
})();
