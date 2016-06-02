(function() {
   "use strict";

   angular.module("App")
      .service("PathGenerationRoot", PathGenerationRoot);

   PathGenerationRoot.$inject = ["Root"];
   function PathGenerationRoot(Root) {
      var self = angular.extend(this, new Root("/"));

      ////////////////////
   }
})();