(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.stores", {
            url: "stores",
            views: {
               "content": {
                  templateUrl: "app/stores/MainStores.html",
                  controller: "MainStores",
                  controllerAs: "mainStoresVM"
               }
            }
         });
   }
})();