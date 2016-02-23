(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
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