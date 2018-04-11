(function() {
   "use strict";

   angular.module("App")
      .controller("ModalShoppingList", ModalShoppingList);

   ModalShoppingList.$inject = ["$uibModalInstance"];
   function ModalShoppingList($uibModalInstance) {
      var modalShoppingListVM = this;
      modalShoppingListVM.name = null;
      modalShoppingListVM.finish = finish;
      modalShoppingListVM.cancel = cancel;

      ////////////////////

      function finish() {
         $uibModalInstance.close({name: modalShoppingListVM.name});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();