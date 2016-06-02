(function() {
   "use strict";

   angular.module("App")
      .service("Shopper", Shopper);

   Shopper.$inject = ["ShoppingListsRoot"];
   function Shopper(ShoppingListsRoot) {
      var self = this;
      self.loadByEmail = loadByEmail;

      ////////////////////

      function loadByEmail(email) {
         return ShoppingListsRoot.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("shoppers", {email: email}).then(createModel);
         });
      }

      function createModel(shopperRsc) {
         return new ShopperModel(shopperRsc);
      }

      function ShopperModel(shopperRsc) {
         var self = this;
         self.lists = shopperRsc.lists;
         self.addList = addList;
         self.removeList = removeList;

         ////////////////////

         function addList(name) {
            return shopperRsc.$post("addList", {name: name})
               .then(function(shoppingListRsc) { self.lists.push(shoppingListRsc); });
         }

         function removeList(list) {
            return ShoppingListsRoot.deleteResource("shoppingList", list.id)
               .then(function() { _.remove(self.lists, list); });
         }
      }
   }
})();