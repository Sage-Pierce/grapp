(function() {
   "use strict";

   angular.module("Grapp")
      .factory("DrawFeatureEventHandler", DrawFeatureEventHandler);

   DrawFeatureEventHandler.$inject = ["BaseEventHandler"];
   function DrawFeatureEventHandler(BaseEventHandler) {
      return function(mapControl, grappStoreLayout) {
         var self = angular.extend(this, new BaseEventHandler(mapControl, grappStoreLayout));
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
            grappStoreLayout.addFeature(_.extractVerticesFromGMapPolygon(gMapPolygon))
               .then(function (model) {
                  mapControl.addFeature(model.id, gMapPolygon);
               });
         }
      };
   }
})();