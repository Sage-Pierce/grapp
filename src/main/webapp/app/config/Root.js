(function() {
   "use strict";

   angular.module("App")
      .factory("Root", Root);

   Root.$inject = ["$q", "halClient"];
   function Root($q, halClient) {
      return function(apiRootRef) {
         var self = this;
         self.loadFromServer = loadFromServer;
         self.afterLoad = afterLoad;
         self.createResourceModel = createResourceModel;
         self.loadResourceModels = loadResourceModels;
         self.loadResourceModel = loadResourceModel;
         self.updateResource = updateResource;
         self.deleteResource = deleteResource;

         var deferred = $q.defer();

         ////////////////////

         function loadFromServer(serverHref) {
            halClient.$get(apiRootRef || "/", { transformUrl: function(href) { return serverHref + href; } }).then(deferred.resolve, console.log);
         }

         function createResourceModel(pluralResourceName, params, resourceModelCreatorCallback) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$post(pluralResourceName, params).then(function(resource) {
                  return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
               });
            });
         }

         function loadResourceModels(pluralResourceName, resourceModelCreatorCallback) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$get(pluralResourceName).then(function(pluralResource) {
                  return pluralResource.$has(pluralResourceName) ? createModelsForPluralResource(pluralResource, pluralResourceName, resourceModelCreatorCallback)
                     : convertResourcesToModels(pluralResource.values || [], resourceModelCreatorCallback);
               });
            });
         }

         function loadResourceModel(resourceName, idParam, resourceModelCreatorCallback) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$get(resourceName, _.isObject(idParam) ? idParam : {id: idParam})
                  .then(function(resource) {
                     return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
                  });
            });
         }

         function updateResource(resourceName, idParam, attributes) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$put(resourceName, _.merge(attributes, _.isObject(idParam) ? idParam : {id: idParam}));
            });
         }

         function deleteResource(resourceName, idParam) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$del(resourceName, _.isObject(idParam) ? idParam : {id: idParam});
            });
         }

         function afterLoad() {
            return deferred.promise;
         }

         function createModelsForPluralResource(pluralResource, pluralResourceName, resourceModelCreatorCallback) {
            return pluralResource.$get(pluralResourceName)
               .then(function(resources) {
                  return convertResourcesToModels(resources, resourceModelCreatorCallback);
               });
         }

         function convertResourcesToModels(resources, resourceModelCreatorCallback) {
            return _.arrayify(resources).map(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
            });
         }

         function mergeResourceIntoModel(resource, model) {
            return decorateResourceModel(resource, _.mergeLeft(model, resource));
         }

         function decorateResourceModel(resource, model) {
            model.delete = model.delete || resource.$del && function() { return resource.$del("self"); };
            return model;
         }
      };
   }
})();