(function() {
   "use strict";

   angular.module("App")
      .service("Shopper", Shopper);

   Shopper.$inject = ["Root"];
   function Shopper(Root) {
      var self = this;
      self.loadByEmail = loadByEmail;

      ////////////////////

      function loadByEmail(email) {
         return Root.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("shoppers", {email: email}).then(
               function(shopperRsc) {
                  return _.mergeLeft(createModel(shopperRsc), shopperRsc);
               });
         });
      }

      function createModel(shopperRsc) {
         return new ShopperModel(shopperRsc);
      }

      function ShopperModel(shopperRsc) {
         var self = this;
         self.addList = addList;
         self.removeList = removeList;

         ////////////////////

         function addList(name) {
            return shopperRsc.$post("addList", {name: name})
               .then(function(shoppingListRsc) {
                  self.lists.push(shoppingListRsc);
               });
         }

         function removeList(list) {
            return Root.deleteResource("shoppingList", list.id)
               .then(function() {
                  _.remove(self.lists, list);
               });
         }
      }
   }
})();