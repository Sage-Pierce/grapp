(function() {
   "use strict";

   angular.module("App")
      .constant("NodeType", createEnum());

   function createEnum() {
      return {
         ENTRANCE: new NodeType("E", "Entrance", "content/img/marker_green.png", true),
         CHECKOUT: new NodeType("C", "Checkout", "content/img/marker_red.png", true),
         REGULAR: new NodeType("R", "Regular", "content/img/marker_blue.png", false)
      };
   }

   function NodeType(code, displayString, iconUrl, isSingleton) {
      this.code = code;
      this.displayString = displayString;
      this.iconUrl = iconUrl;
      this.isSingleton = isSingleton;
   }
})();