(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainItems", MainItems);

   MainItems.$inject = ["$uibModal"];
   function MainItems($uibModal) {
      var mainItemsVM = this;
      mainItemsVM.items = [];
      mainItemsVM.createItem = createItem;
      mainItemsVM.deleteItem = deleteItem;

      initialize();

      ////////////////////

      function initialize() {
         mainItemsVM.items = [
            {
               id: 123,
               parentId: null,
               name: "Bread",
               subItems: [
                  {
                     id: 321,
                     parentId: 123,
                     name: "Pita",
                     subItems: []
                  }
               ]
            }
         ];
      }

      function createItem(itemScope) {
         console.log("CREATE ITEM");
         console.log(itemScope);
         console.log(itemScope.$modelValue);
         openModalUpdateItem(null).then(function(result) {
            itemScope.$modelValue.subItems.push({
               id: 999,
               parentId: itemScope.$modelValue.id,
               name: result.itemName,
               subItems: []
            });
         });
      }

      function deleteItem(itemScope) {
         console.log("DELETE ITEM");
         console.log(itemScope);
      }

      function openModalUpdateItem(itemName) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/items/ModalUpdateItem.html",
            controller: "ModalUpdateItem",
            controllerAs: "modalUpdateItemVM",
            resolve: {
               itemName: function() { return itemName; }
            }
         }).result;
      }
   }
})();