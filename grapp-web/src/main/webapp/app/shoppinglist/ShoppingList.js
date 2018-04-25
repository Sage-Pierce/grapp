(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingList", ShoppingList);

   ShoppingList.$inject = ["ShoppingListsRoot", "ShoppingListItem"];
   function ShoppingList(ShoppingListsRoot, ShoppingListItem) {
      var self = this;
      self.loadById = loadById;

      ////////////////////

      function loadById(id) {
         return ShoppingListsRoot.loadResourceModel("shoppingList", id, createModel);
      }

      function createModel(shoppingListRsc) {
         return new ShoppingListModel(shoppingListRsc);
      }

      function ShoppingListModel(shoppingListRsc) {
         var self = this;
         self.items = shoppingListRsc.items.map(ShoppingListItem.load);
         self.isEmpty = function() { return self.items.length <= 0; };
         self.hasUnobtainedItems = hasUnobtainedItems;
         self.getItems = function() { return self.items; };
         self.addItem = addItem;
         self.removeItem = removeItem;

         ////////////////////

         function hasUnobtainedItems() {
            return !_.isUndefined(_.find(self.items, function(item) { return !item.obtained; }));
         }

         function addItem(item) {
            if (!_.anyMatch(self.items, function(shoppingListItem) { return shoppingListItem.code === item.code; })) {
               return shoppingListRsc.$request().$post("addItem", {item: JSON.stringify(item)})
                  .then(function(shoppingListItemRsc) {
                     self.items.push(ShoppingListItem.load(shoppingListItemRsc));
                  });
            }
         }

         function removeItem(item) {
            return item.delete().then(function() {
               _.remove(self.items, item);
            });
         }
      }
   }
})();