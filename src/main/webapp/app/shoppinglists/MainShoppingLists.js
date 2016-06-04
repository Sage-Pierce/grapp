(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingLists", MainShoppingLists);

   MainShoppingLists.$inject = ["$state", "$uibModal", "shopper"];
   function MainShoppingLists($state, $uibModal, shopper) {
      var mainShoppingListsVM = this;
      mainShoppingListsVM.transitionPromise = null;
      mainShoppingListsVM.lists = shopper.lists;
      mainShoppingListsVM.createList = createList;
      mainShoppingListsVM.deleteList = deleteList;
      mainShoppingListsVM.openList = openList;

      ////////////////////

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
         mainShoppingListsVM.transitionPromise = $state.go("main.shoppingList", {listId: list.id});
      }
   }
})();