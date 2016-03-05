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
         self.updateDisplayName = updateDisplayName;
         self.createStore = createStore;
         self.loadStores = loadStores;

         ////////////////////

         function updateDisplayName(displayName) {
            return grappUserRsc.$put("updateName", {name: displayName}).then(function() {
               self.name = displayName;
            });
         }

         function createStore(storeName, location) {
            return grappUserRsc.$post("createStore", {storeName: storeName, storeLocation: JSON.stringify(location)})
               .then(GrappStore.load);
         }

         function loadStores() {
            return grappUserRsc.$get("getStores").then(function(response) {
               return response.$has("stores") ? createGrappStoreModelsPromiseFromResponse(response) : $q.resolve([]);
            });
         }

         function createGrappStoreModelsPromiseFromResponse(response) {
            return response.$get("stores").then(function(resources) {
               var storeResources = Array.isArray(resources) ? resources : [resources];
               return storeResources.map(GrappStore.load);
            });
         }
      }
   }
})();