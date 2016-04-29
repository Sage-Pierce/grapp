(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingList", ShoppingList);

   ShoppingList.$inject = ["Root", "ShoppingListItem"];
   function ShoppingList(Root, ShoppingListItem) {
      var self = this;
      self.loadById = loadById;

      ////////////////////

      function loadById(id) {
         return Root.loadResourceModel("shoppingList", id, createModel);
      }

      function createModel(shoppingListRsc) {
         return new ShoppingListModel(shoppingListRsc);
      }

      function ShoppingListModel(shoppingListRsc) {
         var self = this;
         self.items = shoppingListRsc.items.map(ShoppingListItem.load);
         self.addItem = addItem;
         self.removeItem = removeItem;

         ////////////////////

         function addItem(item) {
            return shoppingListRsc.$post("addItem", {item: JSON.stringify(item)})
               .then(function(shoppingListItemRsc) {
                  self.items.push(ShoppingListItem.load(shoppingListItemRsc));
               });
         }

         function removeItem(item) {
            return item.delete().then(function() {
               _.remove(self.items, item);
            });
         }
      }
   }
})();