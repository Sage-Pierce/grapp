(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingListsRoot", ShoppingListsRoot);

   ShoppingListsRoot.$inject = ["Root"];
   function ShoppingListsRoot(Root) {
      var self = angular.extend(this, new Root("/"));

      ////////////////////
   }
})();