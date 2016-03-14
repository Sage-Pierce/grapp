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
         self.outerOutline = createPolygonModelFromPolygon({id: "outerOutline", polygon: grappStoreLayoutRsc.outerOutline}, "outerOutline", false);
         self.innerOutline = createPolygonModelFromPolygon({id: "innerOutline", polygon: grappStoreLayoutRsc.innerOutline}, "innerOutline", false);
         self.features = grappStoreLayoutRsc.features.map(createPolygonModelFromFeature);
         self.nodes = grappStoreLayoutRsc.nodes.map(createNodeModelFromNode);
         self.getFeatureById = getFeatureById;
         self.addFeature = addFeature;
         self.removeFeatureById = removeFeatureById;
         self.getNodeById = getNodeById;
         self.addNode = addNode;
         self.removeNodeById = removeNodeById;

         ////////////////////

         function getFeatureById(id) {
            return _.findWhere(self.features, {id: id});
         }

         function addFeature(vertices) {
            return grappStoreLayoutRsc.$post("addFeature", {polygon: stringifyVerticesIntoPolygon(vertices)})
               .then(function(featureRsc) {
                  var polygonModel = createPolygonModelFromFeature(featureRsc);
                  self.features.push(polygonModel);
                  return polygonModel;
               });
         }

         function removeFeatureById(id) {
            var featureModel = getFeatureById(id);
            if (featureModel) {
               grappStoreLayoutRsc.$del("removeFeature", {featureID: id}).then(function() {
                  self.features = _.without(self.features, featureModel);
               });
            }
         }

         function getNodeById(id) {
            return _.findWhere(self.nodes, {id: id});
         }

         function addNode(grappStoreNodeType, location) {
            return grappStoreLayoutRsc.$post("addNode", {type: grappStoreNodeType.code, location: JSON.stringify(location)})
               .then(function(layoutNodeUpdateRsc) {
                  var result = {node: createNodeModelFromNode(layoutNodeUpdateRsc)};
                  self.nodes.push(result.node);
                  return layoutNodeUpdateRsc.$has("affectedNodes") ? layoutNodeUpdateRsc.$get("affectedNodes")
                     .then(function(affectedNodesRsc) {
                        result.affectedNodes = _.arrayify(affectedNodesRsc).map(updateNodeModelFromNode);
                        return result;
                     }) : result;
               });
         }

         function removeNodeById(id) {
            var nodeModel = getNodeById(id);
            if (nodeModel) {
               grappStoreLayoutRsc.$del("removeNode", {nodeID: id}).then(function() {
                  self.nodes = _.without(self.nodes, nodeModel);
               });
            }
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
            return grappStoreLayoutRsc.$put(updateRel, params).then(function() {
               polygonModel.vertices = vertices;
            });
         }

         function stringifyVerticesIntoPolygon(grappPolygonVertices) {
            return JSON.stringify({vertices: grappPolygonVertices});
         }

         function createNodeModelFromNode(grappStoreNode) {
            return {
               id: grappStoreNode.id,
               name: grappStoreNode.name,
               type: GrappStoreNodeType.fromCode(grappStoreNode.type),
               location: grappStoreNode.location,
               commitName: function(name) { commitNodeModelParams(this, {name: name}); },
               commitLocation: function(position) { commitNodeModelPosition(this, position); }
            };
         }

         function updateNodeModelFromNode(node) {
            var nodeModel = getNodeById(node.id);
            nodeModel.type = GrappStoreNodeType.fromCode(node.type);
            return nodeModel;
         }

         function commitNodeModelParams(nodeModel, params) {
            GrappRoot.updateResourceByID("node", nodeModel.id, params)
               .then(function(nodeRsc) {
                  nodeModel.name = nodeRsc.name;
               });
         }

         function commitNodeModelPosition(nodeModel, location) {
            var params = {
               nodeID: nodeModel.id,
               location: JSON.stringify(location)
            };
            return grappStoreLayoutRsc.$put("moveNode", params).then(function() {
               nodeModel.location = location;
            });
         }
      }
   }
})();