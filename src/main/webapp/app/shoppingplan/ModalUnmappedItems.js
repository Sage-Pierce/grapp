(function() {
   "use strict";

   angular.module("App")
      .controller("ModalUnmappedItems", ModalUnmappedItems);

   ModalUnmappedItems.$inject = ["$uibModalInstance", "items"];
   function ModalUnmappedItems($uibModalInstance, items) {
      var modalUnmappedItemsVM = this;
      modalUnmappedItemsVM.items = items;
      modalUnmappedItemsVM.dismiss = $uibModalInstance.dismiss;

      ////////////////////

   }
})();