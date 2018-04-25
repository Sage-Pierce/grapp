(function() {
   "use strict";

   angular.module("App")
      .service("Node", Node);

   Node.$inject = ["$q", "StoresRoot", "NodeType"];
   function Node($q, StoresRoot, NodeType) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(storeLayoutRsc, node) {
         return _.mergeLeft(new NodeModel(storeLayoutRsc, node), node);
      }

      function NodeModel(storeLayoutRsc, node) {
         var self = this;
         self.type = NodeType[node.type];
         self.items = _.fromPairs(node.items.map(function(nodeItem) { return [nodeItem.id, createModelForNodeItem(nodeItem)]; }));
         self.setAttributes = setAttributes;
         self.setLocation = setLocation;
         self.getItems = function() { return _.values(self.items); };
         self.addItem = addItem;
         self.removeItemById = removeItemById;
         self.delete = del;

         ////////////////////

         function setAttributes(attributes) {
            return StoresRoot.updateResource("node", node.id, _.merge(attributes, self))
               .then(function(nodeRsc) {
                  self.name = nodeRsc.name;
                  return self;
               });
         }

         function setLocation(location) {
            return storeLayoutRsc.$request().$put("moveNode", {nodeId: node.id, location: JSON.stringify(location)})
               .then(function(nodeRsc) { self.location = nodeRsc.location; });
         }

         function addItem(item) {
            if (_.anyMatch(self.getItems(), function(nodeItem) { return nodeItem.code === item.code; })) {
               return $q.reject("Item already in Node.");
            }
            return storeLayoutRsc.$request().$post("addNodeItem", {nodeId: node.id, item: JSON.stringify(item)})
               .then(function(result) {
                  self.items[result.id] = createModelForNodeItem(result);
                  return result.$request().$get("affectedNodes")
                     .then(function(affectedNodeRscs) {
                        return {item: self.items[result.id], affectedNodeRscs: _.arrayify(affectedNodeRscs)};
                     }, function() {
                        return {item: self.items[result.id], affectedNodeRscs: []};
                     });
               });
         }

         function removeItemById(itemId) {
            return StoresRoot.deleteResource("nodeItem", itemId)
               .then(function() { delete self.items[itemId]; });
         }

         function del() {
            return StoresRoot.deleteResource("node", node.id);
         }

         function createModelForNodeItem(nodeItem) {
            return {
               id: nodeItem.id,
               code: nodeItem.item.code,
               name: nodeItem.item.name
            };
         }
      }
   }
})();