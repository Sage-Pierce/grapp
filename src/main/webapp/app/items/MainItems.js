(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainItems", MainItems);

   MainItems.$inject = ["$uibModal", "GrappItem"];
   function MainItems($uibModal, GrappItem) {
      var mainItemsVM = this;
      mainItemsVM.items = [];
      mainItemsVM.createGeneralItem = createGeneralItem;
      mainItemsVM.createItem = createItem;
      mainItemsVM.editItem = editItem;
      mainItemsVM.deleteItem = deleteItem;

      initialize();

      ////////////////////

      function initialize() {
         GrappItem.loadAllGeneral().then(function(itemModels) {
            mainItemsVM.items = itemModels;
         });
      }

      function createGeneralItem() {
         openModalUpdateItem(true).then(function(result) {
            GrappItem.createGeneralItem(result.itemName).then(function(itemModel) {
               mainItemsVM.items.push(itemModel);
            });
         });
      }

      function createItem(itemScope) {
         var itemModel = itemScope.$modelValue;
         openModalUpdateItem(itemModel).then(function(result) {
            itemModel.addSubItem(result.itemName);
         });
      }

      function editItem(itemScope) {
         var itemModel = itemScope.$modelValue;
         openModalUpdateItem(itemModel).then(itemModel.commitAttributes);
      }

      function deleteItem(itemScope) {
         itemScope.$modelValue.delete().then(function() {
            itemScope.remove();
         });
      }

      function openModalUpdateItem(itemModel) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalUpdateItem.html",
            controller: "ModalUpdateItem",
            controllerAs: "modalUpdateItemVM",
            resolve: {
               isGeneralItem: function() { return itemModel.superItemId === null; },
               itemName: function() { return itemModel.name; }
            }
         }).result;
      }
   }
})();