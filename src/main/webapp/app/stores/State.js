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
               grappUser: ["GrappLogIn", function(GrappLogIn) {
                  return GrappLogIn.afterLogIn();
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