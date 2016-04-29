(function() {
   "use strict";

   angular.module("App")
      .service("Item", Item);

   Item.$inject = ["Root"];
   function Item(Root) {
      var self = this;
      self.createGeneralItem = createGeneralItem;
      self.importItems = importItems;
      self.loadAllGeneral = loadAllGeneral;

      ////////////////////

      function createGeneralItem(params) {
         return Root.createResourceModel("generalItems", params, createModel);
      }

      function importItems(data) {
         return Root.afterLoad().then(function(rootRsc) {
            return rootRsc.$put("importItems", {type: "NACS"}, data, {headers: {"Content-Type": "text/plain"}}).then(loadAllGeneral);
         });
      }

      function loadAllGeneral() {
         return Root.loadResourceModels("generalItems", createModel);
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
            return Root.createResourceModel("items", _.merge({superItemCode: self.primaryCode}, params), createModel)
               .then(function(itemModel) {
                  self.subItems.push(itemModel);
                  return itemModel;
               });
         }

         function del() {
            return Root.deleteResource("item", {primaryCode: self.primaryCode});
         }

         function isGeneralItem() {
            return self.superItemCode === null;
         }
      }
   }
})();