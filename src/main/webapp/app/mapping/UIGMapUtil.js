(function() {
   "use strict";

   angular.module("Grapp")
      .service("uiGmapUtil", uiGmapUtil);

   uiGmapUtil.$inject =["$q", "uiGmapIsReady"];
   function uiGmapUtil($q, uiGmapIsReady) {
      var self = this;
      self.afterGmapsAreReady = afterGmapsAreReady;
      self.onNextAngularTurn = onNextAngularTurn;

      ////////////////////

      function afterGmapsAreReady(instances) {
         return uiGmapIsReady.promise(instances || 1);
      }

      function onNextAngularTurn() {
         return $q.resolve();
      }
   }
})();