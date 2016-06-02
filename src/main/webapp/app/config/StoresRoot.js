(function() {
   "use strict";

   angular.module("App")
      .service("StoresRoot", StoresRoot);

   StoresRoot.$inject = ["Root"];
   function StoresRoot(Root) {
      var self = angular.extend(this, new Root("/"));

      ////////////////////
   }
})();