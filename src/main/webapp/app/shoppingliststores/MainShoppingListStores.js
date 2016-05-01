(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingListStores", MainShoppingListStores);

   MainShoppingListStores.$inject = ["$state", "$stateParams", "StoresMapControl", "stores"];
   function MainShoppingListStores($state, $stateParams, StoresMapControl, stores) {
      var mainShoppingListStoresVM = this;
      mainShoppingListStoresVM.mapControl = new StoresMapControl();
      mainShoppingListStoresVM.stores = stores;
      mainShoppingListStoresVM.markerClicked = markerClicked;
      mainShoppingListStoresVM.isAStoreSelected = isAStoreSelected;
      mainShoppingListStoresVM.showShoppingPlan = showShoppingPlan;

      var selectedStore = null;

      initialize();

      ////////////////////

      function initialize() {
         mainShoppingListStoresVM.mapControl.setMapBounds(_.calculateBounds(stores.map(function(store) { return store.location; })));
         mainShoppingListStoresVM.mapControl.setEventHandler(mainShoppingListStoresVM);
      }

      function markerClicked(modelId, gMapMarker, mouseEvent) {
         if (selectedStore) {
            mainShoppingListStoresVM.mapControl.setStoreOptions(selectedStore.id, {icon: gMapMarker.model.options.icon});
         }
         selectedStore = _.find(stores, function(store) { return modelId === store.id; });
         mainShoppingListStoresVM.mapControl.setStoreOptions(modelId, {icon: "content/img/marker_green_big.png"});
      }

      function isAStoreSelected() {
         return selectedStore !== null;
      }

      function showShoppingPlan() {
         $state.go("main.shoppingPlan", {listId: $stateParams.listId, storeId: selectedStore.id});
      }
   }
})();