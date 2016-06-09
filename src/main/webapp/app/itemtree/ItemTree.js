(function() {
   "use strict";

   angular.module("App")
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

      var lowerCaseFilter = itemTreeVM.filter;

      ////////////////////

      function filterChanged() {
         lowerCaseFilter = _.lowerCase(itemTreeVM.filter);
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
                doesItemMatchFilter(itemModel) ||
                (itemModel.isRecent() && doAnyItemsMatchFilter(itemModel.lineage)) ||
                _.reduce(itemModel.subItems, function(isASubItemVisible, subItem) { return isASubItemVisible || isItemVisible(subItem); }, false);
      }

      function collapseAll() {
         $scope.$broadcast('angular-ui-tree:collapse-all');
      }

      function isFilterEmpty() {
         return !itemTreeVM.filter || itemTreeVM.filter.length === 0;
      }

      function doAnyItemsMatchFilter(items) {
         return !_.isUndefined(_.find(items, doesItemMatchFilter))
      }

      function doesItemMatchFilter(item) {
         return item.name.toLowerCase().indexOf(lowerCaseFilter) >= 0;
      }
   }
})();