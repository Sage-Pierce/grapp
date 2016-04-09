(function() {
   "use strict";

   angular.module("Grapp")
      .controller("GrappItemTree", GrappItemTree)
      .directive("grappItemTree", GrappItemTreeDirective);

   function GrappItemTreeDirective() {
      return {
         restrict: "E",
         controller: "GrappItemTree",
         controllerAs: "grappItemTreeVM",
         templateUrl: "app/itemtree/GrappItemTree.html",
         scope: {},
         bindToController: {
            items: "=",
            add: "=?",
            remove: "=?",
            options: "=?"
         }
      };
   }

   GrappItemTree.$inject = ["$scope"];
   function GrappItemTree($scope) {
      var grappItemTreeVM = this;
      grappItemTreeVM.items = this.items;
      grappItemTreeVM.filter = "";
      grappItemTreeVM.options = this.options || {};
      grappItemTreeVM.filterChanged = filterChanged;
      grappItemTreeVM.isNodeVisible = isNodeVisible;

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
                itemModel.name.toLowerCase().indexOf(grappItemTreeVM.filter.toLowerCase()) >= 0 ||
                _.reduce(itemModel.subItems, function(isASubItemVisible, subItem) { return isASubItemVisible || isItemVisible(subItem); }, false);
      }

      function collapseAll() {
         $scope.$broadcast('angular-ui-tree:collapse-all');
      }

      function isFilterEmpty() {
         return !grappItemTreeVM.filter || grappItemTreeVM.filter.length === 0;
      }
   }
})();