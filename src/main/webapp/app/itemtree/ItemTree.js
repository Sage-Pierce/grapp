(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ItemTree", ItemTree)
      .directive("itemTree", ItemTreeDirective);

   function ItemTreeDirective() {
      return {
         restrict: "E",
         controller: "ItemTree",
         controllerAs: "itemTreeVM",
         templateUrl: "app/itemtree/ItemTree.html",
         scope: {},
         bindToController: {
            items: "=",
            add: "=?",
            remove: "=?",
            options: "=?",
            disabled: "=?"
         }
      };
   }

   ItemTree.$inject = ["$scope"];
   function ItemTree($scope) {
      var itemTreeVM = this;
      itemTreeVM.items = this.items;
      itemTreeVM.filter = "";
      itemTreeVM.options = this.options || {};
      itemTreeVM.filterChanged = filterChanged;
      itemTreeVM.isNodeVisible = isNodeVisible;

      ////////////////////

      function filterChanged() {
         if (isFilterEmpty()) {
            collapseAll();
         }
      }

      function isNodeVisible(node) {
         var visible = isItemVisible(node.$modelValue);
         if (visible && !isFilterEmpty()) {
            node.expand();
         }
         return visible;
      }

      function isItemVisible(itemModel) {
         return isFilterEmpty() ||
                itemModel.name.toLowerCase().indexOf(itemTreeVM.filter.toLowerCase()) >= 0 ||
                _.reduce(itemModel.subItems, function(isASubItemVisible, subItem) { return isASubItemVisible || isItemVisible(subItem); }, false);
      }

      function collapseAll() {
         $scope.$broadcast('angular-ui-tree:collapse-all');
      }

      function isFilterEmpty() {
         return !itemTreeVM.filter || itemTreeVM.filter.length === 0;
      }
   }
})();