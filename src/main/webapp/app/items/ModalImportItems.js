(function() {
   "use strict";

   angular.module("App")
      .controller("ModalImportItems", ModalImportItems);

   ModalImportItems.$inject = ["$uibModalInstance"];
   function ModalImportItems($uibModalInstance) {
      var modalImportItemsVM = this;
      modalImportItemsVM.data = "";
      modalImportItemsVM.finish = finish;
      modalImportItemsVM.cancel = cancel;

      ////////////////////

      function finish() {
         $uibModalInstance.close({data: modalImportItemsVM.data});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();