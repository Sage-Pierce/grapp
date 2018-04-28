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

         function loadFromServer(serverHref, headers) {
            return $http({
               method: "GET",
               url: serverHref + apiRootRef,
               headers: headers || {}
            }).then(resourceDecorator(headers)).then(deferred.resolve, console.log);
         }

         function unload() {
            deferred = $q.defer();
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
            return loadResource(resourceName, idParam).then(function(resource) {
               return mergeResourceIntoModel(resource, resourceModelCreatorCallback ? resourceModelCreatorCallback(resource) : {});
            });
         }

         function loadResource(resourceName, idParam) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$get(resourceName, _.isObject(idParam) ? idParam : {id: idParam});
            });
         }

         function updateResource(resourceName, idParam, attributes) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$put(resourceName, _.merge(attributes, _.isObject(idParam) ? idParam : {id: idParam}));
            });
         }

         function deleteResource(resourceName, idParam) {
            return afterLoad().then(function(rootRsc) {
               return rootRsc.$delete(resourceName, _.isObject(idParam) ? idParam : {id: idParam});
            });
         }

         function afterLoad() {
            return deferred.promise;
         }

         // Directly define HTTP operations on Resources, propagating all original request headers
         // (i.e. Authorization) to subsequent Requests made on those Resources
         function resourceDecorator(requestHeaders) {
            return function(rsc) {
               rsc.$post = function() {
                  arguments.length = Math.max(4, arguments.length);
                  arguments[3] = _.mergeLeft({headers: requestHeaders}, arguments[3] || {});
                  return rsc.$request().$post.apply(undefined, arguments)
                     .then(resourceDecorator(requestHeaders));
               };
               rsc.$get = function() {
                  arguments.length = Math.max(3, arguments.length);
                  arguments[2] = _.mergeLeft({headers: requestHeaders}, arguments[2] || {});
                  return rsc.$request().$get.apply(undefined, arguments)
                     .then(resourceDecorator(requestHeaders));
               };
               rsc.$put = function() {
                  arguments.length = Math.max(4, arguments.length);
                  arguments[3] = _.mergeLeft({headers: requestHeaders}, arguments[3] || {});
                  return rsc.$request().$put.apply(undefined, arguments)
                     .then(resourceDecorator(requestHeaders));
               };
               rsc.$delete = function() {
                  arguments.length = Math.max(3, arguments.length);
                  arguments[2] = _.mergeLeft({headers: requestHeaders}, arguments[2] || {});
                  return rsc.$request().$delete.apply(undefined, arguments)
                     .then(resourceDecorator(requestHeaders));
               };
               return rsc;
            };
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
            model.delete = model.delete || resource.$delete && function() { return resource.$delete("self"); };
            return model;
         }
      };
   }
})();