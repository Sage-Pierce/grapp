(function() {
   "use strict";

   angular.module("App")
      .controller("ModalImportItems", ModalImportItems);

   ModalImportItems.$inject = ["$uibModalInstance"];
   function ModalImportItems($uibModalInstance) {
      var modalImportItemsVM = this;
      modalImportItemsVM.cancel = cancel;
      modalImportItemsVM.finish = finish;
      modalImportItemsVM.data = "";

      ////////////////////

      function finish() {
         $uibModalInstance.close({data: modalImportItemsVM.data});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();