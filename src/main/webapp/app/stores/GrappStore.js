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
         self.location = convertGrappPointTogMapPoint(grappStoreRsc.location);

         self.updateName = function(name) {
            return grappStoreRsc.$put("updateName", {name: name}).then(function(resource) {
               self.name = resource.name;
            });
         };

         self.updateLocation = function(gMapPoint) {
            return grappStoreRsc.$put("updateLocation", {location: JSON.stringify(convertgMapPointToGrappPoint(gMapPoint))}).then(function(resource) {
               self.location = convertGrappPointTogMapPoint(resource.location);
            });
         };

         self.delete = function() {
            return grappStoreRsc.$del("self");
         };

         function convertGrappPointTogMapPoint(grappPoint) {
            return grappPoint ? {latitude: grappPoint.lat, longitude: grappPoint.lng} : null;
         }

         function convertgMapPointToGrappPoint(gMapPoint) {
            return {lat: gMapPoint.latitude || gMapPoint.lat(), lng: gMapPoint.longitude || gMapPoint.lng()};
         }
      }
   }
})();