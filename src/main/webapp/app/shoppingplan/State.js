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
               shoppingList: ["$stateParams", "ShoppingList", function($stateParams, ShoppingList) {
                  return ShoppingList.loadById($stateParams.listId);
               }],
               store: ["$stateParams", "Store", function($stateParams, Store) {
                  return Store.loadById($stateParams.storeId);
               }],
               shoppingLayout: ["store", "shoppingList", "ShoppingLayout", function(store, shoppingList, ShoppingLayout) {
                  return ShoppingLayout.loadByIdForItems(store.layoutId, shoppingList.items.map(function(shoppingListItem) { return shoppingListItem.item; }));
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
   }
})();