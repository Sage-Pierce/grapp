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
         return _.mergeLeft(createModel(shoppingListItem), shoppingListItem);
      }

      function createModel(shoppingListItem) {
         return new ShoppingListItemModel(shoppingListItem);
      }

      function ShoppingListItemModel(shoppingListItem) {
         var self = this;
         self.code = shoppingListItem.item.code;
         self.name = shoppingListItem.item.name;
         self.delete = del;

         ////////////////////

         function del() {
            return Root.deleteResource("shoppingListItem", shoppingListItem.id);
         }
      }
   }
})();