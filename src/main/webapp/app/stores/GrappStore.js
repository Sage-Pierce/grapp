(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappStore", GrappStore);

   GrappStore.$inject = ["GrappRoot"];
   function GrappStore(GrappRoot) {
      var self = this;
      self.loadByID = loadByID;
      self.load = load;

      ////////////////////

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
         self.location = grappStoreRsc.location;
         self.updateName = updateName;
         self.updateLocation = updateLocation;
         self.remove = remove;

         ////////////////////

         function updateName(name) {
            return grappStoreRsc.$put("updateName", {name: name})
               .then(function(resource) {
                  self.name = resource.name;
               });
         }

         function updateLocation(location) {
            return grappStoreRsc.$put("updateLocation", {location: JSON.stringify(location)})
               .then(function(resource) {
                  self.location = resource.location;
               });
         }

         function remove() {
            return grappStoreRsc.$del("self");
         }
      }
   }
})();