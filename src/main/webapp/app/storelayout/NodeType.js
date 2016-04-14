(function() {
   "use strict";

   angular.module("Grapp")
      .constant("NodeType", createEnum());

   function createEnum() {
      return {
         REGULAR: new NodeType("Regular", "content/img/marker_blue.png"),
         ENTRANCE: new NodeType("Entrance", "content/img/marker_green.png"),
         EXIT: new NodeType("Exit", "content/img/marker_red.png")
      };
   }

   function NodeType(displayString, iconUrl) {
      this.displayString = displayString;
      this.iconUrl = iconUrl;
   }
})();