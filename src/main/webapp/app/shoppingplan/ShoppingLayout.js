(function() {
   "use strict";

   angular.module("App")
      .service("ShoppingLayout", ShoppingLayout);

   ShoppingLayout.$inject = ["Root", "ShoppingNode", "NodeType"];
   function ShoppingLayout(Root, ShoppingNode, NodeType) {
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
         self.features = _.fromPairs(shoppingLayoutRsc.features.map(function(feature) { return [feature.id, feature]; }));
         self.nodes = _.fromPairs(shoppingLayoutRsc.nodes.map(function(shoppingNode) { return [shoppingNode.id, ShoppingNode.load(shoppingNode)]; }));
         self.generateShoppingPath = generateShoppingPath;
         self.getFeatures = function() { return _.values(self.features); };
         self.getNodes = function() { return _.values(self.nodes); };

         ////////////////////

         function generateShoppingPath() {
            var nodesByType = _.groupBy(_.values(self.nodes), function(node) { return node.type.code; });
            var start = _.convertLocationToPoint(nodesByType[NodeType.ENTRANCE.code][0].location);
            var finish = _.convertLocationToPoint(nodesByType[NodeType.EXIT.code][0].location);
            var waypoints = nodesByType[NodeType.REGULAR.code].map(function(node) { return _.convertLocationToPoint(node.location); });
            var enclosure = _.convertGeoPolygonToPolygon(self.innerOutline);
            var polygons = _.values(self.features).map(function(feature) { return _.convertGeoPolygonToPolygon(feature.polygon); });
            return generatePath(start, finish, waypoints, enclosure, polygons);
         }

         function generatePath(start, finish, waypoints, enclosure, polygons) {
            return Root.afterLoad().then(function(rootRsc) {
               return rootRsc.$put("pathGeneration",
                                   {start: JSON.stringify(start), finish: JSON.stringify(finish), waypoints: JSON.stringify({points: waypoints})},
                                   JSON.stringify({enclosure: enclosure, polygons: polygons}))
                  .then(function(pathRsc) {
                     return _.convertPointsToPath(pathRsc.points);
                  });
            });
         }
      }
   }
})();