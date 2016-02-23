(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappLogIn", GrappLogIn)
      .run(logInCachedUser);

   GrappLogIn.$inject = ["$cookies", "$q", "GrappRoot", "GrappUser"];
   function GrappLogIn($cookies, $q, GrappRoot, GrappUser) {
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
         var grappUserID = $cookies.get("grapp-user-id");
         if (grappUserID) {
            GrappUser.loadByID(grappUserID).then(resolveUser, function() {
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
         return GrappRoot.logIn({email: email, avatar: avatar}).then(cacheUser).then(GrappUser.load);
      }

      function cacheUser(grappUser) {
         $cookies.put("grapp-user-id", grappUser.id);
         return grappUser;
      }

      function resolveUser(grappUser) {
         deferred.resolve(grappUser);
         userLoggedIn = true;
      }
   }

   logInCachedUser.$inject = ["GrappLogIn"];
   function logInCachedUser(GrappLogIn) {
      GrappLogIn.logInCachedUser();
   }
})();