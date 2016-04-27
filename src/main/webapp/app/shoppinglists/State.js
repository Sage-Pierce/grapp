(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.shoppingLists", {
            url: "shoppingLists",
            resolve: {
               shopper: ["Login", "Shopper", function(Login, Shopper) {
                  return Login.afterLogIn().then(function(user) {
                     return Shopper.loadByEmail(user.email);
                  });
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/shoppinglists/MainShoppingLists.html",
                  controller: "MainShoppingLists",
                  controllerAs: "mainShoppingListsVM"
               }
            }
         });
   }
})();