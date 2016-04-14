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
               grappStore: ["$stateParams", "GrappStore", function($stateParams, GrappStore) {
                  return GrappStore.loadById($stateParams.storeId);
               }],
               storeLayout: ["$stateParams", "grappStore", "StoreLayout", function($stateParams, grappStore, StoreLayout) {
                  return StoreLayout.loadById(grappStore.layoutId);
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