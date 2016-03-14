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
      self.updateResourceByID = updateResourceByID;
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

      function createResourceModel(pluralResourceName, params, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$post(pluralResourceName, params)
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
            return grappRoot.$get(resourceName + "ById", {id: id}).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
            });
         });
      }

      function updateResourceByID(resourceName, id, params) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$put(resourceName + "ById", _.merge(params, {id: id})).then(function(resource) {
               return resource;
            });
         });
      }

      function mergeResourceIntoModel(resource, model) {
         for (var prop in resource) {
            if (resource.hasOwnProperty(prop) && !model.hasOwnProperty(prop)) {
               model[prop] = resource[prop];
            }
         }
         return decorateResourceModel(resource, model);
      }

      function afterLoad() {
         return self.deferred.promise;
      }

      function decorateResourceModel(resource, model) {
         model.delete = function() {
            return resource.$del("self");
         };
         return model;
      }
   }
})();