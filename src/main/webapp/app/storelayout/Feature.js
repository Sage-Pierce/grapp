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
         return _.mergeLeft(new FeatureModel(storeLayoutRsc, feature), feature);
      }

      function FeatureModel(storeLayoutRsc, feature) {
         var self = this;
         self.vertices = feature.polygon.vertices;
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
            return Root.deleteResource("feature", feature.id);
         }
      }
   }
})();