(function() {
   "use strict";

   angular.module("Grapp")
      .service("Store", Store);

   Store.$inject = ["Root"];
   function Store(Root) {
      var self = this;
      self.create = create;
      self.loadAll = loadAll;
      self.loadById = loadById;
      self.load = load;

      ////////////////////

      function create(name, location) {
         return Root.createResourceModel("stores", {name: name, location: JSON.stringify(location)}, createModel);
      }

      function loadAll() {
         return Root.loadResourceModels("stores", createModel);
      }

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

         ////////////////////

         function commitAttributes(attributes) {
            attributes.location = JSON.stringify(attributes.location);
            return storeRsc.$put("self", attributes).then(function(resource) {
               self.name = resource.name;
               self.location = resource.location;
            });
         }
      }
   }
})();