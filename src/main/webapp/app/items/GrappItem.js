(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappItem", GrappItem);

   GrappItem.$inject = ["GrappRoot"];
   function GrappItem(GrappRoot) {
      var self = this;
      self.createGeneralItem = createGeneralItem;
      self.loadAllGeneral = loadAllGeneral;

      ////////////////////

      function createGeneralItem(name) {
         return GrappRoot.createResourceModel("generalItems", {name: name}, createModel);
      }

      function loadAllGeneral() {
         return GrappRoot.loadResourceModels("generalItems", createModel);
      }

      function createModel(grappItemRsc) {
         return new GrappItemModel(grappItemRsc);
      }

      function GrappItemModel(grappItemRsc) {
         var self = this;
         self.commitAttributes = commitAttributes;
         self.addSubItem = addSubItem;
         self.delete = del;
         self.isGeneralItem = isGeneralItem;
         self.subItems = grappItemRsc.subItems.map(function(subItemRsc) { return GrappRoot.mergeResourceIntoModel(subItemRsc, new GrappItemModel(subItemRsc)); });

         ////////////////////

         function commitAttributes(attributes) {
            return GrappRoot.updateResourceByID("item", self.id, attributes).then(function(itemRsc) {
               self.name = itemRsc.name;
            });
         }

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
            return self.superItemId === null;
         }
      }
   }
})();