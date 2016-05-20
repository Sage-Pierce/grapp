(function() {
   "use strict";

   angular.module("App")
      .controller("ModalItem", ModalItem);

   ModalItem.$inject = ["$uibModalInstance", "CodeType", "superItem"];
   function ModalItem($uibModalInstance, CodeType, superItem) {
      var modalItemVM = this;
      modalItemVM.codeTypes = _.values(CodeType);
      modalItemVM.title = "Create " + (superItem ? "Sub-Item" : "General Item");
      modalItemVM.codeType = CodeType.UPC;
      modalItemVM.code = "";
      modalItemVM.name = "";
      modalItemVM.isDataValid = isDataValid;
      modalItemVM.isRandomCode = isRandomCode;
      modalItemVM.finish = finish;
      modalItemVM.cancel = cancel;

      ////////////////////

      function isDataValid() {
         return modalItemVM.codeType !== null &&
                (isRandomCode() || _.trim(modalItemVM.code).length > 0) &&
                _.trim(modalItemVM.name).length > 0;
      }

      function isRandomCode() {
         return modalItemVM.codeType === CodeType.RANDOM;
      }

      function finish() {
         $uibModalInstance.close({codeType: _.findKey(CodeType, modalItemVM.codeType), code: modalItemVM.code, name: modalItemVM.name});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();