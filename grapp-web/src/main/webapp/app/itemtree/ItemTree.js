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
            edit: "=?",
            remove: "=?",
            options: "=?",
            disabled: "=?"
         }
      };
   }

   ItemTree.$inject = ["$scope"];
   function ItemTree($scope) {
      var itemTreeVM = this;
      itemTreeVM.items = {};
      itemTreeVM.filter = "";
      itemTreeVM.treeOptions = {};
      itemTreeVM.options = {};
      itemTreeVM.filterChanged = filterChanged;
      itemTreeVM.isNodeVisible = isNodeVisible;

      var visibilityCache = {};
      var lowerCaseFilter = itemTreeVM.filter;

      this.$onInit = function() { initialize(this); };

      ////////////////////

      function initialize(binder) {
         itemTreeVM.items = binder.items;
         itemTreeVM.options = binder.options || {};
         itemTreeVM.treeOptions = {
            beforeDrop: function(event) {
               var oldSuperItem = event.source.nodesScope.$parent.$modelValue;
               var newSuperItem = event.dest.nodesScope.$parent.$modelValue;
               var item = event.source.nodeScope.$modelValue;
               return oldSuperItem === newSuperItem || (newSuperItem ? item.move(newSuperItem) : item.makeGeneral());
            }
         };
      }

      function filterChanged() {
         visibilityCache = {};
         lowerCaseFilter = _.lowerCase(itemTreeVM.filter);
         if (isFilterEmpty()) {
            collapseAll();
         }
      }

      function isNodeVisible(node) {
         var item = node.$modelValue;
         var visible = isItemVisible(item);
         if (visible && node.collapsed && !isFilterEmpty()) {
            node.expand();
         }
         return visible;
      }

      function collapseAll() {
         $scope.$broadcast('angular-ui-tree:collapse-all');
      }

      function isItemVisible(item) {
         var visible = item.primaryCode in visibilityCache ? visibilityCache[item.primaryCode]
                                                           : isFilterEmpty() ||
                                                             doesItemMatchFilter(item) ||
                                                             (item.isRecent() && doAnyItemsMatchFilter(item.lineage)) ||
                                                             _.reduce(item.subItems, reduceAnySubItemVisibility, false);
         visibilityCache[item.primaryCode] = visible;
         return visible;
      }

      function reduceAnySubItemVisibility(isASubItemVisible, subItem) {
         return isASubItemVisible || isItemVisible(subItem);
      }

      function isFilterEmpty() {
         return !itemTreeVM.filter || itemTreeVM.filter.length === 0;
      }

      function doAnyItemsMatchFilter(items) {
         return !_.isUndefined(_.find(items, doesItemMatchFilter));
      }

      function doesItemMatchFilter(item) {
         return item.name.toLowerCase().indexOf(lowerCaseFilter) >= 0;
      }
   }
})();