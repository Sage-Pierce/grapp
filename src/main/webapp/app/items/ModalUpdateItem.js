(function() {
   "use strict";

   angular.module("App")
      .controller("ModalUpdateItem", ModalUpdateItem);

   ModalUpdateItem.$inject = ["$uibModalInstance", "item"];
   function ModalUpdateItem($uibModalInstance, item) {
      var modalUpdateItemVM = this;
      modalUpdateItemVM.name = item.name;
      modalUpdateItemVM.isDataValid = isDataValid;
      modalUpdateItemVM.finish = finish;
      modalUpdateItemVM.cancel = cancel;

      ////////////////////

      function isDataValid() {
         return _.trim(modalUpdateItemVM.name).length > 0;
      }

      function finish() {
         $uibModalInstance.close({name: modalUpdateItemVM.name});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();