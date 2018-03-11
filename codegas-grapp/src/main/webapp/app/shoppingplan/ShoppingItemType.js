(function() {
   "use strict";

   angular.module("App")
      .constant("ShoppingItemType", createEnum());

   function createEnum() {
      return {
         EXPLICIT: new ShoppingItemType(),
         IMPLICIT: new ShoppingItemType()
      };
   }

   function ShoppingItemType() {

   }
})();