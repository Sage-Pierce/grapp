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
         Item.loadAllGeneral().then(function(items) {
            mainItemsVM.items = items;
         });
      }

      function createGeneralItem() {
         openModalCreateItem().then(function(result) {
            Item.createGeneralItem(result).then(function(item) {
               mainItemsVM.items.push(item);
            });
         });
      }

      function importItems() {
         openModalImportItems().then(function(result) {
            Item.importItems(result.data).then(function(items) {
               mainItemsVM.items = items;
            });
         });
      }

      function createSubItem(itemScope) {
         var item = itemScope.$modelValue;
         openModalCreateItem(item).then(function(result) {
            item.addSubItem(result);
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

      function openModalCreateItem(superItem) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalCreateItem.html",
            controller: "ModalCreateItem",
            controllerAs: "modalCreateItemVM",
            resolve: {
               superItem: function() { return superItem; }
            }
         }).result;
      }
   }
})();