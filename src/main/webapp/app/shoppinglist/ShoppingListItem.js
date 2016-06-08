(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingListItem", ShoppingListItem);

   ShoppingListItem.$inject = ["ShoppingListsRoot"];
   function ShoppingListItem(ShoppingListsRoot) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(shoppingListItem) {
         return _.mergeLeft(new ShoppingListItemModel(shoppingListItem), shoppingListItem);
      }

      function ShoppingListItemModel(shoppingListItem) {
         var self = this;
         self.code = shoppingListItem.item.code;
         self.name = shoppingListItem.item.name;
         self.setAttributes = setAttributes;
         self.delete = del;

         ////////////////////

         function setAttributes(attributes) {
            return ShoppingListsRoot.updateResource("shoppingListItem", shoppingListItem.id, _.merge(attributes, self))
               .then(function(shoppingListItemRsc) {
                  self.obtained = shoppingListItemRsc.obtained;
                  return self;
               });
         }

         function del() {
            return ShoppingListsRoot.deleteResource("shoppingListItem", shoppingListItem.id);
         }
      }
   }
})();