(function() {
   "use strict";

   angular.module("App")
      .service("Auth", Auth)
      .run(autoLogIn);

   Auth.$inject = ["$cookies", "$q", "$http", "Config", "AuthUser", "StoresRoot", "ItemManagementRoot", "ShoppingListsRoot"];
   function Auth($cookies, $q, $http, Config, AuthUser, StoresRoot, ItemManagementRoot, ShoppingListsRoot) {
      var self = this;
      self.autoLogIn = autoLogIn;
      self.logIn = logIn;
      self.logOut = logOut;
      self.afterLogIn = afterLogIn;
      self.isUserLoggedIn = isUserLoggedIn;

      var deferred = $q.defer();
      var userLoggedIn = false;

      ////////////////////

      function autoLogIn(queryParams) {
         var codegasAuth = $cookies.get("codegas-auth");
         if (queryParams.auth) {
            authenticate(atob(queryParams.auth));
         } else if (codegasAuth) {
            reauthenticate(codegasAuth);
         } else {
            deferred.reject("NO CACHED AUTHENTICATED USER.");
         }
      }

      function logIn() {
         AuthUser.authorize();
      }

      function logOut() {
         afterLogIn().then(function(user) {
            user.logOut().then(unresolve);
         });
      }

      function afterLogIn() {
         return deferred.promise;
      }

      function isUserLoggedIn() {
         return userLoggedIn;
      }

      function authenticate(codegasAuth) {
         AuthUser.authenticate(codegasAuth).then(function(user) {
            resolve(codegasAuth, user);
         });
      }

      function reauthenticate(codegasAuth) {
         AuthUser.reauthenticate(codegasAuth).then(function(user) {
            resolve(codegasAuth, user);
         }, function() {
            deferred.reject("Problem reauthenticating User on Server.");
            $cookies.remove("codegas-auth");
         });
      }

      function resolve(codegasAuth, user) {
         // Load secured Resources
         StoresRoot.loadFromServer(Config.getStoresServer(), {Authorization: codegasAuth});
         ItemManagementRoot.loadFromServer(Config.getItemManagementServer(), {Authorization: codegasAuth});
         ShoppingListsRoot.loadFromServer(Config.getShoppingListsServer(), {Authorization: codegasAuth});

         $cookies.put("codegas-auth", codegasAuth);
         deferred.resolve(user);
         userLoggedIn = true;
      }

      function unresolve() {
         // Unload secured Resources
         StoresRoot.unload();
         ItemManagementRoot.unload();
         ShoppingListsRoot.unload();

         $cookies.remove("codegas-auth");
         deferred = $q.reject("NO USER LOGGED IN");
         userLoggedIn = false;
      }
   }

   autoLogIn.$inject = ["$location", "Auth"];
   function autoLogIn($location, Auth) {
      Auth.autoLogIn($location.search());
   }
})();