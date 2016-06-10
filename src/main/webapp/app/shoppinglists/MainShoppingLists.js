(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingLists", MainShoppingLists);

   MainShoppingLists.$inject = ["$state", "$uibModal", "Shopper"];
   function MainShoppingLists($state, $uibModal, Shopper) {
      var mainShoppingListsVM = this;
      mainShoppingListsVM.loadingPromise = null;
      mainShoppingListsVM.lists = [];
      mainShoppingListsVM.areThereAnyLists = areThereAnyLists;
      mainShoppingListsVM.createList = createList;
      mainShoppingListsVM.deleteList = deleteList;
      mainShoppingListsVM.openList = openList;

      var shopper = null;

      initialize();

      ////////////////////

      function initialize() {
         mainShoppingListsVM.loadingPromise = Shopper.load().then(handleShopperModel);
      }

      function areThereAnyLists() {
         return mainShoppingListsVM.lists.length > 0;
      }

      function createList() {
         $uibModal.open({
            animation: true,
            templateUrl: "app/shoppinglists/ModalShoppingList.html",
            controller: "ModalShoppingList",
            controllerAs: "modalShoppingListVM"
         }).result.then(function(result) { shopper.addList(result.name); });
      }

      function deleteList(list) {
         shopper.removeList(list);
      }

      function openList(list) {
         $state.go("main.shoppingList", {listId: list.id});
      }

      function handleShopperModel(shopperModel) {
         shopper = shopperModel;
         mainShoppingListsVM.lists = shopper.lists;
      }
   }
})();