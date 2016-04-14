(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMap", MainStoreMap);

   MainStoreMap.$inject = ["store", "storeLayout", "mapControl" ];
   function MainStoreMap(store, storeLayout, mapControl) {
      var mainStoreMapVM = this;
      mainStoreMapVM.storeName = store.name;
      mainStoreMapVM.location = store.location;
      mainStoreMapVM.storeLayout = storeLayout;
      mainStoreMapVM.mapControl = mapControl;

      ////////////////////

   }
})();
