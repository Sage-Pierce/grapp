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