(function() {
   "use strict";

   angular.module("App")
      .service("StoreLayout", StoreLayout);

   StoreLayout.$inject = ["Root", "NodeType"];
   function StoreLayout(Root, NodeType) {
      var self = this;
      self.loadById = loadById;

      ////////////////////

      function loadById(storeLayoutId) {
         return Root.loadResourceModelById("layout", storeLayoutId, createModel);
      }

      function createModel(storeLayoutRsc) {
         return new StoreLayoutModel(storeLayoutRsc);
      }

      function StoreLayoutModel(storeLayoutRsc) {
         var self = this;
         self.getOuterOutline = getOuterOutline;
         self.getInnerOutline = getInnerOutline;
         self.getFeatureById = getFeatureById;
         self.getFeatures = getFeatures;
         self.addFeature = addFeature;
         self.removeFeatureById = removeFeatureById;
         self.getNodeById = getNodeById;
         self.getNodes = getNodes;
         self.addNode = addNode;
         self.removeNodeById = removeNodeById;

         var outerOutline = createPolygonModelFromPolygon({id: "outerOutline", polygon: storeLayoutRsc.outerOutline}, "outerOutline", false);
         var innerOutline = createPolygonModelFromPolygon({id: "innerOutline", polygon: storeLayoutRsc.innerOutline}, "innerOutline", false);
         var features = _.object(storeLayoutRsc.features.map(function(feature) { return [feature.id, createPolygonModelFromFeature(feature)]; }));
         var nodes = _.object(storeLayoutRsc.nodes.map(function(node) { return [node.id, createNodeModelFromNode(node)]; }));

         ////////////////////

         function getOuterOutline() {
            return outerOutline;
         }

         function getInnerOutline() {
            return innerOutline;
         }

         function getFeatureById(id) {
            return features[id];
         }

         function getFeatures() {
            return _.values(features);
         }

         function addFeature(vertices) {
            return storeLayoutRsc.$post("addFeature", {polygon: stringifyVerticesIntoPolygon(vertices)})
               .then(function(featureRsc) {
                  features[featureRsc.id] = createPolygonModelFromFeature(featureRsc);
                  return features[featureRsc.id];
               });
         }

         function removeFeatureById(id) {
            storeLayoutRsc.$del("removeFeature", {featureId: id})
               .then(function() { delete features[id]; });
         }

         function getNodeById(id) {
            return nodes[id];
         }

         function getNodes() {
            return _.values(nodes);
         }

         function addNode(nodeType, location) {
            return storeLayoutRsc.$post("addNode", {type: _.findKey(NodeType, nodeType), location: JSON.stringify(location)})
               .then(function(layoutNodeUpdateRsc) {
                  nodes[layoutNodeUpdateRsc.id] = createNodeModelFromNode(layoutNodeUpdateRsc);
                  return layoutNodeUpdateRsc.$get("affectedNodes")
                     .then(function(affectedNodesRsc) {
                        return {node: nodes[layoutNodeUpdateRsc.id], affectedNodes: _.arrayify(affectedNodesRsc).map(updateNodeModelFromNode)};
                     }, function() {
                        return {node: nodes[layoutNodeUpdateRsc.id]};
                     });
               });
         }

         function removeNodeById(id) {
            storeLayoutRsc.$del("removeNode", {nodeId: id})
               .then(function() { delete nodes[id]; });
         }

         function createPolygonModelFromFeature(featureRsc) {
            return createPolygonModelFromPolygon(featureRsc, "reshapeFeature", true);
         }

         function createPolygonModelFromPolygon(polygonRsc, updateRel, isFeature) {
            return {
               id: polygonRsc.id,
               vertices: polygonRsc.polygon ? polygonRsc.polygon.vertices : [],
               isFeature: isFeature,
               setVertices: function(vertices) { return commitPolygonModelVertices(updateRel, this, vertices); }
            };
         }

         function commitPolygonModelVertices(updateRel, polygonModel, vertices) {
            var params = {
               featureId: polygonModel.id,
               polygon: stringifyVerticesIntoPolygon(vertices)
            };
            return storeLayoutRsc.$put(updateRel, params)
               .then(function() { polygonModel.vertices = vertices; });
         }

         function stringifyVerticesIntoPolygon(polygonVertices) {
            return JSON.stringify({vertices: polygonVertices});
         }

         function createNodeModelFromNode(storeNode) {
            return {
               id: storeNode.id,
               name: storeNode.name,
               type: NodeType[storeNode.type],
               location: storeNode.location,
               items: _.object(storeNode.items.map(function(nodeItem) { return [nodeItem.id, createNodeItemModelFromNodeItem(nodeItem)]; })),
               setName: function(name) { commitNodeModelParams(this, {name: name}); },
               setLocation: function(position) { commitNodeModelPosition(this, position); },
               getItems: function() { return _.values(this.items); },
               addItem: function(code, name) { return addNodeItem(this, {code: code, name: name}); },
               removeItemById: function(itemId) { return removeNodeItemFromNodeById(this, itemId); }
            };
         }

         function addNodeItem(nodeModel, item) {
            return storeLayoutRsc.$post("addNodeItem", {nodeId: nodeModel.id, item: JSON.stringify(item)})
               .then(function(layoutNodeItemUpdateRsc) {
                  nodeModel.items[layoutNodeItemUpdateRsc.id] = createNodeItemModelFromNodeItem(layoutNodeItemUpdateRsc);
                  return layoutNodeItemUpdateRsc.$get("affectedNodes")
                     .then(function(affectedNodesRsc) {
                        return {item: nodeModel.items[layoutNodeItemUpdateRsc.id], affectedNodes: _.arrayify(affectedNodesRsc).map(updateNodeModelFromNode)};
                     }, function() {
                        return {item: nodeModel.items[layoutNodeItemUpdateRsc.id]};
                     });
               });
         }

         function commitNodeModelParams(nodeModel, params) {
            Root.updateResourceById("node", nodeModel.id, params)
               .then(function(nodeRsc) { nodeModel.name = nodeRsc.name; });
         }

         function commitNodeModelPosition(nodeModel, location) {
            var params = {
               nodeId: nodeModel.id,
               location: JSON.stringify(location)
            };
            return storeLayoutRsc.$put("moveNode", params)
               .then(function() { nodeModel.location = location; });
         }

         function updateNodeModelFromNode(node) {
            var nodeModel = getNodeById(node.id);
            nodeModel.type = NodeType[node.type];
            nodeModel.items = _.object(node.items.map(function(nodeItem) { return [nodeItem.id, createNodeItemModelFromNodeItem(nodeItem)]; }));
            return nodeModel;
         }

         function createNodeItemModelFromNodeItem(nodeItem) {
            return {
               id: nodeItem.id,
               code: nodeItem.item.code,
               name: nodeItem.item.name
            };
         }

         function removeNodeItemFromNodeById(node, itemId) {
            return Root.deleteResourceById("nodeItem", itemId)
               .then(function() {
                  delete node.items[itemId];
               });
         }
      }
   }
})();