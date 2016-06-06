(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.shoppingList", {
            url: "shoppingList/:listId",
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