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
            control: "=",
            grappStoreLocation: "=?",
            grappStoreLayout: "="
         }
      };
   }

   GrappMap.$inject = [];
   function GrappMap() {
      var grappMapVM = this;
      grappMapVM.center = null;
      grappMapVM.zoom = 3;
      grappMapVM.options = null;
      grappMapVM.mapEvents = null;
      grappMapVM.drawingManagerEvents = null;
      grappMapVM.polygonEvents = null;
      grappMapVM.mapControl = {};
      grappMapVM.drawingManagerControl = {};
      grappMapVM.outlineControl = {};
      grappMapVM.featureControl = {};
      grappMapVM.storeOutlines = null;
      grappMapVM.storeFeatures = null;

      var control = this.control;
      var grappStoreLocation = this.grappStoreLocation;
      var grappStoreLayout = this.grappStoreLayout;

      initialize();

      ////////////////////

      function initialize() {
         grappMapVM.center = {
            latitude: grappStoreLocation ? grappStoreLocation.latitude : 0,
            longitude: grappStoreLocation ? grappStoreLocation.longitude : 0
         };

         grappMapVM.zoom = grappStoreLocation ? 18 : 3;

         grappMapVM.options = {
            draggableCursor: "pointer",
            draggingCursor: "pointer",
            disableDefaultUI: true,
            zoomControl: true,
            drawingControl: false,
            polygonOptions: {
               strokeWeight: 1,
               fillColor: "#194d4d",
               fillOpacity: 1,
               zIndex: 3
            }
         };

         grappMapVM.mapEvents = {
            click: function(map, eventName, args) {
               control.mapClicked(map, args[0]);
            }
         };

         grappMapVM.drawingManagerEvents = {
            polygoncomplete: function(drawingManager, eventName, model, args) { control.polygonComplete(args[0]); }
         };

         grappMapVM.polygonEvents = {
            click: function(polygon, eventName, model, args) { control.polygonClicked(model.id, polygon, args[0]); },
            rightclick: function(polygon, eventName, model, args) { control.polygonRightClicked(model.id, polygon, args[0]); },
            dragend: function (polygon, eventName, model, args) { control.polygonDragEnd(model.id, polygon, args[0]); }
         };

         control.setMapControl(grappMapVM.mapControl);
         control.setDrawingManagerControl(grappMapVM.drawingManagerControl);
         control.setOutlineControl(grappMapVM.outlineControl);
         control.setFeatureControl(grappMapVM.featureControl);

         grappMapVM.storeOutlines = [
            convertGrappPolygonTogMapPolygon(grappStoreLayout.outerOutline, {color: "#194d4d", opacity: 1}, 0, true),
            convertGrappPolygonTogMapPolygon(grappStoreLayout.innerOutline, {color: "#b3e5e6", opacity: 1}, 1, false)
         ];

         grappMapVM.storeFeatures = grappStoreLayout.features.map(function(grappPolygon) {
            return convertGrappPolygonTogMapPolygon(grappPolygon, {color: "#194d4d", opacity: 1}, 2);
         });
      }

      function convertGrappPolygonTogMapPolygon(grappPolygon, fill, zIndex, fit) {
         return {
            id: grappPolygon.id,
            path: convertGrappPolygonVerticesTogMapPolygonPath(grappPolygon.vertices),
            fill: fill,
            zIndex: zIndex,
            fit: fit,
            clickable: grappPolygon.isFeature,
            editable: false,
            draggable: false
         };
      }

      function convertGrappPolygonVerticesTogMapPolygonPath(vertices) {
         return vertices.map(function(vertex) {
            return {
               latitude: vertex.lat,
               longitude: vertex.lng
            };
         });
      }
   }
})();