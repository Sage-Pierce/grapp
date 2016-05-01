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
               storeLayout: ["store", function(store) {
                  return store.layoutId;
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