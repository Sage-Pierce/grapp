(function() {
   "use strict";

   angular.module("App")
      .service("ItemManagementRoot", ItemManagementRoot);

   ItemManagementRoot.$inject = ["Root"];
   function ItemManagementRoot(Root) {
      var self = angular.extend(this, new Root("/itemManagement/root/"));
      self.importItems = importItems;

      ////////////////////

      function importItems(type, data) {
         return self.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("importItems", {type: type}, data, {headers: {"Content-Type": "text/plain"}});
         });
      }
   }
})();