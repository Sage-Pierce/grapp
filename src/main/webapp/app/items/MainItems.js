(function() {
   "use strict";

   angular.module("Grapp")
      .controller("MainItems", MainItems);

   MainItems.$inject = [];
   function MainItems() {
      var mainItemsVM = this;
      mainItemsVM.itemTree = {};
      mainItemsVM.createItem = createItem;

      initialize();

      ////////////////////

      function initialize() {
         mainItemsVM.itemTree = {
            expandOn: {
               field: "name",
               displayName: "Item Name",
               cellTemplateScope: {
                  createItem: mainItemsVM.createItem
               },
               sortable: true,
               sortingType: "string",
               filterable: true
            },
            data: [
               {id: 123, parentId: null, name: "Bread", children: [
                  {id: 321, parentId: 123, name: "Pita Bread"}
               ]}
            ]
         };
      }

      function createItem(branch) {
         console.log("CLICKED");
      }
   }
})();