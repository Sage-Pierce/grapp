(function() {
   "use strict";

   angular.module("App")
      .service("uiGmapUtil", UIGMapUtil);

   UIGMapUtil.$inject = ["$q", "uiGmapIsReady"];
   function UIGMapUtil($q, uiGmapIsReady) {
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