(function() {
   "use strict";

   angular.module("App")
      .service("Login", Login)
      .run(logInCachedUser);

   Login.$inject = ["$cookies", "$q", "Root", "User"];
   function Login($cookies, $q, Root, User) {
      var self = this;
      self.logInCachedUser = logInCachedUser;
      self.logInWithOAuth = logInWithOAuth;
      self.afterLogIn = afterLogIn;
      self.logOut = logOut;
      self.isUserLoggedIn = isUserLoggedIn;

      var deferred = $q.defer();
      var userLoggedIn = false;

      ////////////////////

      function logInCachedUser() {
         var userEmail = $cookies.get("user-email");
         if (userEmail) {
            User.loadByEmail(userEmail).then(resolveUser, function() {
               deferred.reject("Problem logging User in on Server.");
            });
         }
         else {
            deferred.reject("NO CACHED USER.");
         }
      }

      function logInWithOAuth(oAuthProvider) {
         deferred = $q.defer();
         OAuth.popup(oAuthProvider).done(function(oauthResponse) {
            oauthResponse.me().done(function(me) {
               return logIn(me.email, me.avatar).then(resolveUser);
            });
         }).fail(function(oauthError) {
            deferred.reject(oauthError);
         });
         return deferred.promise;
      }

      function afterLogIn() {
         return deferred.promise;
      }

      function logOut() {
         $cookies.remove("user-email");
         deferred = $q.defer();
         deferred.reject("NO USER LOGGED IN");
         userLoggedIn = false;
      }

      function isUserLoggedIn() {
         return userLoggedIn;
      }

      function logIn(email, avatar) {
         return Root.afterLoad().then(function(root) {
            return root.$put("logIn", {email: email, avatar: avatar}).then(cacheUser).then(User.load);
         });
      }

      function cacheUser(userRsc) {
         $cookies.put("user-email", userRsc.email);
         return userRsc;
      }

      function resolveUser(userRsc) {
         deferred.resolve(userRsc);
         userLoggedIn = true;
      }
   }

   logInCachedUser.$inject = ["Login"];
   function logInCachedUser(Login) {
      Login.logInCachedUser();
   }
})();