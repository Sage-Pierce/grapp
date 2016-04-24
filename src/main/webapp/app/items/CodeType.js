(function() {
   "use strict";

   angular.module("App")
      .constant("CodeType", createEnum());

   function createEnum() {
      return {
         GTIN: new CodeType("GTIN"),
         UPC: new CodeType("UPC"),
         NACS: new CodeType("NACS"),
         MANUAL: new CodeType("Manual"),
         RANDOM: new CodeType("Random")
      };
   }

   function CodeType(displayString) {
      this.displayString = displayString;
   }
})();