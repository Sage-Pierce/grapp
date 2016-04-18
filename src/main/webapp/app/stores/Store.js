(function() {
   "use strict";

   angular.module("App")
      .service("Store", Store);

   Store.$inject = ["Root"];
   function Store(Root) {
      var self = this;
      self.loadById = loadById;
      self.load = load;

      ////////////////////

      function loadById(storeId) {
         return Root.loadResourceModelById("store", storeId, createModel);
      }

      function load(storeRsc) {
         return Root.mergeResourceIntoModel(storeRsc, createModel(storeRsc));
      }

      function createModel(storeRsc) {
         return new StoreModel(storeRsc);
      }

      function StoreModel(storeRsc) {
         var self = this;
         self.commitAttributes = commitAttributes;
         self.delete = del;

         ////////////////////

         function commitAttributes(attributes) {
            attributes.location = JSON.stringify(attributes.location);
            return Root.updateResourceById("store", storeRsc.id, attributes).then(function(resource) {
               self.name = resource.name;
               self.location = resource.location;
            });
         }

         function del() {
            return Root.deleteResourceById("store", storeRsc.id);
         }
      }
   }
})();