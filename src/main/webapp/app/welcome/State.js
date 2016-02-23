(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
      $stateProvider
         .state("main", {
            url: "/",
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
            url: "welcome",
            views: {
               "content": {
                  templateUrl: "app/welcome/MainWelcome.html"
               }
            }
         });
   }
})();