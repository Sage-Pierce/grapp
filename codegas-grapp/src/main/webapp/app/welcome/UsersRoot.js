(function() {
   "use strict";

   angular.module("App")
      .service("UsersRoot", UsersRoot);

   UsersRoot.$inject = ["Root"];
   function UsersRoot(Root) {
      var self = angular.extend(this, new Root("/users/root/"));
      self.logIn = logIn;

      ////////////////////

      function logIn(args) {
         return self.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("logIn", args);
         });
      }
   }
})();