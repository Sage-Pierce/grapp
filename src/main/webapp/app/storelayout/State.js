(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
      $stateProvider
         .state("main.storeLayout", {
            url: "storeLayout/:storeId",
            abstract: true,
            resolve: {
               store: ["$stateParams", "Store", function($stateParams, Store) {
                  return Store.loadById($stateParams.storeId);
               }],
               storeLayout: ["$stateParams", "store", "StoreLayout", function($stateParams, store, StoreLayout) {
                  return StoreLayout.loadById(store.layoutId);
               }],
               mapControl: ["StoreMapControl", function(StoreMapControl) {
                  return new StoreMapControl();
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/storelayout/MainStoreMap.html",
                  controller: "MainStoreMap",
                  controllerAs: "mainStoreMapVM"
               }
            }
         })
         .state("main.storeLayout.features", {
            url: "/features",
            resolve: {
               mapControl: ["mapControl", function(mapControl) {
                  mapControl.setEventHandler(null);
                  return mapControl;
               }]
            },
            views: {
               "tabContent": {
                  templateUrl: "app/storelayout/features/MainStoreMapFeatures.html",
                  controller: "MainStoreMapFeatures",
                  controllerAs: "mainStoreMapFeaturesVM"
               }
            }
         })
         .state("main.storeLayout.nodes", {
            url: "/nodes",
            resolve: {
               mapControl: ["mapControl", function(mapControl) {
                  mapControl.setEventHandler(null);
                  return mapControl;
               }]
            },
            views: {
               "tabContent": {
                  templateUrl: "app/storelayout/nodes/MainStoreMapNodes.html",
                  controller: "MainStoreMapNodes",
                  controllerAs: "mainStoreMapNodesVM"
               }
            }
         });
   }
})();