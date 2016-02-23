(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappStoreLayout", GrappStoreLayout);

   GrappStoreLayout.$inject = ["GrappRoot"];
   function GrappStoreLayout(GrappRoot) {
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
         self.outerOutline = createGMapPolygonModelFromGrappPolygonRsc({id: "outerOutline", polygon: grappStoreLayoutRsc.outerOutline}, "outerOutline", false);
         self.innerOutline = createGMapPolygonModelFromGrappPolygonRsc({id: "innerOutline", polygon: grappStoreLayoutRsc.innerOutline}, "innerOutline", false);
         self.features = grappStoreLayoutRsc.features.map(createGMapPolygonModelFromGrappFeatureRsc);
         self.getFeatureById = getFeatureById;
         self.addFeature = addFeature;
         self.removeFeatureById = removeFeatureById;

         ////////////////////

         function getFeatureById(id) {
            return _.findWhere(self.features, {id: id});
         }

         function addFeature(path) {
            return grappStoreLayoutRsc.$post("addFeature", {polygon: stringifyVerticesIntoGrappPolygon(convertgMapPolygonPathToGrappPolygonVertices(path))})
               .then(function(featureRsc) {
                  var polygonModel = createGMapPolygonModelFromGrappFeatureRsc(featureRsc);
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

         function createGMapPolygonModelFromGrappFeatureRsc(featureRsc) {
            return createGMapPolygonModelFromGrappPolygonRsc(featureRsc, "reshapeFeature", true);
         }

         function createGMapPolygonModelFromGrappPolygonRsc(grappPolygonRsc, updateRel, isFeature) {
            return {
               id: grappPolygonRsc.id,
               vertices: grappPolygonRsc.polygon ? grappPolygonRsc.polygon.vertices : [],
               isFeature: isFeature,
               commitPath: function(path) { return commitGMapPolygonModelPath(updateRel, this, path); }
            };
         }

         function commitGMapPolygonModelPath(updateRel, gMapPolygonModel, path) {
            var grappPolygonVertices = convertgMapPolygonPathToGrappPolygonVertices(path);
            var params = {
               polygon: stringifyVerticesIntoGrappPolygon(grappPolygonVertices),
               featureID: gMapPolygonModel.id
            };
            return grappStoreLayoutRsc.$put(updateRel, params).then(function() {
               gMapPolygonModel.vertices = grappPolygonVertices;
            });
         }

         function stringifyVerticesIntoGrappPolygon(grappPolygonVertices) {
            return JSON.stringify({vertices: grappPolygonVertices});
         }

         function convertgMapPolygonPathToGrappPolygonVertices(path) {
            return path.map(function(latLng) { return {lat: latLng.latitude || latLng.lat(), lng: latLng.longitude || latLng.lng()}; });
         }
      }
   }
})();