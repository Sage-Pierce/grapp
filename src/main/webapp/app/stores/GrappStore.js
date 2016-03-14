(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappStore", GrappStore);

   GrappStore.$inject = ["GrappRoot"];
   function GrappStore(GrappRoot) {
      var self = this;
      self.create = create;
      self.loadAll = loadAll;
      self.loadByID = loadByID;
      self.load = load;

      ////////////////////

      function create(name, location) {
         return GrappRoot.createResourceModel("stores", {name: name, location: JSON.stringify(location)}, createModel);
      }

      function loadAll() {
         return GrappRoot.loadResourceModels("stores", createModel);
      }

      function loadByID(grappStoreID) {
         return GrappRoot.loadResourceModelByID("store", grappStoreID, createModel);
      }

      function load(grappStoreRsc) {
         return GrappRoot.mergeResourceIntoModel(grappStoreRsc, createModel(grappStoreRsc));
      }

      function createModel(grappStoreRsc) {
         return new GrappStoreModel(grappStoreRsc);
      }

      function GrappStoreModel(grappStoreRsc) {
         var self = this;
         self.commitAttributes = commitAttributes;

         ////////////////////

         function commitAttributes(attributes) {
            return grappStoreRsc.$put("self", attributes)
               .then(function(resource) {
                  self.name = resource.name;
                  self.location = resource.location;
               });
         }
      }
   }
})();