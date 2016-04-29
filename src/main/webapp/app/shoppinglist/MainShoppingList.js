(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingList", MainShoppingList);

   MainShoppingList.$inject = ["shoppingList", "ItemLineage"];
   function MainShoppingList(shoppingList, ItemLineage) {
      var mainShoppingListVM = this;
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
         console.log("Proceed to Store Selection!");
      }
   }
})();