(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalCreateItem", ModalCreateItem);

   ModalCreateItem.$inject = ["$uibModalInstance", "CodeType", "superItemModel"];
   function ModalCreateItem($uibModalInstance, CodeType, superItemModel) {
      var modalCreateItemVM = this;
      modalCreateItemVM.codeTypes = _.values(CodeType);
      modalCreateItemVM.title = "Create " + (superItemModel ? "Sub-Item" : "General Item");
      modalCreateItemVM.codeType = CodeType.UPC;
      modalCreateItemVM.code = "";
      modalCreateItemVM.name = "";
      modalCreateItemVM.isDataValid = isDataValid;
      modalCreateItemVM.finish = finish;
      modalCreateItemVM.cancel = cancel;

      ////////////////////

      function isDataValid() {
         return modalCreateItemVM.codeType !== null && _.trim(modalCreateItemVM.code).length > 0 && _.trim(modalCreateItemVM.name).length > 0;
      }

      function finish() {
         $uibModalInstance.close({codeType: _.findKey(CodeType, modalCreateItemVM.codeType), code: modalCreateItemVM.code, name: modalCreateItemVM.name});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();