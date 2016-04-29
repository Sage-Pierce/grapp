(function() {
   "use strict";

   angular.module("App")
      .service("Outline", Outline);

   Outline.$inject = [];
   function Outline() {
      var self = this;
      self.load = load;

      ////////////////////

      function load(storeLayoutRsc, outlineProp) {
         var outline = storeLayoutRsc[outlineProp] || {};
         return _.mergeLeft(createModel(storeLayoutRsc, outlineProp, outline), outline);
      }

      function createModel(storeLayoutRsc, outlineProp, outline) {
         return new OutlineModel(storeLayoutRsc, outlineProp, outline);
      }

      function OutlineModel(storeLayoutRsc, outlineProp, outline) {
         var self = this;
         self.id = outlineProp;
         self.vertices = outline.vertices ? outline.vertices : [];
         self.setVertices = setVertices;

         ////////////////////

         function setVertices(vertices) {
            return storeLayoutRsc.$put(outlineProp, {polygon: _.stringifyVerticesIntoPolygon(vertices)})
               .then(function() { self.vertices = vertices; });
         }
      }
   }
})();