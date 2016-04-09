(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalUpdateItem", ModalUpdateItem);

   ModalUpdateItem.$inject = ["$uibModalInstance", "superItemModel"];
   function ModalUpdateItem($uibModalInstance, superItemModel) {
      var modalCreateItemVM = this;
      modalCreateItemVM.title = "Create " + (superItemModel ? "Sub-Item" : "General Item");
      modalCreateItemVM.itemName = "";
      modalCreateItemVM.finish = finish;
      modalCreateItemVM.cancel = cancel;

      ////////////////////

      function finish() {
         $uibModalInstance.close({name: modalCreateItemVM.itemName});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();