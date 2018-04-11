(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.shoppingListStores", {
            url: "shoppingList/:listId/stores",
            resolve: {
               stores: ["Store", function(Store) {
                  return Store.loadAll();
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/shoppingliststores/MainShoppingListStores.html",
                  controller: "MainShoppingListStores",
                  controllerAs: "mainShoppingListStoresVM"
               }
            }
         });
   }
})();