(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingListItem", ShoppingListItem);

   ShoppingListItem.$inject = ["Root"];
   function ShoppingListItem(Root) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(shoppingListItem) {
         return Root.mergeResourceIntoModel(shoppingListItem, createModel(shoppingListItem));
      }

      function createModel(shoppingListItem) {
         return new ShoppingListItemModel(shoppingListItem);
      }

      function ShoppingListItemModel(shoppingListItem) {
         var self = this;
         self.delete = del;

         ////////////////////

         function del() {
            return Root.deleteResource("shoppingListItem", shoppingListItem.id);
         }
      }
   }
})();