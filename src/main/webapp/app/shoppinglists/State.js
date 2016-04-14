(function() {
   "use strict";

   angular.module("Grapp")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.shoppingLists", {
            url: "shoppingLists",
            views: {
               "content": {
                  templateUrl: "app/shoppinglists/MainShoppingLists.html"
               }
            }
         });
   }
})();