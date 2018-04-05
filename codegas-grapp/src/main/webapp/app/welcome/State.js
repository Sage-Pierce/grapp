(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
      $stateProvider
         .state("main", {
            abstract: true,
            views: {
               "main": {
                  templateUrl: "app/welcome/Main.html",
                  controller: "Main",
                  controllerAs: "mainVM"
               }
            }
         })
         .state("main.welcome", {
            url: "/",
            views: {
               "content": {
                  templateUrl: "app/welcome/MainWelcome.html"
               }
            }
         });
   }
})();