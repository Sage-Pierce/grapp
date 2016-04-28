(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.shoppingList", {
            url: "shoppingList/:listId",
            resolve: {
               shoppingList: ["$stateParams", "ShoppingList", function($stateParams, ShoppingList) {
                  return ShoppingList.loadById($stateParams.listId);
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/shoppinglist/MainShoppingList.html",
                  controller: "MainShoppingList",
                  controllerAs: "mainShoppingListVM"
               }
            }
         });
   }
})();