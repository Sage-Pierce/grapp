(function () {
   "use strict";

   angular.module("App", ["angular-hal", "ngCookies", "ngMaterial", "ngAnimate", "cgBusy", "ui.router", "ui.bootstrap", "ui.tree", "uiGmapgoogle-maps"])
      .constant("Config", new Config())
      .config(configure)
      .run(initialize);

   var servers = [
      "https://grapplication.herokuapp.com/rest", // When running on Heroku
      "http://localhost:8008/rest",               // When running Heroku locally (Need to set PORT Environment Var.)
      "http://localhost:8080/rest"                // When running the server manually
   ];
   var defaultServer = servers[0];

   function Config() {
      var self = this;
      self.getAuthServer = getAuthServer;
      self.getItemManagementServer = getItemManagementServer;
      self.getStoresServer = getStoresServer;
      self.getShoppingListsServer = getShoppingListsServer;
      self.getPathGenerationServer = getPathGenerationServer;

      ////////////////////

      function getAuthServer() {
         return defaultServer;
      }

      function getItemManagementServer() {
         return defaultServer;
      }

      function getStoresServer() {
         return defaultServer;
      }

      function getShoppingListsServer() {
         return defaultServer;
      }

      function getPathGenerationServer() {
         return defaultServer;
      }
   }

   configure.$inject = ["$urlRouterProvider", "uiGmapGoogleMapApiProvider"];
   function configure($urlRouterProvider, uiGmapGoogleMapApiProvider) {
      // Necessary to make UI Router work in certain environments
      angular.element(document.getElementsByTagName("head")).append(angular.element("<base href='" + window.location.pathname + "'/>"));

      $urlRouterProvider.otherwise("/welcome");

      uiGmapGoogleMapApiProvider.configure({
         key: "AIzaSyBXdSdaWFJdrh1JBLK5lGXgQHB64Ip9esM",
         v: "3.31",
         libraries: "drawing,places"
      });
   }

   initialize.$inject = ["$rootScope", "$state", "Config", "AuthRoot", "PathGenerationRoot", "uiGmapGoogleMapApi"];
   function initialize($rootScope, $state, Config, AuthRoot, PathGenerationRoot, uiGmapGoogleMapApi) {
      $rootScope.$on("$stateChangeError", function (event, toState, toParams, fromState, fromParams, error) {
         console.log("Error on state transition:");
         console.log(error);
         $state.go("main.welcome");
      });

      // Load unsecured Resources
      AuthRoot.loadFromServer(Config.getAuthServer());
      PathGenerationRoot.loadFromServer(Config.getPathGenerationServer());

      // Leaving this in as a hint to future-me if GMap behaves strangely due to not being fully loaded before having attributes set
      uiGmapGoogleMapApi.then(function() {});
   }
})();
