(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainStoreMap", MainStoreMap);

   MainStoreMap.$inject = ["grappStore", "grappStoreLayout", "mapControl" ];
   function MainStoreMap(grappStore, grappStoreLayout, mapControl) {
      var mainStoreMapVM = this;
      mainStoreMapVM.grappStoreName = grappStore.name;
      mainStoreMapVM.grappStoreLocation = grappStore.location;
      mainStoreMapVM.grappStoreLayout = grappStoreLayout;
      mainStoreMapVM.mapControl = mapControl;

      ////////////////////

   }
})();
