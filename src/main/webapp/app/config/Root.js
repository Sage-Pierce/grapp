(function() {
   "use strict";

   angular.module("App")
      .service("Root", Root);

   Root.$inject = ["$q"];
   function Root($q) {
      var self = this;
      self.load = load;
      self.afterLoad = afterLoad;
      self.createResourceModel = createResourceModel;
      self.loadResourceModels = loadResourceModels;
      self.loadResourceModelById = loadResourceModelById;
      self.updateResourceById = updateResourceById;
      self.deleteResourceById = deleteResourceById;
      self.mergeResourceIntoModel = mergeResourceIntoModel;

      var deferred = $q.defer();

      ////////////////////

      function load(rootRsc) {
         deferred.resolve(rootRsc);
      }

      function createResourceModel(pluralResourceName, params, resourceModelCreatorCallback) {
         return afterLoad().then(function(rootRsc) {
            return rootRsc.$post(pluralResourceName, params).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback(resource));
            });
         });
      }

      function loadResourceModels(pluralResourceName, resourceModelCreatorCallback) {
         return afterLoad().then(function(rootRsc) {
            return rootRsc.$get(pluralResourceName).then(function(pluralResource) {
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

      function loadResourceModelById(resourceName, idParam, resourceModelCreatorCallback) {
         return afterLoad().then(function(rootRsc) {
            return rootRsc.$get(resourceName + "ById", _.isObject(idParam) ? idParam : {id: idParam})
               .then(function(resource) {
                  return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
               });
         });
      }

      function updateResourceById(resourceName, idParam, attributes) {
         return afterLoad().then(function(rootRsc) {
            return rootRsc.$put(resourceName + "ById", _.merge(attributes, _.isObject(idParam) ? idParam : {id: idParam}));
         });
      }

      function deleteResourceById(resourceName, idParam) {
         return afterLoad().then(function(rootRsc) {
            return rootRsc.$del(resourceName + "ById", _.isObject(idParam) ? idParam : {id: idParam});
         });
      }

      function afterLoad() {
         return deferred.promise;
      }

      function mergeResourceIntoModel(resource, model) {
         for (var prop in resource) {
            if (resource.hasOwnProperty(prop) && !model.hasOwnProperty(prop)) {
               model[prop] = resource[prop];
            }
         }
         return decorateResourceModel(resource, model);
      }

      function decorateResourceModel(resource, model) {
         model.delete = model.delete || resource.$del && function() {
            return resource.$del("self");
         };
         return model;
      }
   }
})();