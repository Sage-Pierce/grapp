(function() {
   "use strict";

   angular.module("App")
      .service("StoresRoot", StoresRoot);

   StoresRoot.$inject = ["Root"];
   function StoresRoot(Root) {
      var self = angular.extend(this, new Root("/stores/root/"));
      self.loadManager = loadManager;

      ////////////////////

      function loadManager() {
         return self.afterLoad().then(function(rootRsc) {
            return rootRsc.$get("storeManager").then(
               function(response) { return response; },
               function(response) {
                  return response.status === 404 ? rootRsc.$post("storeManager") : response;
               });
         });
      }
   }
})();