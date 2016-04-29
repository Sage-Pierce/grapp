(function() {
   "use strict";

   angular.module("App")
      .service("ItemHierarchy", ItemHierarchy);

   ItemHierarchy.$inject = ["Root"];
   function ItemHierarchy(Root) {
      var self = this;
      self.loadAll = loadAll;

      ////////////////////

      function loadAll() {
         return Root.loadResourceModels("items", createModel);
      }

      function createModel(itemHierarchy) {
         return new ItemHierarchyModel(itemHierarchy);
      }

      function ItemHierarchyModel(itemHierarchy) {
         var self = this;
         self.lineage = convertHierarchyToLineage(itemHierarchy.hierarchy);

         ////////////////////

         function convertHierarchyToLineage(hierarchy) {
            var reverseHierarchy = hierarchy.slice(Math.min(1, hierarchy.length)).reverse();
            return reverseHierarchy.length > 0 ? reverseHierarchy.map(function(ancestor) { return ancestor.name; }).join(" > ") : "General Item";
         }
      }
   }
})();