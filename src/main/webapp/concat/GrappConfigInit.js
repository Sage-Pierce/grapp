(function() {
   "use strict";

   OAuth.initialize("lqAgua7gEL9Rf-DF-Qm1jxBst6g");

   angular.element(document.getElementsByTagName('head')).append(angular.element('<base href="' + window.location.pathname + '" />'));

   angular.module("Grapp", ["ngCookies", "ui.router", "angular-hal", "ui.bootstrap", "uiGmapgoogle-maps"])
      .config(grappConfig)
      .run(grappInit);

   grappConfig.$inject = ["$urlRouterProvider", "uiGmapGoogleMapApiProvider"];
   function grappConfig($urlRouterProvider, uiGmapGoogleMapApiProvider) {
      $urlRouterProvider.otherwise("/welcome");

      uiGmapGoogleMapApiProvider.configure({
         key: "AIzaSyBXdSdaWFJdrh1JBLK5lGXgQHB64Ip9esM",
         v: "3.22",
         libraries: "drawing,places"
      });
   }

   grappInit.$inject = ["$rootScope", "$state", "halClient", "GrappRoot", "uiGmapGoogleMapApi"];
   function grappInit($rootScope, $state, halClient, GrappRoot, uiGmapGoogleMapApi) {
      $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
         console.log("Error on state transition:");
         console.log(error);
         $state.go("main.welcome");
      });

      var servers = [
         "https://grapplication.herokuapp.com/rest/",
         "http://localhost:5000/rest/"
      ];
      halClient.$get(servers[1]).then(GrappRoot.load, console.log);

      uiGmapGoogleMapApi.then(function() {
         // Leaving this in as a hint to future-me if GMap behaves strangely
         // due to not being fully loaded before having attributes set
      });
   }
})();
