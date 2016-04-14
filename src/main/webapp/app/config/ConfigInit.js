(function() {
   "use strict";

   OAuth.initialize("lqAgua7gEL9Rf-DF-Qm1jxBst6g");

   angular.element(document.getElementsByTagName('head')).append(angular.element('<base href="' + window.location.pathname + '" />'));

   angular.module("App", ["ngCookies", "ui.router", "angular-hal", "ui.bootstrap", "uiGmapgoogle-maps", "ui.tree"])
      .config(configure)
      .run(initialize);

   configure.$inject = ["$urlRouterProvider", "uiGmapGoogleMapApiProvider"];
   function configure($urlRouterProvider, uiGmapGoogleMapApiProvider) {
      $urlRouterProvider.otherwise("/welcome");

      uiGmapGoogleMapApiProvider.configure({
         key: "AIzaSyBXdSdaWFJdrh1JBLK5lGXgQHB64Ip9esM",
         v: "3.22",
         libraries: "drawing,places"
      });
   }

   initialize.$inject = ["$rootScope", "$state", "halClient", "Root", "uiGmapGoogleMapApi"];
   function initialize($rootScope, $state, halClient, Root, uiGmapGoogleMapApi) {
      $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
         console.log("Error on state transition:");
         console.log(error);
         $state.go("main.welcome");
      });

      var servers = [
         "https://grapplication.herokuapp.com/rest/", // When running on Heroku
         "http://localhost:5000/rest/",               // When running Heroku locally
         "http://localhost:8008/rest/"                // When running the server manually (Need to set PORT Environment Var.)
      ];
      halClient.$get(servers[0]).then(Root.load, console.log);

      uiGmapGoogleMapApi.then(function() {
         // Leaving this in as a hint to future-me if GMap behaves strangely
         // due to not being fully loaded before having attributes set
      });
   }
})();
