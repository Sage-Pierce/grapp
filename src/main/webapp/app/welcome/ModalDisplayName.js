(function() {
   "use strict";

   angular.module("App")
      .controller("ModalDisplayName", ModalDisplayName);

   ModalDisplayName.$inject = ["$uibModalInstance", "name"];
   function ModalDisplayName($uibModalInstance, name) {
      var modalDisplayNameVM = this;
      modalDisplayNameVM.name = name;
      modalDisplayNameVM.update = update;
      modalDisplayNameVM.cancel = cancel;

      ////////////////////

      function update() {
         $uibModalInstance.close(modalDisplayNameVM.name);
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();
