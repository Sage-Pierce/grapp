(function() {
   "use strict";

   angular.module("Grapp")
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
         var userId = $cookies.get("grapp-user-id");
         if (userId) {
            User.loadById(userId).then(resolveUser, function() {
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
         $cookies.remove("grapp-user-id");
         deferred = $q.defer();
         deferred.reject("NO USER LOGGED IN");
         userLoggedIn = false;
      }

      function isUserLoggedIn() {
         return userLoggedIn;
      }

      function logIn(email, avatar) {
         return Root.afterLoad().then(function(grappRoot) {
            return grappRoot.$put("logIn", {email: email, avatar: avatar}).then(cacheUser).then(User.load);
         });
      }

      function cacheUser(user) {
         $cookies.put("grapp-user-id", user.id);
         return user;
      }

      function resolveUser(user) {
         deferred.resolve(user);
         userLoggedIn = true;
      }
   }

   logInCachedUser.$inject = ["Login"];
   function logInCachedUser(Login) {
      Login.logInCachedUser();
   }
})();