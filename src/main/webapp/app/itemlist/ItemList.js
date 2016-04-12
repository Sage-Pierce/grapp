(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ItemList", ItemList)
      .directive("itemList", ItemListDirective);

   function ItemListDirective() {
      return {
         restrict: "E",
         controller: "ItemList",
         controllerAs: "itemListVM",
         templateUrl: "app/itemlist/ItemList.html",
         scope: {},
         bindToController: {
            items: "=",
            remove: "=",
            options: "=?"
         }
      };
   }

   ItemList.$inject = [];
   function ItemList() {
      var itemListVM = this;
      itemListVM.options = this.options || {};

      ////////////////////

   }
})();