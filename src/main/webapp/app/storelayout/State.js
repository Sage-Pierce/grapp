(function() {
   "use strict";

   angular.module("App")
      .config(state);

   state.$inject = ["$stateProvider"];
   function state($stateProvider) {
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
                  templateUrl: "app/storelayout/MainStoreLayout.html",
                  controller: "MainStoreLayout",
                  controllerAs: "mainStoreLayoutVM"
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
                  templateUrl: "app/storelayout/MainStoreLayoutFeatures.html",
                  controller: "MainStoreLayoutFeatures",
                  controllerAs: "mainStoreLayoutFeaturesVM"
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
                  templateUrl: "app/storelayout/MainStoreLayoutNodes.html",
                  controller: "MainStoreLayoutNodes",
                  controllerAs: "mainStoreLayoutNodesVM"
               }
            }
         });
   }
})();