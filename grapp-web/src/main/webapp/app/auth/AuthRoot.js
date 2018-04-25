(function() {
   "use strict";

   angular.module("App")
      .service("AuthRoot", AuthRoot);

   AuthRoot.$inject = ["$q", "$http", "$location"];
   function AuthRoot($q, $http, $location) {
      var self = this;
      self.loadFromServer = loadFromServer;
      self.authorize = authorize;
      self.reauthenticate = reauthenticate;
      self.afterLoad = afterLoad;

      var deferred = $q.defer();

      ////////////////////

      function loadFromServer(authServer) {
         deferred.resolve(authServer);
      }

      function authorize() {
         afterLoad().then(function(authHref) {
            $http({
               method: "POST",
               url: authHref + "/auth",
               params: {
                  appStateUri: $location.absUrl()
               }
            }).then(function(response) {
               window.location = response.headers("Location");
            });
         });
      }

      function reauthenticate(authorization) {
         return afterLoad().then(function(authHref) {
            return $http({
               method: "PUT",
               url: authHref + "/auth",
               headers: {
                  Authorization: authorization
               }
            });
         }).then(function(response) {
            return response.value;
         });
      }

      function afterLoad() {
         return deferred.promise;
      }
   }
})();