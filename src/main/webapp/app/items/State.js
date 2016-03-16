(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
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