(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappRoot", GrappRoot);

   GrappRoot.$inject = ["$q"];
   function GrappRoot($q) {
      var self = this;
      self.load = load;
      self.logIn = logIn;
      self.loadResourceModelByID = loadResourceModelByID;
      self.afterLoad = afterLoad;
      self.mergeResourceIntoModel = mergeResourceIntoModel;

      self.deferred = $q.defer();

      ////////////////////

      function load(grappRootRsc) {
         self.deferred.resolve(grappRootRsc);
      }

      function logIn(parameters) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$put("logIn", parameters);
         });
      }

      function loadResourceModelByID(resourceName, id, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$get(resourceName + "ByID", {id: id}).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
            });
         });
      }

      function afterLoad() {
         return self.deferred.promise;
      }

      function mergeResourceIntoModel(resource, model) {
         for (var prop in resource) {
            if (resource.hasOwnProperty(prop) && !model.hasOwnProperty(prop)) {
               model[prop] = resource[prop];
            }
         }
         return model;
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappUser", GrappUser);

   GrappUser.$inject = ["$q", "GrappRoot", "GrappStore"];
   function GrappUser($q, GrappRoot, GrappStore) {
      var self = this;
      self.loadByID = loadByID;
      self.load = load;

      ////////////////////

      function loadByID(grappUserID) {
         return GrappRoot.loadResourceModelByID("user", grappUserID, createModel);
      }

      function load(grappUserRsc) {
         return GrappRoot.mergeResourceIntoModel(grappUserRsc, createModel(grappUserRsc));
      }

      function createModel(grappUserRsc) {
         return new GrappUserModel(grappUserRsc);
      }

      function GrappUserModel(grappUserRsc) {
         var self = this;

         self.updateDisplayName = function(displayName) {
            return grappUserRsc.$put("updateName", {name: displayName}).then(function() {
               self.name = displayName;
            });
         };

         self.createStore = function(storeName, gMapPoint) {
            return grappUserRsc.$post("createStore", {storeName: storeName, storeLocation: JSON.stringify(convertgMapPointToGrappPoint(gMapPoint))}).then(GrappStore.load);
         };

         self.loadStores = function() {
            return grappUserRsc.$get("getStores").then(function(response) {
               return response.$has("stores") ? createGrappStoreModelsPromiseFromResponse(response) : $q.resolve([]);
            });
         };

         function convertgMapPointToGrappPoint(gMapPoint) {
            return {lat: gMapPoint.latitude || gMapPoint.lat(), lng: gMapPoint.longitude || gMapPoint.lng()};
         }

         function createGrappStoreModelsPromiseFromResponse(response) {
            return response.$get("stores").then(function(resources) {
               var storeResources = Array.isArray(resources) ? resources : [resources];
               return storeResources.map(function(storeResource) {
                  return GrappStore.load(storeResource);
               });
            });
         }
      }
   }
})();