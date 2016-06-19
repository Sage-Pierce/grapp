(function() {
   "use strict";

   angular.module("App")
      .service("Item", Item);

   Item.$inject = ["$q", "ItemManagementRoot"];
   function Item($q, ItemManagementRoot) {
      var self = this;
      self.createGeneralItem = createGeneralItem;
      self.loadAllGeneral = loadAllGeneral;

      ////////////////////

      function createGeneralItem(params) {
         return ItemManagementRoot.createResourceModel("generalItems", params, createRecentModel);
      }

      function loadAllGeneral() {
         return ItemManagementRoot.loadResourceModels("generalItems", createModel);
      }

      function createRecentModel(item) {
         return new ItemModel(item, true);
      }

      function createModel(item) {
         return new ItemModel(item);
      }

      function ItemModel(item, recent) {
         var self = this;
         self.resource = null;
         self.recent = recent || false;
         self.subItems = item.subItems.map(function(subItem) { return _.mergeLeft(new ItemModel(subItem), subItem); });
         self.addSubItem = addSubItem;
         self.makeGeneral = makeGeneral;
         self.move = move;
         self.setAttributes = setAttributes;
         self.delete = del;
         self.isRecent = isRecent;

         ////////////////////

         function addSubItem(params) {
            return ItemManagementRoot.createResourceModel("items", _.merge({superItemCode: self.primaryCode}, params), createModel)
               .then(function(itemModel) {
                  itemModel.recent = true;
                  self.subItems.push(itemModel);
                  return itemModel;
               });
         }

         function makeGeneral() {
            return fetchResource().then(function(itemRsc) {
               return itemRsc.$put("makeGeneral");
            });
         }

         function move(superItem) {
            return fetchResource().then(function(itemRsc) {
               return itemRsc.$put("move", {superItemCode: superItem.primaryCode});
            });
         }

         function setAttributes(attributes) {
            return ItemManagementRoot.updateResource("item", {primaryCode: self.primaryCode}, _.merge(attributes, self)).then(function(itemRsc) {
               self.name = itemRsc.name;
               return cacheResource(itemRsc);
            });
         }

         function del() {
            return ItemManagementRoot.deleteResource("item", {primaryCode: self.primaryCode});
         }

         function isRecent() {
            return self.recent;
         }

         function fetchResource() {
            return self.resource ? $q.resolve(self.resource) : ItemManagementRoot.loadResource("item", {primaryCode: self.primaryCode}).then(cacheResource);
         }

         function cacheResource(resource) {
            self.resource = resource;
            return self.resource;
         }
      }
   }
})();