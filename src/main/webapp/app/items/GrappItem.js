(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappItem", GrappItem);

   GrappItem.$inject = ["GrappRoot"];
   function GrappItem(GrappRoot) {
      var self = this;
      self.createGeneralItem = createGeneralItem;
      self.importItems = importItems;
      self.loadAllGeneral = loadAllGeneral;

      ////////////////////

      function createGeneralItem(name) {
         return GrappRoot.createResourceModel("generalItems", {name: name}, createModel);
      }

      function importItems(data) {
         return GrappRoot.afterLoad().then(function(grappRoot) {
            return grappRoot.$put("importItems", {type: "NACS"}, data, {headers: {"Content-Type": "text/plain"}}).then(loadAllGeneral);
         });
      }

      function loadAllGeneral() {
         return GrappRoot.loadResourceModels("generalItems", createModel);
      }

      function createModel(grappItemRsc) {
         return new GrappItemModel(grappItemRsc);
      }

      function GrappItemModel(grappItemRsc) {
         var self = this;
         self.addSubItem = addSubItem;
         self.delete = del;
         self.isGeneralItem = isGeneralItem;
         self.subItems = grappItemRsc.subItems.map(function(subItemRsc) { return GrappRoot.mergeResourceIntoModel(subItemRsc, new GrappItemModel(subItemRsc)); });

         ////////////////////

         function addSubItem(name) {
            return GrappRoot.createResourceModel("items", {superItemId: self.id, name: name}, createModel)
               .then(function(itemModel) {
                  self.subItems.push(itemModel);
                  return itemModel;
               });
         }

         function del() {
            return GrappRoot.deleteResourceByID("item", self.id);
         }

         function isGeneralItem() {
            return self.superItemName === null;
         }
      }
   }
})();