(function() {
   "use strict";

   angular.module("App")
      .controller("MainShoppingPlan", MainShoppingPlan);

   MainShoppingPlan.$inject = ["$uibModal", "StoreMapControl", "shoppingList", "store", "shoppingLayout"];
   function MainShoppingPlan($uibModal, StoreMapControl, shoppingList, store, shoppingLayout) {
      var mainShoppingPlanVM = this;
      mainShoppingPlanVM.mapControl = new StoreMapControl();
      mainShoppingPlanVM.location = store.location;
      mainShoppingPlanVM.shoppingLayout = shoppingLayout;

      initialize();

      ////////////////////

      function initialize() {
         shoppingLayout.generateShoppingPath()
            .then(createShoppingPathPolyline)
            .then(mainShoppingPlanVM.mapControl.addPath);
         notifyOfAnyUnmappedItems();
      }

      function createShoppingPathPolyline(path) {
         return new google.maps.Polyline({
            path: path.locations,
            strokeColor: "#339933",
            icons: [{
               icon: { path: google.maps.SymbolPath.FORWARD_OPEN_ARROW },
               offset: "10px",
               repeat: "50px"
            }]
         });
      }

      function notifyOfAnyUnmappedItems() {
         var unmappedItems = findUnmappedItems();
         if (unmappedItems.length > 0) {
            showModalUnmappedItems(unmappedItems);
         }
      }

      function findUnmappedItems() {
         var itemMap = _.fromPairs(shoppingList.items.map(function(item) { return [item.code, item]; }));
         shoppingLayout.getNodes().forEach(function(node) {
            node.getItems().forEach(function(nodeItem) {
               delete itemMap[nodeItem.item.code];
            });
         });
         return _.values(itemMap);
      }

      function showModalUnmappedItems(unmappedItems) {
         $uibModal.open({
            animation: true,
            templateUrl: "app/shoppingplan/ModalUnmappedItems.html",
            controller: "ModalUnmappedItems",
            controllerAs: "modalUnmappedItemsVM",
            resolve: {
               items: function() { return unmappedItems; }
            }
         });
      }
   }
})();