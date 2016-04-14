(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMap", MainStoreMap);

   MainStoreMap.$inject = ["grappStore", "storeLayout", "mapControl" ];
   function MainStoreMap(grappStore, storeLayout, mapControl) {
      var mainStoreMapVM = this;
      mainStoreMapVM.grappStoreName = grappStore.name;
      mainStoreMapVM.location = grappStore.location;
      mainStoreMapVM.storeLayout = storeLayout;
      mainStoreMapVM.mapControl = mapControl;

      ////////////////////

   }
})();
