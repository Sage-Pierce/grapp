(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingList", MainShoppingList);

   MainShoppingList.$inject = ["$document", "$q", "$state", "$stateParams", "ShoppingList", "ItemLineage"];
   function MainShoppingList($document, $q, $state, $stateParams, ShoppingList, ItemLineage) {
      var mainShoppingListVM = this;
      mainShoppingListVM.transitionPromise = null;
      mainShoppingListVM.shoppingList = null;
      mainShoppingListVM.items = [];
      mainShoppingListVM.searchText = null;
      mainShoppingListVM.getFilteredItems = getFilteredItems;
      mainShoppingListVM.itemToAddSelected = itemToAddSelected;
      mainShoppingListVM.setShoppingItemObtainment = setShoppingItemObtainment;
      mainShoppingListVM.removeShoppingItem = removeShoppingItem;
      mainShoppingListVM.selectStore = selectStore;

      initialize();

      ////////////////////

      function initialize() {
         var listPromise = ShoppingList.loadById($stateParams.listId).then(function(shoppingListModel) { mainShoppingListVM.shoppingList = shoppingListModel; });
         var itemPromise = ItemLineage.loadAll().then(function(items) { mainShoppingListVM.items = items; });
         mainShoppingListVM.transitionPromise = $q.all([listPromise, itemPromise]);
      }

      function getFilteredItems() {
         return mainShoppingListVM.searchText ? mainShoppingListVM.items.filter(createItemFilter(mainShoppingListVM.searchText))
                                              : mainShoppingListVM.items;
      }

      function itemToAddSelected(item) {
         mainShoppingListVM.shoppingList.addItem({code: item.primaryCode, name: item.name});
         mainShoppingListVM.searchText = null;
         $document[0].activeElement.blur();
      }

      function setShoppingItemObtainment(item, obtained) {
         item.setAttributes({obtained: obtained});
      }

      function removeShoppingItem(item) {
         mainShoppingListVM.shoppingList.removeShoppingItem(item);
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