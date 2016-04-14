(function() {
   "use strict";

   angular.module("App")
      .controller("MainItems", MainItems);

   MainItems.$inject = ["$uibModal", "Item"];
   function MainItems($uibModal, Item) {
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
         Item.loadAllGeneral().then(function(itemModels) {
            mainItemsVM.items = itemModels;
         });
      }

      function createGeneralItem() {
         openModalCreateItem().then(function(result) {
            Item.createGeneralItem(result).then(function(itemModel) {
               mainItemsVM.items.push(itemModel);
            });
         });
      }

      function importItems() {
         openModalImportItems().then(function(result) {
            Item.importItems(result.data).then(function(itemModels) {
               mainItemsVM.items = itemModels;
            });
         });
      }

      function createSubItem(itemScope) {
         var itemModel = itemScope.$modelValue;
         openModalCreateItem(itemModel).then(function(result) {
            itemModel.addSubItem(result);
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