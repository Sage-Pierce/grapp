(function() {
   "use strict";

   angular.module("App")
      .service("Shopper", Shopper);

   Shopper.$inject = ["Login", "ShoppingListsRoot"];
   function Shopper(Login, ShoppingListsRoot) {
      var self = this;
      self.load = load;

      ////////////////////

      function load() {
         return Login.afterLogIn().then(function(user) {
            return ShoppingListsRoot.loadShopperByEmail(user.email).then(createModel);
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