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

      function load(store) {
         return Root.mergeResourceIntoModel(store, createModel(store));
      }

      function createModel(store) {
         return new StoreModel(store);
      }

      function StoreModel(store) {
         var self = this;
         self.setAttributes = setAttributes;
         self.delete = del;

         ////////////////////

         function setAttributes(attributes) {
            attributes.location = attributes.location && JSON.stringify(attributes.location);
            return Root.updateResourceById("store", store.id, attributes).then(function(storeRsc) {
               self.name = storeRsc.name;
               self.location = storeRsc.location;
            });
         }

         function del() {
            return Root.deleteResourceById("store", store.id);
         }
      }
   }
})();