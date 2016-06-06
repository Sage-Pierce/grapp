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
      mainShoppingListVM.searchText = null;
      mainShoppingListVM.getFilteredItems = getFilteredItems;
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

      function getFilteredItems() {
         return mainShoppingListVM.searchText ? mainShoppingListVM.items.filter(createItemFilter(mainShoppingListVM.searchText))
                                              : mainShoppingListVM.items;
      }

      function itemToAddSelected(item) {
         mainShoppingListVM.shoppingList.addItem({code: item.primaryCode, name: item.name});
         mainShoppingListVM.searchText = null;
      }

      function removeItem(item) {
         mainShoppingListVM.shoppingList.removeItem(item);
      }

      function selectStore() {
         mainShoppingListVM.transitionPromise = $state.go("main.shoppingListStores", {listId: $stateParams.listId});
      }

      function createItemFilter(searchText) {
         var lowercaseSearchText = _.lowerCase(searchText);
         return function(item) {
            return _.lowerCase(item.name).indexOf(lowercaseSearchText) >= 0;
         };
      }
   }
})();