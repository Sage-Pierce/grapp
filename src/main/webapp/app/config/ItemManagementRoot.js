(function() {
   "use strict";

   angular.module("App")
      .service("ItemManagementRoot", ItemManagementRoot);

   ItemManagementRoot.$inject = ["Root"];
   function ItemManagementRoot(Root) {
      var self = angular.extend(this, new Root("/"));

      ////////////////////
   }
})();