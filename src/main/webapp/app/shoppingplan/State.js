(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.shoppingPlan", {
            url: "shoppingPlan/:listId/:storeId",
            resolve: {
               store: ["$stateParams", "Store", function($stateParams, Store) {
                  return Store.loadById($stateParams.storeId);
               }],
               shoppingItems: ["$stateParams", "ShoppingList", function($stateParams, ShoppingList) {
                  return ShoppingList.loadById($stateParams.listId).then(filterShoppingItemsFromList);
               }],
               shoppingLayout: ["store", "shoppingItems", "ShoppingLayout", function(store, shoppingItems, ShoppingLayout) {
                  return ShoppingLayout.loadByIdForItems(store.layoutId, shoppingItems);
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/shoppingplan/MainShoppingPlan.html",
                  controller: "MainShoppingPlan",
                  controllerAs: "mainShoppingPlanVM"
               }
            }
         });

      function filterShoppingItemsFromList(shoppingList) {
         return shoppingList.items.filter(function(shoppingListItem) { return !shoppingListItem.obtained; })
                                  .map(function(shoppingListItem) { return shoppingListItem.item; });

      }
   }
})();