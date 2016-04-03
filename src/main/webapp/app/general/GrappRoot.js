(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappRoot", GrappRoot);

   GrappRoot.$inject = ["$q"];
   function GrappRoot($q) {
      var self = this;
      self.load = load;
      self.afterLoad = afterLoad;
      self.createResourceModel = createResourceModel;
      self.loadResourceModels = loadResourceModels;
      self.loadResourceModelByID = loadResourceModelByID;
      self.updateResourceByID = updateResourceByID;
      self.mergeResourceIntoModel = mergeResourceIntoModel;
      self.deleteResourceByID = deleteResourceByID;

      var deferred = $q.defer();

      ////////////////////

      function load(grappRootRsc) {
         deferred.resolve(grappRootRsc);
      }

      function createResourceModel(pluralResourceName, params, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$post(pluralResourceName, params).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
            });
         });
      }

      function loadResourceModels(pluralResourceName, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$get(pluralResourceName).then(function(pluralResource) {
               return pluralResource.$get(pluralResourceName)
                  .then(function(resources) {
                     return _.arrayify(resources).map(function(resource) {
                        return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
                     });
                  }, function() {
                     return [];
                  });
            });
         });
      }

      function loadResourceModelByID(resourceName, id, resourceModelCreatorCallback) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$get(resourceName + "ById", {id: id})
               .then(function(resource) {
                  return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
               });
         });
      }

      function updateResourceByID(resourceName, id, params) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$put(resourceName + "ById", _.merge(params, {id: id}));
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

      function deleteResourceByID(resourceName, id) {
         return afterLoad().then(function(grappRoot) {
            return grappRoot.$del(resourceName + "ById", {id: id});
         });
      }

      function afterLoad() {
         return deferred.promise;
      }

      function decorateResourceModel(resource, model) {
         model.delete = model.delete || resource.$del && function() {
            return resource.$del("self");
         };
         return model;
      }
   }
})();