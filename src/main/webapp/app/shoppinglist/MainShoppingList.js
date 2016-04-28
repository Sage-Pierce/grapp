(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingList", MainShoppingList);

   MainShoppingList.$inject = ["shoppingList"];
   function MainShoppingList(shoppingList) {
      var mainShoppingListVM = this;
      mainShoppingListVM.shoppingList = shoppingList;
      mainShoppingListVM.items = [{primaryCode: "ABC", name: "Apples"}];
      mainShoppingListVM.removeItem = removeItem;
      mainShoppingListVM.selectStore = selectStore;

      ////////////////////

      function removeItem(item) {

      }

      function selectStore() {
         console.log("Proceed to Store Selection!");
      }
   }
})();