(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappItem", GrappItem);

   GrappItem.$inject = ["Root"];
   function GrappItem(Root) {
      var self = this;
      self.createGeneralItem = createGeneralItem;
      self.importItems = importItems;
      self.loadAllGeneral = loadAllGeneral;

      ////////////////////

      function createGeneralItem(params) {
         return Root.createResourceModel("generalItems", params, createModel);
      }

      function importItems(data) {
         return Root.afterLoad().then(function(grappRoot) {
            return grappRoot.$put("importItems", {type: "NACS"}, data, {headers: {"Content-Type": "text/plain"}}).then(loadAllGeneral);
         });
      }

      function loadAllGeneral() {
         return Root.loadResourceModels("generalItems", createModel);
      }

      function createModel(grappItemRsc) {
         return new GrappItemModel(grappItemRsc);
      }

      function GrappItemModel(grappItemRsc) {
         var self = this;
         self.addSubItem = addSubItem;
         self.delete = del;
         self.isGeneralItem = isGeneralItem;
         self.subItems = grappItemRsc.subItems.map(function(subItemRsc) { return Root.mergeResourceIntoModel(subItemRsc, new GrappItemModel(subItemRsc)); });

         ////////////////////

         function addSubItem(params) {
            return Root.createResourceModel("items", _.merge({superItemId: self.id}, params), createModel)
               .then(function(itemModel) {
                  self.subItems.push(itemModel);
                  return itemModel;
               });
         }

         function del() {
            return Root.deleteResourceByID("item", self.id);
         }

         function isGeneralItem() {
            return self.superItemName === null;
         }
      }
   }
})();