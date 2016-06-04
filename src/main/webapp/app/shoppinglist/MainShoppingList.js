(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingList", MainShoppingList);

   MainShoppingList.$inject = ["$state", "$stateParams", "shoppingList", "ItemLineage"];
   function MainShoppingList($state, $stateParams, shoppingList, ItemLineage) {
      var mainShoppingListVM = this;
      mainShoppingListVM.transitionPromise = null;
      mainShoppingListVM.shoppingList = shoppingList;
      mainShoppingListVM.items = [];
      mainShoppingListVM.itemToAdd = null;
      mainShoppingListVM.itemToAddSelected = itemToAddSelected;
      mainShoppingListVM.removeItem = removeItem;
      mainShoppingListVM.selectStore = selectStore;

      initialize();

      ////////////////////

      function initialize() {
         ItemLineage.loadAll().then(function(items) {
            mainShoppingListVM.items = items;
         });
      }

      function itemToAddSelected(item) {
         mainShoppingListVM.shoppingList.addItem({code: item.primaryCode, name: item.name});
         mainShoppingListVM.itemToAdd = null;
      }

      function removeItem(item) {
         mainShoppingListVM.shoppingList.removeItem(item);
      }

      function selectStore() {
         mainShoppingListVM.transitionPromise = $state.go("main.shoppingListStores", {listId: $stateParams.listId});
      }
   }
})();