(function() {
   "use strict";

   angular.module("App")
      .factory("DrawFeatureEventHandler", DrawFeatureEventHandler);

   DrawFeatureEventHandler.$inject = ["BaseEventHandler"];
   function DrawFeatureEventHandler(BaseEventHandler) {
      return function(mapControl, storeLayout) {
         var self = angular.extend(this, new BaseEventHandler(mapControl, storeLayout));
         self.start = start;
         self.finish = finish;
         self.polygonComplete = polygonComplete;

         ////////////////////

         function start() {
            mapControl.setDrawingMode("POLYGON");
         }

         function finish() {
            mapControl.setDrawingMode(null);
         }

         function polygonComplete(gMapPolygon) {
            storeLayout.addFeature(_.extractVerticesFromGMapPolygon(gMapPolygon))
               .then(function(feature) {
                  mapControl.addFeature(feature.id, gMapPolygon);
               });
         }
      };
   }
})();