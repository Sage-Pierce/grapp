(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main.stores", {
            url: "stores",
            resolve: {
               storeManager: ["Login", "StoreManager", function(Login, StoreManager) {
                  return Login.afterLogIn().then(function(user) {
                     return StoreManager.loadByEmail(user.email);
                  });
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