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
         self.location = convertGrappPointToGMapPosition(grappStoreRsc.location);
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

         function updateLocation(position) {
            return grappStoreRsc.$put("updateLocation", {location: JSON.stringify(convertGMapPositionToGrappPoint(position))})
               .then(function(resource) {
                  self.location = convertGrappPointToGMapPosition(resource.location);
               });
         }

         function remove() {
            return grappStoreRsc.$del("self");
         }

         function convertGrappPointToGMapPosition(point) {
            return point ? {latitude: point.lat, longitude: point.lng} : null;
         }

         function convertGMapPositionToGrappPoint(position) {
            return {lat: position.latitude || position.lat(), lng: position.longitude || position.lng()};
         }
      }
   }
})();