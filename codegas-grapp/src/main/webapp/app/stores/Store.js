(function() {
   "use strict";

   angular.module("App")
      .service("Store", Store);

   Store.$inject = ["StoresRoot", "AddressLookup"];
   function Store(StoresRoot, AddressLookup) {
      var self = this;
      self.loadAll = loadAll;
      self.loadById = loadById;
      self.load = load;

      ////////////////////

      function loadAll() {
         return StoresRoot.loadResourceModels("stores", createModel);
      }

      function loadById(storeId) {
         return StoresRoot.loadResourceModel("store", storeId, createModel);
      }

      function load(store) {
         return _.mergeLeft(createModel(store), store);
      }

      function createModel(store) {
         return new StoreModel(store);
      }

      function StoreModel(store) {
         var self = this;
         self.approximateAddress = null;
         self.setAttributes = setAttributes;
         self.delete = del;

         initialize();

         ////////////////////

         function initialize() {
            performAddressLookup(store.location);
         }

         function setAttributes(attributes) {
            attributes.location = JSON.stringify(attributes.location || self.location);
            return StoresRoot.updateResource("store", store.id, _.merge(attributes, self)).then(function(storeRsc) {
               self.name = storeRsc.name;
               self.location = storeRsc.location;
               self.approximateAddress = attributes.approximateAddress || performAddressLookup(self.location);
               return self;
            });
         }

         function del() {
            return StoresRoot.deleteResource("store", store.id);
         }

         function performAddressLookup(location) {
            AddressLookup.lookupLocation(location).then(function(result) {
               self.approximateAddress = result;
            });
         }
      }
   }
})();