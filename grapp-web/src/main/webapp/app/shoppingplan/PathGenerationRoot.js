(function() {
   "use strict";

   angular.module("App")
      .service("PathGenerationRoot", PathGenerationRoot);

   PathGenerationRoot.$inject = ["Root"];
   function PathGenerationRoot(Root) {
      var self = angular.extend(this, new Root("/pathGeneration/"));
      self.generatePath = generatePath;

      ////////////////////

      function generatePath(start, finish, waypoints, enclosure, polygons) {
         var startJson = JSON.stringify(start);
         var finishJson = JSON.stringify(finish);
         var waypointsJson = JSON.stringify({points: waypoints});
         var polygonsJson = JSON.stringify({enclosure: enclosure, polygons: polygons});
         return self.afterLoad().then(function(rootRsc) {
            return rootRsc.$request().$put("self", {start: startJson, finish: finishJson, waypoints: waypointsJson}, polygonsJson);
         });
      }
   }
})();