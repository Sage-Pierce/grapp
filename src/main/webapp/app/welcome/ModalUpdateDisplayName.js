(function() {
   "use strict";

   angular.module("App")
      .controller("ModalUpdateDisplayName", ModalUpdateDisplayName);

   ModalUpdateDisplayName.$inject = ["$uibModalInstance", "displayName"];
   function ModalUpdateDisplayName($uibModalInstance, displayName) {
      var modalUpdateDisplayNameVM = this;
      modalUpdateDisplayNameVM.displayName = displayName;
      modalUpdateDisplayNameVM.update = update;
      modalUpdateDisplayNameVM.cancel = cancel;

      ////////////////////

      function update() {
         $uibModalInstance.close(modalUpdateDisplayNameVM.displayName);
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();
