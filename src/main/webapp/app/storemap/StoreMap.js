(function() {
   "use strict";

   angular.module("Grapp")
      .controller("StoreMap", StoreMap)
      .directive("storeMap", StoreMapDirective);

   function StoreMapDirective() {
      return {
         restrict: "E",
         controller: "StoreMap",
         controllerAs: "storeMapVM",
         templateUrl: "app/storemap/StoreMap.html",
         scope: {},
         bindToController: {
            mapControl: "=",
            location: "=",
            layout: "="
         }
      };
   }

   StoreMap.$inject = [];
   function StoreMap() {
      var storeMapVM = this;
      storeMapVM.mapSettings = null;
      storeMapVM.events = null;
      storeMapVM.controls = null;
      storeMapVM.storeOutlines = null;
      storeMapVM.storeFeatures = null;
      storeMapVM.storeNodes = null;

      var mapControl = this.mapControl;
      var location = this.location;
      var layout = this.layout;

      initialize();

      ////////////////////

      function initialize() {
         initializeMapSettings(location);
         initializeEvents();
         initializeControls();
         initializeLayoutObjects(layout);
      }

      function initializeMapSettings(location) {
         storeMapVM.mapSettings = {
            center: _.convertLocationToPosition(location),
            zoom: 18,
            options: {
               draggableCursor: "pointer",
               draggingCursor: "pointer",
               disableDefaultUI: true,
               zoomControl: true,
               drawingControl: false,
               polygonOptions: {
                  strokeWeight: 1,
                  fillColor: "#194d4d",
                  fillOpacity: 1,
                  zIndex: 3,
                  clickable: true,
                  editable: false,
                  draggable: true
               },
               markerOptions: {
                  zIndex: 4,
                  draggable: true
               }
            }
         };
      }

      function initializeEvents() {
         storeMapVM.events = {
            map: {
               click: function (map, eventName, args) { mapControl.handleGObjectMouseEvent("mapClicked", "map", map, args[0]); },
               rightclick: function (map, eventName, args) { mapControl.handleGObjectMouseEvent("mapRightClicked", "map", map, args[0]); }
            },
            drawingManager: {
               polygoncomplete: function (drawingManager, eventName, model, args) { mapControl.polygonComplete(args[0]); }
            },
            polygon: {
               click: function (polygon, eventName, model, args) { mapControl.handleGObjectMouseEvent("polygonClicked", model.id, polygon, args[0]); },
               rightclick: function (polygon, eventName, model, args) { mapControl.handleGObjectMouseEvent("polygonRightClicked", model.id, polygon, args[0]); },
               dragend: function (polygon, eventName, model, args) { mapControl.handleGObjectMouseEvent("polygonDragEnd", model.id, polygon, args[0]); }
            },
            marker: {
               click: function (marker, eventName, model, args) { mapControl.handleGObjectMouseEvent("markerClicked", model.id, marker, args[0]); },
               rightclick: function (marker, eventName, model, args) { mapControl.handleGObjectMouseEvent("markerRightClicked", model.id, marker, args[0]); },
               dragend: function (marker, eventName, model, args) { mapControl.handleGObjectMouseEvent("markerDragEnd", model.id, marker, args[0]); }
            }
         };
      }

      function initializeControls() {
         storeMapVM.controls = {
            map: {},
            drawingManager: {},
            outline: {},
            feature: {},
            node: {}
         };
         mapControl.setControls(storeMapVM.controls);
      }

      function initializeLayoutObjects(layout) {
         storeMapVM.storeOutlines = [
            createGMapPolygonFromModel(layout.getOuterOutline(), {color: "#194d4d", opacity: 1}, 0, true),
            createGMapPolygonFromModel(layout.getInnerOutline(), {color: "#b3e5e6", opacity: 1}, 1, false)
         ];

         storeMapVM.storeFeatures = layout.getFeatures().map(function (grappPolygon) {
            return createGMapPolygonFromModel(grappPolygon, {color: "#194d4d", opacity: 1}, 2);
         });

         storeMapVM.storeNodes = layout.getNodes().map(function (node) {
            return createGMapMarkerFromNodeModel(node);
         });
      }

      function createGMapPolygonFromModel(polygonModel, fill, zIndex, fit) {
         return {
            id: polygonModel.id,
            path: polygonModel.vertices.map(_.convertLocationToPosition),
            fill: fill,
            zIndex: zIndex,
            fit: fit,
            clickable: polygonModel.isFeature,
            editable: false,
            draggable: polygonModel.isFeature
         };
      }

      function createGMapMarkerFromNodeModel(nodeModel) {
         return {
            id: nodeModel.id,
            position: _.convertLocationToPosition(nodeModel.location),
            icon: nodeModel.type.iconUrl,
            options: storeMapVM.mapSettings.options.markerOptions
         };
      }
   }
})();