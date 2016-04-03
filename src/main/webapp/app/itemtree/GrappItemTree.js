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
            edit: "=?",
            remove: "=?"
         }
      };
   }

   GrappItemTree.$inject = [];
   function GrappItemTree() {
      var grappItemTreeVM = this;
      grappItemTreeVM.items = this.items;

      ////////////////////

   }
})();