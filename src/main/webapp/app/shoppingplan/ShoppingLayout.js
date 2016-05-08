(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingLayout", ShoppingLayout);

   ShoppingLayout.$inject = ["Root", "ShoppingNode"];
   function ShoppingLayout(Root, ShoppingNode) {
      var self = this;
      self.loadByIdForItems = loadByIdForItems;

      ////////////////////

      function loadByIdForItems(id, items) {
         return Root.loadResourceModel("shoppingLayout", {id: id, shoppingList: JSON.stringify({items: items})}, createModel);
      }

      function createModel(shoppingLayoutRsc) {
         return new ShoppingLayoutModel(shoppingLayoutRsc);
      }

      function ShoppingLayoutModel(shoppingLayoutRsc) {
         var self = this;
         self.outerOutline = _.mergeLeft(shoppingLayoutRsc.outerOutline, {id: "outerOutline"});
         self.innerOutline = _.mergeLeft(shoppingLayoutRsc.innerOutline, {id: "innerOutline"});
         self.features = _.object(shoppingLayoutRsc.features.map(function(feature) { return [feature.id, feature]; }));
         self.nodes = _.object(shoppingLayoutRsc.nodes.map(function(shoppingNode) { return [shoppingNode.id, ShoppingNode.load(shoppingNode)]; }));
         self.getFeatures = function() { return _.values(self.features); };
         self.getNodes = function() { return _.values(self.nodes); };

         ////////////////////

      }
   }
})();