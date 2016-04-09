(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainItems", MainItems);

   MainItems.$inject = ["$uibModal", "GrappItem"];
   function MainItems($uibModal, GrappItem) {
      var mainItemsVM = this;
      mainItemsVM.items = [];
      mainItemsVM.filter = "";
      mainItemsVM.createGeneralItem = createGeneralItem;
      mainItemsVM.importItems = importItems;
      mainItemsVM.createSubItem = createSubItem;
      mainItemsVM.deleteItem = deleteItem;

      initialize();

      ////////////////////

      function initialize() {
         GrappItem.loadAllGeneral().then(function(itemModels) {
            mainItemsVM.items = itemModels;
         });
      }

      function createGeneralItem() {
         openModalCreateItem().then(function(result) {
            GrappItem.createGeneralItem(result.name).then(function(itemModel) {
               mainItemsVM.items.push(itemModel);
            });
         });
      }

      function importItems() {
         openModalImportItems().then(function(result) {
            GrappItem.importItems(result.data).then(function(itemModels) {
               mainItemsVM.items = itemModels;
            });
         });
      }

      function createSubItem(itemScope) {
         var itemModel = itemScope.$modelValue;
         openModalCreateItem(itemModel).then(function(result) {
            itemModel.addSubItem(result.name);
         });
      }

      function deleteItem(itemScope) {
         itemScope.$modelValue.delete().then(function() {
            itemScope.remove();
         });
      }

      function openModalImportItems() {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalImportItems.html",
            controller: "ModalImportItems",
            controllerAs: "modalImportItemsVM"
         }).result;
      }

      function openModalCreateItem(superItemModel) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalCreateItem.html",
            controller: "ModalCreateItem",
            controllerAs: "modalCreateItemVM",
            resolve: {
               superItemModel: function() { return superItemModel; }
            }
         }).result;
      }
   }
})();