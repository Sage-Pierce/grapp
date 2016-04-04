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
            filter: "=?",
            add: "=?",
            edit: "=?",
            remove: "=?"
         }
      };
   }

   GrappItemTree.$inject = ["$scope"];
   function GrappItemTree($scope) {
      var grappItemTreeVM = this;
      grappItemTreeVM.items = this.items;
      grappItemTreeVM.filter = this.filter;
      grappItemTreeVM.isNodeVisible = isNodeVisible;

      initialize();

      ////////////////////

      function initialize() {
         $scope.$watch("grappItemTreeVM.filter", filterChanged);
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

      function filterChanged() {
         if (isFilterEmpty()) {
            collapseAll();
         }
      }

      function collapseAll() {
         $scope.$broadcast('angular-ui-tree:collapse-all');
      }

      function isFilterEmpty() {
         return !grappItemTreeVM.filter || grappItemTreeVM.filter.length === 0;
      }
   }
})();