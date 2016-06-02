(function() {
   "use strict";

   angular.module("App")
      .service("ItemLineage", ItemLineage);

   ItemLineage.$inject = ["StoresRoot"];
   function ItemLineage(StoresRoot) {
      var self = this;
      self.loadAll = loadAll;

      ////////////////////

      function loadAll() {
         return StoresRoot.loadResourceModels("items", createModel);
      }

      function createModel(itemLineage) {
         return new ItemLineageModel(itemLineage);
      }

      function ItemLineageModel(itemLineage) {
         var self = this;
         self.hierarchy = convertLineageToHierarchyDescriptor(itemLineage.lineage);

         ////////////////////

         function convertLineageToHierarchyDescriptor(lineage) {
            var hierarchy = lineage.slice(Math.min(1, lineage.length)).reverse();
            return hierarchy.length > 0 ? hierarchy.map(function(member) { return member.name; }).join(" > ") : "General Item";
         }
      }
   }
})();