(function() {
   "use strict";

   angular.module("Grapp")
      .constant("GrappStoreNodeType", createEnum());

   function createEnum() {
      return {
         REGULAR: new GrappStoreNodeType("Regular", "content/img/marker_blue.png"),
         ENTRANCE: new GrappStoreNodeType("Entrance", "content/img/marker_green.png"),
         EXIT: new GrappStoreNodeType("Exit", "content/img/marker_red.png")
      };
   }

   function GrappStoreNodeType(displayString, iconUrl) {
      this.displayString = displayString;
      this.iconUrl = iconUrl;
   }
})();