(function() {
   "use strict";

   angular.module("App")
      .factory("Root", Root);

   Root.$inject = ["$q", "$http"];
   function Root($q, $http) {
      return function(apiRootRef) {
         var self = this;
         self.loadFromServer = loadFromServer;
         self.unload = unload;
         self.afterLoad = afterLoad;
         self.createResourceModel = createResourceModel;
         self.loadResourceModels = loadResourceModels;
         self.loadResourceModel = loadResourceModel;
         self.loadResource = loadResource;
         self.updateResource = updateResource;
         self.deleteResource = deleteResource;

         var deferred = $q.defer();

         ////////////////////

         function loadFromServer(serverHref) {
            return $http({
               method: "GET",
               url: serverHref + apiRootRef
            }).then(deferred.resolve, console.log);
            // $http.$get(apiRootRef || "/root/", { transformUrl: _.urlTransformer(serverHref) });
         }

         function unload() {
            deferred = $q.defer();
         }

         function createResourceModel(pluralResourceName, params, resourceModelCreatorCallback) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$request().$post(pluralResourceName, params).then(function(resource) {
                  return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
               });
            });
         }

         function loadResourceModels(pluralResourceName, resourceModelCreatorCallback) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$request().$get(pluralResourceName).then(function(pluralResource) {
                  return pluralResource.$has(pluralResourceName) ? createModelsForPluralResource(pluralResource, pluralResourceName, resourceModelCreatorCallback)
                                                                 : convertResourcesToModels(pluralResource.values || [], resourceModelCreatorCallback);
               });
            });
         }

         function loadResourceModel(resourceName, idParam, resourceModelCreatorCallback) {
            return loadResource(resourceName, idParam).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
            });
         }

         function loadResource(resourceName, idParam) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$request().$get(resourceName, _.isObject(idParam) ? idParam : {id: idParam});
            });
         }

         function updateResource(resourceName, idParam, attributes) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$request().$put(resourceName, _.merge(attributes, _.isObject(idParam) ? idParam : {id: idParam}));
            });
         }

         function deleteResource(resourceName, idParam) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$request().$delete(resourceName, _.isObject(idParam) ? idParam : {id: idParam});
            });
         }

         function afterLoad() {
            return deferred.promise;
         }

         function createModelsForPluralResource(pluralResource, pluralResourceName, resourceModelCreatorCallback) {
            return pluralResource.$request().$get(pluralResourceName)
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
            model.delete = model.delete || resource.$request().$delete && function() { return resource.$request().$delete("self"); };
            return model;
         }
      };
   }
})();