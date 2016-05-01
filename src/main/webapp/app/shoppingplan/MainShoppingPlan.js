(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingPlan", MainShoppingPlan);

   MainShoppingPlan.$inject = ["shoppingList", "storeLayout"];
   function MainShoppingPlan(shoppingList, storeLayout) {
      var mainShoppingPlanVM = this;
      mainShoppingPlanVM.shoppingList = shoppingList;
      mainShoppingPlanVM.storeLayout = storeLayout;

      ////////////////////

   }
})();