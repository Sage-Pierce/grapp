(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingNode", ShoppingNode);

   ShoppingNode.$inject = ["NodeType", "ShoppingItemType"];
   function ShoppingNode(NodeType, ShoppingItemType) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(shoppingNode) {
         return _.mergeLeft(new ShoppingNodeModel(shoppingNode), shoppingNode);
      }

      function ShoppingNodeModel(shoppingNode) {
         var self = this;
         self.type = NodeType[shoppingNode.type];
         self.items = _.object(shoppingNode.items.map(function(shoppingItem) { return [shoppingItem.id, createModelForShoppingItem(shoppingItem)]; }));

         ////////////////////

         function createModelForShoppingItem(shoppingItem) {
            return {
               item: shoppingItem.item,
               type: ShoppingItemType[shoppingItem.type]
            };
         }
      }
   }
})();