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
         self.outerOutline = createGMapPolygonModelFromGrappPolygon({id: "outerOutline", polygon: grappStoreLayoutRsc.outerOutline}, "outerOutline", false);
         self.innerOutline = createGMapPolygonModelFromGrappPolygon({id: "innerOutline", polygon: grappStoreLayoutRsc.innerOutline}, "innerOutline", false);
         self.features = grappStoreLayoutRsc.features.map(createGMapPolygonModelFromGrappFeature);
         self.nodes = grappStoreLayoutRsc.nodes.map(createGMapMarkerModelFromGrappStoreNode);
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

         function addFeature(path) {
            return grappStoreLayoutRsc.$post("addFeature", {polygon: stringifyVerticesIntoGrappPolygon(path.map(convertGMapPositionToGrappLocation))})
               .then(function(featureRsc) {
                  var polygonModel = createGMapPolygonModelFromGrappFeature(featureRsc);
                  self.features.push(polygonModel);
                  return polygonModel;
               });
         }

         function removeFeatureById(id) {
            var indexOfFeature = _.findIndex(self.features, function(feature) { return feature.id === id; });
            if (indexOfFeature >= 0) {
               grappStoreLayoutRsc.$del("removeFeature", {featureID: id}).then(function() {
                  self.features.splice(indexOfFeature, 1);
               });
            }
         }

         function getNodeById(id) {
            return _.findWhere(self.nodes, {id: id});
         }

         function addNode(grappStoreNodeType, position) {
            return grappStoreLayoutRsc.$post("addNode", {type: grappStoreNodeType.code, location: JSON.stringify(convertGMapPositionToGrappLocation(position))})
               .then(function(nodeRsc) {
                  var nodeModel = createGMapMarkerModelFromGrappStoreNode(nodeRsc);
                  self.nodes.push(nodeModel);
                  return nodeModel;
               });
         }

         function removeNodeById(id) {
            var indexOfNode = _.findIndex(self.nodes, function(node) { return node.id === id; });
            if (indexOfNode >= 0) {
               grappStoreLayoutRsc.$del("removeNode", {nodeID: id}).then(function() {
                  self.nodes.splice(indexOfNode, 1);
               });
            }
         }

         function createGMapPolygonModelFromGrappFeature(featureRsc) {
            return createGMapPolygonModelFromGrappPolygon(featureRsc, "reshapeFeature", true);
         }

         function createGMapPolygonModelFromGrappPolygon(grappPolygonRsc, updateRel, isFeature) {
            return {
               id: grappPolygonRsc.id,
               vertices: grappPolygonRsc.polygon ? grappPolygonRsc.polygon.vertices : [],
               isFeature: isFeature,
               commitPath: function(path) { return commitGMapPolygonModelPath(updateRel, this, path); }
            };
         }

         function commitGMapPolygonModelPath(updateRel, gMapPolygonModel, path) {
            var grappPolygonVertices = path.map(convertGMapPositionToGrappLocation);
            var params = {
               featureID: gMapPolygonModel.id,
               polygon: stringifyVerticesIntoGrappPolygon(grappPolygonVertices)
            };
            return grappStoreLayoutRsc.$put(updateRel, params).then(function() {
               gMapPolygonModel.vertices = grappPolygonVertices;
            });
         }

         function stringifyVerticesIntoGrappPolygon(grappPolygonVertices) {
            return JSON.stringify({vertices: grappPolygonVertices});
         }

         function createGMapMarkerModelFromGrappStoreNode(grappStoreNodeRsc) {
            return {
               id: grappStoreNodeRsc.id,
               location: grappStoreNodeRsc.location,
               type: GrappStoreNodeType.fromCode(grappStoreNodeRsc.type),
               commitLocation: function(position) { commitGMapMarkerModelPosition(this, position); }
            };
         }

         function commitGMapMarkerModelPosition(gMapMarkerModel, position) {
            var grappLocation = convertGMapPositionToGrappLocation(position);
            var params = {
               nodeID: gMapMarkerModel.id,
               location: JSON.stringify(grappLocation)
            };
            return grappStoreLayoutRsc.$put("moveNode", params).then(function() {
               gMapMarkerModel.location = grappLocation;
            });
         }

         function convertGMapPositionToGrappLocation(position) {
            return {lat: position.latitude || position.lat(), lng: position.longitude || position.lng()};
         }
      }
   }
})();