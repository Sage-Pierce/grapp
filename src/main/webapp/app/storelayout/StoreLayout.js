(function() {
   "use strict";

   angular.module("App")
      .service("StoreLayout", StoreLayout);

   StoreLayout.$inject = ["Root", "Outline", "Feature", "Node", "NodeType"];
   function StoreLayout(Root, Outline, Feature, Node, NodeType) {
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
         self.outerOutline = Outline.load(storeLayoutRsc, "outerOutline");
         self.innerOutline = Outline.load(storeLayoutRsc, "innerOutline");
         self.features = _.object(storeLayoutRsc.features.map(function(feature) { return [feature.id, Feature.load(storeLayoutRsc, feature)]; }));
         self.nodes = _.object(storeLayoutRsc.nodes.map(function(node) { return [node.id, Node.load(storeLayoutRsc, node)]; }));
         self.getFeatureById = function(id) { return self.features[id]; };
         self.getFeatures = function() { return _.values(self.features); };
         self.addFeature = addFeature;
         self.removeFeatureById = removeFeatureById;
         self.getNodeById = function(id) { return self.nodes[id]; };
         self.getNodes = function() { return _.values(self.nodes); };
         self.addNode = addNode;
         self.removeNodeById = removeNodeById;
         self.addNodeItem = addNodeItem;

         ////////////////////

         function addFeature(vertices) {
            return storeLayoutRsc.$post("addFeature", {polygon: _.stringifyVerticesIntoPolygon(vertices)})
               .then(function(featureRsc) {
                  self.features[featureRsc.id] = Feature.load(storeLayoutRsc, featureRsc);
                  return self.features[featureRsc.id];
               });
         }

         function removeFeatureById(id) {
            return self.features[id].delete()
               .then(function() { delete self.features[id]; });
         }

         function addNode(nodeType, location) {
            return storeLayoutRsc.$post("addNode", {type: _.findKey(NodeType, nodeType), location: JSON.stringify(location)})
               .then(function(result) {
                  self.nodes[result.id] = Node.load(storeLayoutRsc, result);
                  return result.$get("affectedNodes")
                     .then(function(affectedNodeRscs) {
                        return {node: self.nodes[result.id], affectedNodes: _.arrayify(affectedNodeRscs).map(updateNodeFromResource)};
                     }, function() {
                        return {node: self.nodes[result.id], affectedNodes: []};
                     });
               });
         }

         function removeNodeById(id) {
            return self.nodes[id].delete()
               .then(function() { delete self.nodes[id]; });
         }

         function addNodeItem(nodeId, item) {
            return self.nodes[nodeId].addItem(item)
               .then(function(result) {
                  return {item: result.item, affectedNodes: result.affectedNodeRscs.map(updateNodeFromResource)};
               });
         }

         function updateNodeFromResource(nodeRsc) {
            self.nodes[nodeRsc.id] = Node.load(storeLayoutRsc, nodeRsc);
            return self.nodes[nodeRsc.id];
         }
      }
   }
})();