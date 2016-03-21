(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalUpdateItem", ModalUpdateItem);

   ModalUpdateItem.$inject = ["$uibModalInstance", "isGeneralItem", "itemName"];
   function ModalUpdateItem($uibModalInstance, isGeneralItem, itemName) {
      var modalUpdateItemVM = this;
      modalUpdateItemVM.title = (itemName ? "Edit" : "Create") + (isGeneralItem ? " General Item" : " Sub-Item");
      modalUpdateItemVM.itemName = itemName || "";
      modalUpdateItemVM.finish = finish;
      modalUpdateItemVM.cancel = cancel;

      ////////////////////

      function finish() {
         $uibModalInstance.close({name: modalUpdateItemVM.itemName});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();