(function() {
   "use strict";

   angular.module("App")
      .service("ItemLineage", ItemLineage);

   ItemLineage.$inject = ["ItemManagementRoot"];
   function ItemLineage(ItemManagementRoot) {
      var self = this;
      self.loadAll = loadAll;

      ////////////////////

      function loadAll() {
         return ItemManagementRoot.loadResourceModels("items", createModel);
      }

      function createModel(itemLineage) {
         return new ItemLineageModel(itemLineage);
      }

      function ItemLineageModel(itemLineage) {
         var self = this;
         self.hierarchyDescriptor = createHierarchyDescriptorForLineage(itemLineage.lineage);

         ////////////////////

         function createHierarchyDescriptorForLineage(lineage) {
            var hierarchy = lineage.slice(Math.min(1, lineage.length)).reverse();
            return hierarchy.length > 0 ? hierarchy.map(function(member) { return member.name; }).join(" > ") : "General Item";
         }
      }
   }
})();