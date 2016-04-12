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

      function createGeneralItem(params) {
         return GrappRoot.createResourceModel("generalItems", params, createModel);
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

         function addSubItem(params) {
            return GrappRoot.createResourceModel("items", _.merge({superItemId: self.id}, params), createModel)
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