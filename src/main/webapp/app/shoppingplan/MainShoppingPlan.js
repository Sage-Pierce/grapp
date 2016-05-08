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

      ////////////////////

   }
})();