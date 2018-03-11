(function() {
   "use strict";

   angular.module("App")
      .service("StoreManager", StoreManager);

   StoreManager.$inject = ["Login", "StoresRoot", "Store"];
   function StoreManager(Login, StoresRoot, Store) {
      var self = this;
      self.load = load;

      ////////////////////

      function load() {
         return Login.afterLogIn().then(function(user) {
            return StoresRoot.loadManagerByEmail(user.email).then(createModel);
         });
      }

      function createModel(storeManagerRsc) {
         return new StoreManagerModel(storeManagerRsc);
      }

      function StoreManagerModel(storeManagerRsc) {
         var self = this;
         self.stores = storeManagerRsc.stores.map(Store.load);
         self.addStore = addStore;

         ////////////////////

         function addStore(name, location) {
            return storeManagerRsc.$post("addStore", {name: name, location: JSON.stringify(location)})
               .then(function(storeRsc) {
                  var storeModel = Store.load(storeRsc);
                  self.stores.push(storeModel);
                  return storeModel;
               });
         }
      }
   }
})();