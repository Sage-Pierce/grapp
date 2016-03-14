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
         templateUrl: "app/mapping/GrappMap.html",
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
                  draggable: false
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
               click: function (map, eventName, args) { grappMapControl.handleGObjectMouseEvent("mapClicked", "map", map, args[0]); }
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
            convertGrappPolygonToGMapPolygon(layout.outerOutline, {color: "#194d4d", opacity: 1}, 0, true),
            convertGrappPolygonToGMapPolygon(layout.innerOutline, {color: "#b3e5e6", opacity: 1}, 1, false)
         ];

         grappMapVM.storeFeatures = layout.features.map(function (grappPolygon) {
            return convertGrappPolygonToGMapPolygon(grappPolygon, {color: "#194d4d", opacity: 1}, 2);
         });

         grappMapVM.storeNodes = layout.nodes.map(function (node) {
            return convertGrappNodeToGMapMarker(node);
         });
      }

      function convertGrappPolygonToGMapPolygon(grappPolygon, fill, zIndex, fit) {
         return {
            id: grappPolygon.id,
            path: grappPolygon.vertices.map(_.convertLocationToPosition),
            fill: fill,
            zIndex: zIndex,
            fit: fit,
            clickable: grappPolygon.isFeature,
            editable: false,
            draggable: false
         };
      }

      function convertGrappNodeToGMapMarker(node) {
         return {
            id: node.id,
            position: _.convertLocationToPosition(node.location),
            icon: node.type.iconUrl,
            options: grappMapVM.mapSettings.options.markerOptions
         };
      }
   }
})();