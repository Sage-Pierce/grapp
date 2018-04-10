(function() {
   "use strict";

   angular.module("App")
      .service("Auth", Auth)
      .run(autoLogIn);

   Auth.$inject = ["$location", "$cookies", "$q", "Config", "AuthRoot", "AuthUser", "StoresRoot", "ItemManagementRoot", "ShoppingListsRoot"];
   function Auth($location, $cookies, $q, Config, AuthRoot, AuthUser, StoresRoot, ItemManagementRoot, ShoppingListsRoot) {
      var self = this;
      self.autoLogIn = autoLogIn;
      self.logIn = logIn;
      self.logOut = logOut;
      self.afterLogIn = afterLogIn;
      self.isUserLoggedIn = isUserLoggedIn;

      var deferred = $q.defer();
      var userLoggedIn = false;

      ////////////////////

      function autoLogIn() {
         var queryParams = parseQueryParams(window.location.search);
         if (queryParams.code) {
            AuthRoot.logIn(getRedirectUri(), queryParams.code).then(authenticate, logInCachedUser);
         } else {
            logInCachedUser();
         }
      }

      function logIn() {
         AuthRoot.authorize(getRedirectUri());
      }

      function logOut() {
         afterLogIn().then(function(user) {
            user.logOut().then(function() {
               // Unload secured Resources
               StoresRoot.unload();
               ItemManagementRoot.unload();
               ShoppingListsRoot.unload();

               $cookies.remove("codegas-auth");
               deferred = $q.reject("NO USER LOGGED IN");
               userLoggedIn = false;
            });
         });
      }

      function afterLogIn() {
         return deferred.promise;
      }

      function isUserLoggedIn() {
         return userLoggedIn;
      }

      function parseQueryParams(search) {
         var params = {};
         var query = search.length > 0 && search.charAt(0) === "?" ? search.substring(1) : search;
         query.split("&").forEach(function(variable) {
            var pair = variable.split("=");
            params[pair[0]] = decodeURIComponent(pair[1]);
         });
         return params;
      }

      function getRedirectUri() {
         var url = $location.absUrl();
         var redirectEndingIndex = findRedirectEndingIndex(url);
         return url.substring(0, redirectEndingIndex < 0 ? url.length : redirectEndingIndex);
      }

      function findRedirectEndingIndex(url) {
         var fragmentIndex = url.indexOf("#");
         var queryIndex = url.indexOf("?");
         if (fragmentIndex < 0) {
            return queryIndex;
         } else if (queryIndex < 0) {
            return fragmentIndex;
         } else {
            return Math.min(fragmentIndex, queryIndex);
         }
      }

      function authenticate(codegasAuth) {
         return AuthUser.authenticate(codegasAuth)
            .then(function(user) { resolveUser(codegasAuth, user); }, logInCachedUser);
      }

      function logInCachedUser() {
         var codegasAuth = $cookies.get("codegas-auth");
         if (codegasAuth) {
            AuthUser.authenticate(codegasAuth).then(function(user) { resolveUser(codegasAuth, user); }, function() {
               deferred.reject("Problem authenticating User on Server.");
               $cookies.remove("codegas-auth");
            });
         }
         else {
            deferred.reject("NO CACHED USER.");
         }
      }

      function resolveUser(codegasAuth, user) {
         console.log(user);

         // Load secured Resources
         StoresRoot.loadFromServer(Config.getStoresServer(), codegasAuth);
         ItemManagementRoot.loadFromServer(Config.getItemManagementServer(), codegasAuth);
         ShoppingListsRoot.loadFromServer(Config.getShoppingListsServer(), codegasAuth);

         $cookies.put("codegas-auth", codegasAuth);
         deferred.resolve(user);
         userLoggedIn = true;
      }
   }

   autoLogIn.$inject = ["Auth"];
   function autoLogIn(Auth) {
      Auth.autoLogIn();
   }
})();