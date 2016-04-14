(function() {
   "use strict";

   angular.module("Grapp")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.stores", {
            url: "stores",
            resolve: {
               user: ["Login", function(Login) {
                  return Login.afterLogIn();
               }]
            },
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