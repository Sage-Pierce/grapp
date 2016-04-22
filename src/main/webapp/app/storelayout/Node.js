(function() {
   "use strict";

   angular.module("App")
      .service("Node", Node);

   Node.$inject = ["Root", "NodeType"];
   function Node(Root, NodeType) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(storeLayoutRsc, node) {
         return Root.mergeResourceIntoModel(node, createModel(storeLayoutRsc, node));
      }

      function createModel(storeLayoutRsc, node) {
         return new NodeModel(storeLayoutRsc, node);
      }

      function NodeModel(storeLayoutRsc, node) {
         var self = this;
         self.type = NodeType[node.type];
         self.items = _.object(node.items.map(function(nodeItem) { return [nodeItem.id, createModelForNodeItem(nodeItem)]; }));
         self.setAttributes = setAttributes;
         self.setLocation = setLocation;
         self.getItems = function() { return _.values(self.items); };
         self.addItem = addItem;
         self.removeItemById = removeItemById;
         self.delete = del;

         ////////////////////

         function setAttributes(attributes) {
            return Root.updateResourceById("node", node.id, _.merge(attributes, self))
               .then(function() { self.name = name; });
         }

         function setLocation(location) {
            return storeLayoutRsc.$put("moveNode", {nodeId: node.id, location: JSON.stringify(location)})
               .then(function() { self.location = location; });
         }

         function addItem(item) {
            return storeLayoutRsc.$post("addNodeItem", {nodeId: node.id, item: JSON.stringify(item)})
               .then(function(result) {
                  self.items[result.id] = createModelForNodeItem(result);
                  return result.$get("affectedNodes")
                     .then(function(affectedNodeRscs) {
                        return {item: self.items[result.id], affectedNodeRscs: _.arrayify(affectedNodeRscs)};
                     }, function() {
                        return {item: self.items[result.id], affectedNodeRscs: []};
                     });
               });
         }

         function removeItemById(itemId) {
            return Root.deleteResourceById("nodeItem", itemId)
               .then(function() { delete self.items[itemId]; });
         }

         function del() {
            return Root.deleteResourceById("node", node.id);
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