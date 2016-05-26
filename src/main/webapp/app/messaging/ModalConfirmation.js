(function() {
   "use strict";

   angular.module("App")
      .controller("ModalConfirmation", ModalConfirmation);

   ModalConfirmation.$inject = ["$uibModalInstance", "title", "message"];
   function ModalConfirmation($uibModalInstance, title, message) {
      var modalConfirmationVM = this;
      modalConfirmationVM.title = title;
      modalConfirmationVM.message = message;
      modalConfirmationVM.confirm = confirm;
      modalConfirmationVM.cancel = cancel;

      ////////////////////

      function confirm() {
         $uibModalInstance.close(true);
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();