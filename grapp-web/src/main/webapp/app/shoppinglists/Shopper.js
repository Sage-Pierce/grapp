(function() {
   "use strict";

   angular.module("App")
      .service("Shopper", Shopper);

   Shopper.$inject = ["Auth", "ShoppingListsRoot"];
   function Shopper(Auth, ShoppingListsRoot) {
      var self = this;
      self.load = load;

      ////////////////////

      function load() {
         return Auth.afterLogIn().then(function(user) {
            return ShoppingListsRoot.loadShopperByEmail(user.getEmail()).then(createModel);
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
            return shopperRsc.$request().$post("addList", {name: name})
               .then(function(shoppingListRsc) { self.lists.push(shoppingListRsc); });
         }

         function removeList(list) {
            return ShoppingListsRoot.deleteResource("shoppingList", list.id)
               .then(function() { _.remove(self.lists, list); });
         }
      }
   }
})();