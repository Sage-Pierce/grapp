(function() {
   "use strict";

   angular.module("App")
      .service("StoresRoot", StoresRoot);

   StoresRoot.$inject = ["Root"];
   function StoresRoot(Root) {
      var self = angular.extend(this, new Root("/stores/root/"));
      self.loadManagerByEmail = loadManagerByEmail;

      ////////////////////

      function loadManagerByEmail(email) {
         return self.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("storeManagers", {email: email});
         });
      }
   }
})();