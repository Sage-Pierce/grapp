(function() {
   "use strict";

   angular.module("Grapp")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.items", {
            url: "items",
            views: {
               "content": {
                  templateUrl: "app/items/MainItems.html",
                  controller: "MainItems",
                  controllerAs: "mainItemsVM"
               }
            }
         });
   }
})();