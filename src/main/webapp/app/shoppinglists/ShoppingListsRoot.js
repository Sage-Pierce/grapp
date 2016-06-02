(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingListsRoot", ShoppingListsRoot);

   ShoppingListsRoot.$inject = ["Root"];
   function ShoppingListsRoot(Root) {
      var self = angular.extend(this, new Root("/shoppingLists/root/"));
      self.loadShopperByEmail = loadShopperByEmail;

      ////////////////////

      function loadShopperByEmail(email) {
         return self.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("shoppers", {email: email});
         });
      }
   }
})();