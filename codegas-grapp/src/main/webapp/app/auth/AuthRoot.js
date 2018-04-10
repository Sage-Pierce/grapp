(function() {
   "use strict";

   angular.module("App")
      .service("AuthRoot", AuthRoot);

   AuthRoot.$inject = ["$q", "$http"];
   function AuthRoot($q, $http) {
      var self = this;
      self.loadFromServer = loadFromServer;
      self.authorize = authorize;
      self.logIn = logIn;
      self.afterLoad = afterLoad;

      var deferred = $q.defer();

      ////////////////////

      function loadFromServer(authServer) {
         deferred.resolve(authServer);
      }

      function authorize(redirectUri) {
         afterLoad().then(function(authHref) {
            $http({
               method: "POST",
               url: authHref + "/auth",
               params: {
                  redirectUri: redirectUri
               }
            }).then(function(response) {
               window.location = response.headers("Location");
            });
         });
      }

      function logIn(redirectUri, authCode) {
         return afterLoad().then(function(authHref) {
            return $http({
               method: "PUT",
               url: authHref + "/auth",
               params: {
                  redirectUri: redirectUri,
                  authCode: authCode
               }
            });
         }).then(function(response) {
            return response.data.value;
         });
      }

      function afterLoad() {
         return deferred.promise;
      }
   }
})();