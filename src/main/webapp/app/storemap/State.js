(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
      $stateProvider
         .state("main.storeMap", {
            url: "storeMap/:storeId",
            abstract: true,
            resolve: {
               grappStore: ["$stateParams", "GrappStore", function($stateParams, GrappStore) {
                  return GrappStore.loadById($stateParams.storeId);
               }],
               grappStoreLayout: ["$stateParams", "grappStore", "GrappStoreLayout", function($stateParams, grappStore, GrappStoreLayout) {
                  return GrappStoreLayout.loadById(grappStore.layoutId);
               }],
               mapControl: ["GrappMapControl", function(GrappMapControl) {
                  return new GrappMapControl();
               }]
            },
            views: {
               "content": {
                  templateUrl: "app/storemap/MainStoreMap.html",
                  controller: "MainStoreMap",
                  controllerAs: "mainStoreMapVM"
               }
            }
         })
         .state("main.storeMap.layout", {
            url: "/layout",
            resolve: {
               mapControl: ["mapControl", function(mapControl) {
                  mapControl.setEventHandler(null);
                  return mapControl;
               }]
            },
            views: {
               "tabContent": {
                  templateUrl: "app/storemap/layout/MainStoreMapLayout.html",
                  controller: "MainStoreMapLayout",
                  controllerAs: "mainStoreMapLayoutVM"
               }
            }
         })
         .state("main.storeMap.nodes", {
            url: "/nodes",
            resolve: {
               mapControl: ["mapControl", function(mapControl) {
                  mapControl.setEventHandler(null);
                  return mapControl;
               }]
            },
            views: {
               "tabContent": {
                  templateUrl: "app/storemap/nodes/MainStoreMapNodes.html",
                  controller: "MainStoreMapNodes",
                  controllerAs: "mainStoreMapNodesVM"
               }
            }
         });
   }
})();