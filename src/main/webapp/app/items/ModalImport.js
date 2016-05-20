(function() {
   "use strict";

   angular.module("App")
      .controller("ModalImport", ModalImport);

   ModalImport.$inject = ["$uibModalInstance"];
   function ModalImport($uibModalInstance) {
      var modalImportVM = this;
      modalImportVM.data = "";
      modalImportVM.finish = finish;
      modalImportVM.cancel = cancel;

      ////////////////////

      function finish() {
         $uibModalInstance.close({data: modalImportVM.data});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();