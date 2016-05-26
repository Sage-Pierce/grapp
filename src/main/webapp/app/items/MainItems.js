(function() {
   "use strict";

   angular.module("App")
      .controller("MainItems", MainItems);

   MainItems.$inject = ["$uibModal", "Item", "Messaging"];
   function MainItems($uibModal, Item, Messaging) {
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
         openModalItem().then(function(result) {
            Item.createGeneralItem(result).then(function(item) {
               mainItemsVM.items.push(item);
            });
         });
      }

      function importItems() {
         openModalImport().then(function(result) {
            Item.importItems(result.data).then(function(items) {
               mainItemsVM.items = items;
            });
         });
      }

      function createSubItem(itemScope) {
         var item = itemScope.$modelValue;
         openModalItem(item).then(function(result) {
            item.addSubItem(result);
         });
      }

      function deleteItem(itemScope) {
         var item = itemScope.$modelValue;
         Messaging.requestConfirmation("Delete Item", "Are you sure you want to delete the Item " + item.name + "?")
            .then(item.delete)
            .then(itemScope.remove);
      }

      function openModalImport() {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalImport.html",
            controller: "ModalImport",
            controllerAs: "modalImportVM"
         }).result;
      }

      function openModalItem(superItem) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalItem.html",
            controller: "ModalItem",
            controllerAs: "modalItemVM",
            resolve: {
               superItem: function() { return superItem; }
            }
         }).result;
      }
   }
})();