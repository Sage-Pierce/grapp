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
         return _.mergeLeft(new OutlineModel(storeLayoutRsc, outlineProp, outline), outline);
      }

      function OutlineModel(storeLayoutRsc, outlineProp, outline) {
         var self = this;
         self.id = outlineProp;
         self.vertices = outline.vertices || [];
         self.setVertices = setVertices;

         ////////////////////

         function setVertices(vertices) {
            return storeLayoutRsc.$request().$put(outlineProp, {polygon: _.stringifyVerticesIntoPolygon(vertices)})
               .then(function() { self.vertices = vertices; });
         }
      }
   }
})();