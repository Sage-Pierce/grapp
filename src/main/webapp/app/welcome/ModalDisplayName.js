(function() {
   "use strict";

   angular.module("App")
      .controller("ModalUpdateDisplayName", ModalUpdateDisplayName);

   ModalUpdateDisplayName.$inject = ["$uibModalInstance", "name"];
   function ModalUpdateDisplayName($uibModalInstance, name) {
      var modalUpdateDisplayNameVM = this;
      modalUpdateDisplayNameVM.name = name;
      modalUpdateDisplayNameVM.update = update;
      modalUpdateDisplayNameVM.cancel = cancel;

      ////////////////////

      function update() {
         $uibModalInstance.close(modalUpdateDisplayNameVM.name);
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();
