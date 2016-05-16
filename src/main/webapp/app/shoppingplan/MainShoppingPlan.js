(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingPlan", MainShoppingPlan);

   MainShoppingPlan.$inject = ["StoreMapControl", "store", "shoppingLayout"];
   function MainShoppingPlan(StoreMapControl, store, shoppingLayout) {
      var mainShoppingPlanVM = this;
      mainShoppingPlanVM.mapControl = new StoreMapControl();
      mainShoppingPlanVM.location = store.location;
      mainShoppingPlanVM.shoppingLayout = shoppingLayout;

      initialize();

      ////////////////////

      function initialize() {
         shoppingLayout.generateShoppingPath().then(function(path) {
            mainShoppingPlanVM.mapControl.addPath(new google.maps.Polyline({
               path: path.locations,
               strokeColor: "#339933",
               icons: [{
                  icon: { path: google.maps.SymbolPath.FORWARD_OPEN_ARROW },
                  offset: "10px",
                  repeat: "50px"
               }]
            }));
         });
      }
   }
})();