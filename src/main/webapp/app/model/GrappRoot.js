(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappRoot", GrappRoot);

   GrappRoot.$inject = ["$q"];
   function GrappRoot($q) {
      var self = this;
      self.load = load;
      self.logIn = logIn;
      self.createResourceModel = createResourceModel;
      self.loadResourceModels = loadResourceModels;
      self.loadResourceModelByID = loadResourceModelByID;
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

      function createResourceModel(resourceName, params, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$post("create" + resourceName.charAt(0).toUpperCase() + resourceName.slice(1), params)
               .then(function(resource) {
                  return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
               });
         });
      }

      function loadResourceModels(pluralResourceName, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$get(pluralResourceName).then(function(pluralResource) {
               return pluralResource.$has(pluralResourceName) ? pluralResource.$get(pluralResourceName).then(function(resources) {
                  var arrayedResources = Array.isArray(resources) ? resources : [resources];
                  return arrayedResources.map(function(resource) {
                     return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
                  });
               }) : $q.resolve([]);
            });
         });
      }

      function loadResourceModelByID(resourceName, id, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$get(resourceName + "ByID", {id: id}).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
            });
         });
      }

      function mergeResourceIntoModel(resource, model) {
         for (var prop in resource) {
            if (resource.hasOwnProperty(prop) && !model.hasOwnProperty(prop)) {
               model[prop] = resource[prop];
            }
         }
         return model;
      }

      function afterLoad() {
         return self.deferred.promise;
      }
   }
})();