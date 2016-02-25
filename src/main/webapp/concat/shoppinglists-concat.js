(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
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