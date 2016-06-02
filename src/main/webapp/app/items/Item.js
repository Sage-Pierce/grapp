(function() {
   "use strict";

   angular.module("App")
      .service("Item", Item);

   Item.$inject = ["ItemManagementRoot"];
   function Item(ItemManagementRoot) {
      var self = this;
      self.createGeneralItem = createGeneralItem;
      self.loadAllGeneral = loadAllGeneral;

      ////////////////////

      function createGeneralItem(params) {
         return ItemManagementRoot.createResourceModel("generalItems", params, createModel);
      }

      function loadAllGeneral() {
         return ItemManagementRoot.loadResourceModels("generalItems", createModel);
      }

      function createModel(item) {
         return new ItemModel(item);
      }

      function ItemModel(item) {
         var self = this;
         self.subItems = item.subItems.map(function(subItem) { return _.mergeLeft(new ItemModel(subItem), subItem); });
         self.addSubItem = addSubItem;
         self.delete = del;
         self.isGeneralItem = isGeneralItem;

         ////////////////////

         function addSubItem(params) {
            return ItemManagementRoot.createResourceModel("items", _.merge({superItemCode: self.primaryCode}, params), createModel)
               .then(function(itemModel) {
                  self.subItems.push(itemModel);
                  return itemModel;
               });
         }

         function del() {
            return ItemManagementRoot.deleteResource("item", {primaryCode: self.primaryCode});
         }

         function isGeneralItem() {
            return self.superItemCode === null;
         }
      }
   }
})();