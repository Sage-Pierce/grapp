(function() {
   "use strict";

   angular.module("App")
      .factory("CopyFeatureEventHandler", CopyFeatureEventHandler);

   CopyFeatureEventHandler.$inject = ["BaseEventHandler"];
   function CopyFeatureEventHandler(BaseEventHandler) {
      return function(mapControl, storeLayout) {
         var self = angular.extend(this, new BaseEventHandler(mapControl, storeLayout));
         self.finish = finish;
         self.mapClicked = mapClicked;
         self.polygonClicked = polygonClicked;
         self.polygonRightClicked = polygonRightClicked;

         var gMapPolygonCopyModel;

         ////////////////////

         function finish() {
            if (gMapPolygonCopyModel) {
               gMapPolygonCopyModel.deselect();
            }
         }

         function mapClicked(modelId, map, mouseEvent) {
            if (gMapPolygonCopyModel) {
               var gMapPolygon = gMapPolygonCopyModel.copyToLatLng(mouseEvent.latLng);
               storeLayout.addFeature(_.extractVerticesFromGMapPolygon(gMapPolygon))
                  .then(function(feature) {
                     gMapPolygon.setDraggable(true);
                     mapControl.addFeature(feature.id, gMapPolygon);
                  });
            }
         }

         function polygonClicked(modelId, gMapPolygon, polyMouseEvent) {
            if (gMapPolygonCopyModel) {
               gMapPolygonCopyModel.deselect();
            }
            gMapPolygonCopyModel = new GMapPolygonCopyModel(gMapPolygon, polyMouseEvent.latLng);
            gMapPolygonCopyModel.select();
         }

         function polygonRightClicked(modelId, gMapPolygon) {
            if (gMapPolygonCopyModel && gMapPolygonCopyModel.isForGMapPolygon(gMapPolygon)) {
               gMapPolygonCopyModel.deselect();
               gMapPolygonCopyModel = null;
            }
            storeLayout.removeFeatureById(modelId);
            mapControl.removeFeatureById(modelId, gMapPolygon);
         }

         function GMapPolygonCopyModel(gMapPolygon, copyLatLng) {
            var self = this;
            self.select = select;
            self.deselect = deselect;
            self.isForGMapPolygon = isForGMapPolygon;
            self.copyToLatLng = copyToLatLng;

            var copyOffsets = null;
            var originalStroke = null;

            initialize();

            ////////////////////

            function initialize() {
               copyOffsets = _.extractPathFromGMapPolygon(gMapPolygon).map(function(latLng) {
                  return {
                     latOffset: latLng.lat() - copyLatLng.lat(),
                     lngOffset: latLng.lng() - copyLatLng.lng()
                  };
               });
            }

            function select() {
               originalStroke = createStrokeOptions(gMapPolygon.strokeColor, gMapPolygon.strokeWeight);
               gMapPolygon.setOptions(createStrokeOptions("#ffffff", 4));
            }

            function deselect() {
               gMapPolygon.setOptions(originalStroke);
            }

            function copyToLatLng(latLng) {
               return new google.maps.Polygon({
                  paths: copyOffsets.map(function(offset) {
                     return {
                        lat: latLng.lat() + offset.latOffset,
                        lng: latLng.lng() + offset.lngOffset
                     };
                  }),
                  strokeColor: originalStroke.strokeColor,
                  strokeWeight: originalStroke.strokeWeight,
                  fillColor: gMapPolygon.fillColor,
                  fillOpacity: gMapPolygon.fillOpacity,
                  draggable: gMapPolygon.draggable,
                  zIndex: gMapPolygon.zIndex
               });
            }

            function isForGMapPolygon(otherGMapPolygon) {
               return gMapPolygon === otherGMapPolygon;
            }

            function createStrokeOptions(color, weight) {
               return {
                  strokeColor: color,
                  strokeWeight: weight
               };
            }
         }
      };
   }
})();