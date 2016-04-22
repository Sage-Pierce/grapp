(function() {
   "use strict";

   angular.module("App")
      .service("Outline", Outline);

   Outline.$inject = ["Root"];
   function Outline(Root) {
      var self = this;
      self.load = load;

      ////////////////////

      function load(storeLayoutRsc, outlineProp) {
         var outline = storeLayoutRsc[outlineProp] || {};
         return Root.mergeResourceIntoModel(outline, createModel(storeLayoutRsc, outlineProp, outline));
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