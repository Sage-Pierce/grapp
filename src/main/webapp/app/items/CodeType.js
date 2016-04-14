(function() {
   "use strict";

   angular.module("Grapp")
      .constant("CodeType", createEnum());

   function createEnum() {
      return {
         GTIN: new CodeType("GTIN"),
         UPC: new CodeType("UPC"),
         NACS: new CodeType("NACS"),
         MANUAL: new CodeType("MANUAL")
      };
   }

   function CodeType(displayString) {
      this.displayString = displayString;
   }
})();