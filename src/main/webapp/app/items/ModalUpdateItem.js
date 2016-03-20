(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalUpdateItem", ModalUpdateItem);

   ModalUpdateItem.$inject = ["$uibModalInstance", "itemName"];
   function ModalUpdateItem($uibModalInstance, itemName) {
      var modalUpdateItemVM = this;
      modalUpdateItemVM.title = itemName ? "Update Item" : "Create Item";
      modalUpdateItemVM.itemName = itemName || "";
      modalUpdateItemVM.finish = finish;
      modalUpdateItemVM.cancel = cancel;

      ////////////////////

      function finish() {
         $uibModalInstance.close({itemName: modalUpdateItemVM.itemName});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();