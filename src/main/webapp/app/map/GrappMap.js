(function() {
   "use strict";

   angular.module("Grapp")
      .controller("GrappMap", GrappMap)
      .directive("grappMap", GrappMapDirective);

   function GrappMapDirective() {
      return {
         restrict: "E",
         controller: "GrappMap",
         controllerAs: "grappMapVM",
         templateUrl: "app/map/GrappMap.html",
         scope: {},
         bindToController: {
            grappMapControl: "=",
            grappStoreLocation: "=",
            grappLayout: "="
         }
      };
   }

   GrappMap.$inject = [];
   function GrappMap() {
      var grappMapVM = this;
      grappMapVM.mapSettings = null;
      grappMapVM.events = null;
      grappMapVM.controls = null;
      grappMapVM.storeOutlines = null;
      grappMapVM.storeFeatures = null;
      grappMapVM.storeNodes = null;

      var grappMapControl = this.grappMapControl;
      var grappStoreLocation = this.grappStoreLocation;
      var grappLayout = this.grappLayout;

      initialize();

      ////////////////////

      function initialize() {
         initializeMapSettings(grappStoreLocation);
         initializeEvents();
         initializeControls();
         initializeLayoutObjects(grappLayout);
      }

      function initializeMapSettings(location) {
         grappMapVM.mapSettings = {
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
         grappMapVM.events = {
            map: {
               click: function (map, eventName, args) { grappMapControl.handleGObjectMouseEvent("mapClicked", "map", map, args[0]); },
               rightclick: function (map, eventName, args) { grappMapControl.handleGObjectMouseEvent("mapRightClicked", "map", map, args[0]); }
            },
            drawingManager: {
               polygoncomplete: function (drawingManager, eventName, model, args) { grappMapControl.polygonComplete(args[0]); }
            },
            polygon: {
               click: function (polygon, eventName, model, args) { grappMapControl.handleGObjectMouseEvent("polygonClicked", model.id, polygon, args[0]); },
               rightclick: function (polygon, eventName, model, args) { grappMapControl.handleGObjectMouseEvent("polygonRightClicked", model.id, polygon, args[0]); },
               dragend: function (polygon, eventName, model, args) { grappMapControl.handleGObjectMouseEvent("polygonDragEnd", model.id, polygon, args[0]); }
            },
            marker: {
               click: function (marker, eventName, model, args) { grappMapControl.handleGObjectMouseEvent("markerClicked", model.id, marker, args[0]); },
               rightclick: function (marker, eventName, model, args) { grappMapControl.handleGObjectMouseEvent("markerRightClicked", model.id, marker, args[0]); },
               dragend: function (marker, eventName, model, args) { grappMapControl.handleGObjectMouseEvent("markerDragEnd", model.id, marker, args[0]); }
            }
         };
      }

      function initializeControls() {
         grappMapVM.controls = {
            map: {},
            drawingManager: {},
            outline: {},
            feature: {},
            node: {}
         };
         grappMapControl.setControls(grappMapVM.controls);
      }

      function initializeLayoutObjects(layout) {
         grappMapVM.storeOutlines = [
            createGMapPolygonFromModel(layout.getOuterOutline(), {color: "#194d4d", opacity: 1}, 0, true),
            createGMapPolygonFromModel(layout.getInnerOutline(), {color: "#b3e5e6", opacity: 1}, 1, false)
         ];

         grappMapVM.storeFeatures = layout.getFeatures().map(function (grappPolygon) {
            return createGMapPolygonFromModel(grappPolygon, {color: "#194d4d", opacity: 1}, 2);
         });

         grappMapVM.storeNodes = layout.getNodes().map(function (node) {
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
            options: grappMapVM.mapSettings.options.markerOptions
         };
      }
   }
})();