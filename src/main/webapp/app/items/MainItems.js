(function() {
   "use strict";

   angular.module("App")
      .controller("MainItems", MainItems);

   MainItems.$inject = ["$uibModal", "ItemManagementRoot", "Item", "Messaging"];
   function MainItems($uibModal, ItemManagementRoot, Item, Messaging) {
      var mainItemsVM = this;
      mainItemsVM.loadingPromise = null;
      mainItemsVM.items = [];
      mainItemsVM.filter = "";
      mainItemsVM.createGeneralItem = createGeneralItem;
      mainItemsVM.importNacsItems = importNacsItems;
      mainItemsVM.createSubItem = createSubItem;
      mainItemsVM.editItem = editItem;
      mainItemsVM.deleteItem = deleteItem;

      initialize();

      ////////////////////

      function initialize() {
         reloadItems();
      }

      function createGeneralItem() {
         openModalCreateItem().then(function(result) {
            Item.createGeneralItem(result).then(function(item) {
               mainItemsVM.items.push(item);
            });
         });
      }

      function importNacsItems() {
         openModalImport().then(function(result) {
            ItemManagementRoot.importItems("NACS", result.data).then(reloadItems);
         });
      }

      function createSubItem(itemScope) {
         var item = itemScope.$modelValue;
         openModalCreateItem(item).then(function(result) {
            item.addSubItem(result);
         });
      }

      function editItem(itemScope) {
         var item = itemScope.$modelValue;
         openModalUpdateItem(item).then(item.setAttributes);
      }

      function deleteItem(itemScope) {
         var item = itemScope.$modelValue;
         Messaging.requestConfirmation("Delete Item", "Are you sure you want to delete the Item '" + item.name + "'?")
            .then(item.delete)
            .then(itemScope.remove);
      }

      function reloadItems() {
         mainItemsVM.loadingPromise = Item.loadAllGeneral().then(function(items) {
            mainItemsVM.items = items;
         });
      }

      function openModalImport() {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalImport.html",
            controller: "ModalImport",
            controllerAs: "modalImportVM"
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

      function openModalUpdateItem(item) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalUpdateItem.html",
            controller: "ModalUpdateItem",
            controllerAs: "modalUpdateItemVM",
            resolve: {
               item: function() { return item; }
            }
         }).result;
      }
   }
})();