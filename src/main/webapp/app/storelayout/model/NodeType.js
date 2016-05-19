(function() {
   "use strict";

   angular.module("App")
      .constant("NodeType", createEnum());

   function createEnum() {
      return {
         ENTRANCE: new NodeType("E", "Entrance", "content/img/marker_green.png"),
         CHECKOUT: new NodeType("C", "Checkout", "content/img/marker_red.png"),
         REGULAR: new NodeType("R", "Regular", "content/img/marker_blue.png")
      };
   }

   function NodeType(code, displayString, iconUrl) {
      this.code = code;
      this.displayString = displayString;
      this.iconUrl = iconUrl;
   }
})();