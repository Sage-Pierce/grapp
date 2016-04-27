(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingList", ShoppingList);

   ShoppingList.$inject = ["Root"];
   function ShoppingList(Root) {
      var self = this;
      self.loadById = loadById;

      ////////////////////

      function loadById(id) {
         return Root.loadResourceModel("shoppingLists", id, createModel);
      }

      function createModel(shoppingList) {
         return new ShoppingListModel(shoppingList);
      }

      function ShoppingListModel(shoppingList) {
         var self = this;

         ////////////////////

      }
   }
})();