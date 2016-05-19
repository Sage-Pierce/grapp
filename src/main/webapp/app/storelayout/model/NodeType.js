(function() {
   "use strict";

   angular.module("App")
      .constant("NodeType", createEnum());

   function createEnum() {
      return {
         REGULAR: new NodeType("R", "Regular", "content/img/marker_blue.png"),
         ENTRANCE: new NodeType("E", "Entrance", "content/img/marker_green.png"),
         EXIT: new NodeType("X", "Exit", "content/img/marker_red.png")
      };
   }

   function NodeType(code, displayString, iconUrl) {
      this.code = code;
      this.displayString = displayString;
      this.iconUrl = iconUrl;
   }
})();