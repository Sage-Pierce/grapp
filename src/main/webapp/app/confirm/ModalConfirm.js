(function() {
   "use strict";

   angular.module("App")
      .controller("ModalConfirm", ModalConfirm);

   ModalConfirm.$inject = ["$uibModalInstance", "title", "message"];
   function ModalConfirm($uibModalInstance, title, message) {
      var modalConfirmVM = this;
      modalConfirmVM.title = title;
      modalConfirmVM.message = message;
      modalConfirmVM.confirm = confirm;
      modalConfirmVM.cancel = cancel;

      ////////////////////

      function confirm() {
         $uibModalInstance.close(true);
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();