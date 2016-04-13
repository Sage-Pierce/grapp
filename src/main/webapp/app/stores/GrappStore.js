(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappStore", GrappStore);

   GrappStore.$inject = ["Root"];
   function GrappStore(Root) {
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

      function loadById(grappStoreId) {
         return Root.loadResourceModelById("store", grappStoreId, createModel);
      }

      function load(grappStoreRsc) {
         return Root.mergeResourceIntoModel(grappStoreRsc, createModel(grappStoreRsc));
      }

      function createModel(grappStoreRsc) {
         return new GrappStoreModel(grappStoreRsc);
      }

      function GrappStoreModel(grappStoreRsc) {
         var self = this;
         self.commitAttributes = commitAttributes;

         ////////////////////

         function commitAttributes(attributes) {
            attributes.location = JSON.stringify(attributes.location);
            return grappStoreRsc.$put("self", attributes).then(function(resource) {
               self.name = resource.name;
               self.location = resource.location;
            });
         }
      }
   }
})();