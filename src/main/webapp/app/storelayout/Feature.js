(function() {
   "use strict";

   angular.module("App")
      .service("Feature", Feature);

   Feature.$inject = ["Root"];
   function Feature(Root) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(storeLayoutRsc, feature) {
         return Root.mergeResourceIntoModel(feature, createModel(storeLayoutRsc, feature));
      }

      function createModel(feature) {
         return new FeatureModel(feature);
      }

      function FeatureModel(storeLayoutRsc, feature) {
         var self = this;
         self.setVertices = setVertices;
         self.delete = del;

         ////////////////////

         function setVertices(vertices) {
            return storeLayoutRsc.$put("reshapeFeature", {featureId: feature.id, polygon: _.stringifyVerticesIntoPolygon(vertices)})
               .then(function() {
                  self.vertices = vertices;
               });
         }

         function del() {
            return Root.deleteResourceById("feature", feature.id);
         }
      }
   }
})();