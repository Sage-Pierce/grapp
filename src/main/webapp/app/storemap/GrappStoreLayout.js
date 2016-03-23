(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappStoreLayout", GrappStoreLayout);

   GrappStoreLayout.$inject = ["GrappRoot", "GrappStoreNodeType"];
   function GrappStoreLayout(GrappRoot, GrappStoreNodeType) {
      var self = this;
      self.loadByID = loadByID;

      ////////////////////

      function loadByID(grappStoreLayoutID) {
         return GrappRoot.loadResourceModelByID("storeLayout", grappStoreLayoutID, createModel);
      }

      function createModel(grappStoreLayoutRsc) {
         return new GrappStoreLayoutModel(grappStoreLayoutRsc);
      }

      function GrappStoreLayoutModel(grappStoreLayoutRsc) {
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

         var outerOutline = createPolygonModelFromPolygon({id: "outerOutline", polygon: grappStoreLayoutRsc.outerOutline}, "outerOutline", false);
         var innerOutline = createPolygonModelFromPolygon({id: "innerOutline", polygon: grappStoreLayoutRsc.innerOutline}, "innerOutline", false);
         var features = _.object(grappStoreLayoutRsc.features.map(function(feature) { return [feature.id, createPolygonModelFromFeature(feature)]; }));
         var nodes = _.object(grappStoreLayoutRsc.nodes.map(function(node) { return [node.id, createNodeModelFromNode(node)]; }));

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
            return grappStoreLayoutRsc.$post("addFeature", {polygon: stringifyVerticesIntoPolygon(vertices)})
               .then(function(featureRsc) {
                  features[featureRsc.id] = createPolygonModelFromFeature(featureRsc);
                  return features[featureRsc.id];
               });
         }

         function removeFeatureById(id) {
            grappStoreLayoutRsc.$del("removeFeature", {featureID: id})
               .then(function() { delete features[id]; });
         }

         function getNodeById(id) {
            return nodes[id];
         }

         function getNodes() {
            return _.values(nodes);
         }

         function addNode(grappStoreNodeType, location) {
            return grappStoreLayoutRsc.$post("addNode", {type: _.findKey(GrappStoreNodeType, grappStoreNodeType), location: JSON.stringify(location)})
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
            grappStoreLayoutRsc.$del("removeNode", {nodeID: id})
               .then(function() { delete nodes[id]; });
         }

         function createPolygonModelFromFeature(featureRsc) {
            return createPolygonModelFromPolygon(featureRsc, "reshapeFeature", true);
         }

         function createPolygonModelFromPolygon(grappPolygonRsc, updateRel, isFeature) {
            return {
               id: grappPolygonRsc.id,
               vertices: grappPolygonRsc.polygon ? grappPolygonRsc.polygon.vertices : [],
               isFeature: isFeature,
               commitVertices: function(vertices) { return commitPolygonModelVertices(updateRel, this, vertices); }
            };
         }

         function commitPolygonModelVertices(updateRel, polygonModel, vertices) {
            var params = {
               featureID: polygonModel.id,
               polygon: stringifyVerticesIntoPolygon(vertices)
            };
            return grappStoreLayoutRsc.$put(updateRel, params)
               .then(function() { polygonModel.vertices = vertices; });
         }

         function stringifyVerticesIntoPolygon(grappPolygonVertices) {
            return JSON.stringify({vertices: grappPolygonVertices});
         }

         function createNodeModelFromNode(grappStoreNode) {
            return {
               id: grappStoreNode.id,
               name: grappStoreNode.name,
               type: GrappStoreNodeType[grappStoreNode.type],
               location: grappStoreNode.location,
               commitName: function(name) { commitNodeModelParams(this, {name: name}); },
               commitLocation: function(position) { commitNodeModelPosition(this, position); }
            };
         }

         function updateNodeModelFromNode(node) {
            var nodeModel = getNodeById(node.id);
            nodeModel.type = GrappStoreNodeType[node.type];
            return nodeModel;
         }

         function commitNodeModelParams(nodeModel, params) {
            GrappRoot.updateResourceByID("node", nodeModel.id, params)
               .then(function(nodeRsc) { nodeModel.name = nodeRsc.name; });
         }

         function commitNodeModelPosition(nodeModel, location) {
            var params = {
               nodeID: nodeModel.id,
               location: JSON.stringify(location)
            };
            return grappStoreLayoutRsc.$put("moveNode", params)
               .then(function() { nodeModel.location = location; });
         }
      }
   }
})();