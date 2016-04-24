(function() {
   "use strict";

   angular.module("App")
      .controller("ModalCreateItem", ModalCreateItem);

   ModalCreateItem.$inject = ["$uibModalInstance", "CodeType", "superItem"];
   function ModalCreateItem($uibModalInstance, CodeType, superItem) {
      var modalCreateItemVM = this;
      modalCreateItemVM.codeTypes = _.values(CodeType);
      modalCreateItemVM.title = "Create " + (superItem ? "Sub-Item" : "General Item");
      modalCreateItemVM.codeType = CodeType.UPC;
      modalCreateItemVM.code = "";
      modalCreateItemVM.name = "";
      modalCreateItemVM.isDataValid = isDataValid;
      modalCreateItemVM.isRandomCode = isRandomCode;
      modalCreateItemVM.finish = finish;
      modalCreateItemVM.cancel = cancel;

      ////////////////////

      function isDataValid() {
         return modalCreateItemVM.codeType !== null &&
                (isRandomCode() || _.trim(modalCreateItemVM.code).length > 0) &&
                _.trim(modalCreateItemVM.name).length > 0;
      }

      function isRandomCode() {
         return modalCreateItemVM.codeType === CodeType.RANDOM;
      }

      function finish() {
         $uibModalInstance.close({codeType: _.findKey(CodeType, modalCreateItemVM.codeType), code: modalCreateItemVM.code, name: modalCreateItemVM.name});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();