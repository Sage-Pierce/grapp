(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreLayout", MainStoreLayout);

   MainStoreLayout.$inject = ["store", "storeLayout", "mapControl" ];
   function MainStoreLayout(store, storeLayout, mapControl) {
      var mainStoreLayoutVM = this;
      mainStoreLayoutVM.storeName = store.name;
      mainStoreLayoutVM.location = store.location;
      mainStoreLayoutVM.storeLayout = storeLayout;
      mainStoreLayoutVM.mapControl = mapControl;

      ////////////////////

   }
})();
