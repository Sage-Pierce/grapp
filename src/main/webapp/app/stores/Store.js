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
         return Root.loadResourceModel("store", storeId, createModel);
      }

      function load(store) {
         return _.mergeLeft(createModel(store), store);
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
            attributes.location = JSON.stringify(attributes.location || self.location);
            return Root.updateResource("store", store.id, _.merge(attributes, self)).then(function(storeRsc) {
               self.name = storeRsc.name;
               self.location = storeRsc.location;
            });
         }

         function del() {
            return Root.deleteResource("store", store.id);
         }
      }
   }
})();