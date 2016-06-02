(function() {
   "use strict";

   angular.module("App")
      .service("UsersRoot", UsersRoot);

   UsersRoot.$inject = ["Root"];
   function UsersRoot(Root) {
      var self = angular.extend(this, new Root("/"));

      ////////////////////
   }
})();