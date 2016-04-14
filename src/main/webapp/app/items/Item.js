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
         return Root.afterLoad().then(function(root) {
            return root.$put("importItems", {type: "NACS"}, data, {headers: {"Content-Type": "text/plain"}}).then(loadAllGeneral);
         });
      }

      function loadAllGeneral() {
         return Root.loadResourceModels("generalItems", createModel);
      }

      function createModel(itemRsc) {
         return new ItemModel(itemRsc);
      }

      function ItemModel(itemRsc) {
         var self = this;
         self.addSubItem = addSubItem;
         self.delete = del;
         self.isGeneralItem = isGeneralItem;
         self.subItems = itemRsc.subItems.map(function(subItemRsc) { return Root.mergeResourceIntoModel(subItemRsc, new ItemModel(subItemRsc)); });

         ////////////////////

         function addSubItem(params) {
            return Root.createResourceModel("items", _.merge({superItemId: self.id}, params), createModel)
               .then(function(itemModel) {
                  self.subItems.push(itemModel);
                  return itemModel;
               });
         }

         function del() {
            return Root.deleteResourceById("item", self.id);
         }

         function isGeneralItem() {
            return self.superItemName === null;
         }
      }
   }
})();