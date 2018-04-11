(function() {
   "use strict";

   angular.module("App")
      .controller("StoresMap", StoresMap)
      .directive("storesMap", StoresMapDirective);

   function StoresMapDirective() {
      return {
         restrict: "E",
         controller: "StoresMap",
         controllerAs: "storesMapVM",
         templateUrl: "app/storesmap/StoresMap.html",
         scope: {},
         bindToController: {
            mapControl: "=",
            stores: "="
         }
      };
   }

   StoresMap.$inject = ["uiGmapUtil"];
   function StoresMap(uiGmapUtil) {
      var storesMapVM = this;
      storesMapVM.mapSettings = null;
      storesMapVM.events = null;
      storesMapVM.controls = null;
      storesMapVM.window = null;
      storesMapVM.showWindow = showWindow;

      var mapControl = null;
      var stores = [];

      this.$onInit = function() { initialize(this); };

      ////////////////////

      function initialize(binder) {
         mapControl = binder.mapControl;
         stores = binder.stores;

         initializeMapSettings();
         initializeControls();
         initializeStoreObjects();
         initializeStoreWindow();
         initializeEvents();
      }

      function initializeMapSettings() {
         storesMapVM.mapSettings = {
            center: { latitude: 0, longitude: 0 },
            options: {
               maxZoom: 18
            }
         };
      }

      function initializeControls() {
         storesMapVM.controls = {
            map: {},
            store: {}
         };
         mapControl.setControls(storesMapVM.controls);
      }

      function initializeStoreObjects() {
         storesMapVM.stores = stores.map(createGMapMarkerFromStoreModel);
      }

      function createGMapMarkerFromStoreModel(storeModel) {
         return {
            id: storeModel.id,
            position: _.convertLocationToPosition(storeModel.location),
            store: storeModel,
            options: {
               title: storeModel.name,
               icon: "content/img/marker_red_big.png"
            }
         };
      }

      function initializeStoreWindow() {
         storesMapVM.window = {
            show: false,
            coords: null,
            templateUrl: "app/storesmap/StoreWindow.html",
            templateParameter: null,
            options: {
               disableAutoPan: true,
               pixelOffset: new google.maps.Size(0, -25)
            }
         };
      }

      function initializeEvents() {
         storesMapVM.events = {
            map: {
               click: function(map, eventName, args) { mapControl.handleGObjectMouseEvent("mapClicked", "map", map, args[0]); },
               rightclick: function(map, eventName, args) { mapControl.handleGObjectMouseEvent("mapRightClicked", "map", map, args[0]); }
            },
            marker: {
               click: function(marker, eventName, model, args) {
                  updateWindowForStore(model);
                  mapControl.handleGObjectMouseEvent("markerClicked", model.id, marker, args[0]);
               },
               rightclick: function(marker, eventName, model, args) { mapControl.handleGObjectMouseEvent("markerRightClicked", model.id, marker, args[0]); }
            }
         };
      }

      function updateWindowForStore(model) {
         storesMapVM.window.coords = model.position;
         storesMapVM.window.templateParameter = model.store;
         showWindow(true);
      }

      function showWindow(show) {
         uiGmapUtil.onNextAngularTurn().then(function() {
            storesMapVM.window.show = show;
         });
      }
   }
})();