(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalCreateItem", ModalCreateItem);

   ModalCreateItem.$inject = ["$uibModalInstance", "GrappItemCodeType", "superItemModel"];
   function ModalCreateItem($uibModalInstance, GrappItemCodeType, superItemModel) {
      var modalCreateItemVM = this;
      modalCreateItemVM.codeTypes = _.values(GrappItemCodeType);
      modalCreateItemVM.title = "Create " + (superItemModel ? "Sub-Item" : "General Item");
      modalCreateItemVM.codeType = GrappItemCodeType.UPC;
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
         $uibModalInstance.close({codeType: _.findKey(GrappItemCodeType, modalCreateItemVM.codeType), code: modalCreateItemVM.code, name: modalCreateItemVM.name});
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();