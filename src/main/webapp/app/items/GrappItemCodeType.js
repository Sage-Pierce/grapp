(function() {
   "use strict";

   angular.module("Grapp")
      .constant("GrappItemCodeType", createEnum());

   function createEnum() {
      return {
         GTIN: new GrappItemCodeType("GTIN"),
         UPC: new GrappItemCodeType("UPC"),
         NACS: new GrappItemCodeType("NACS"),
         MANUAL: new GrappItemCodeType("MANUAL")
      };
   }

   function GrappItemCodeType(displayString) {
      this.displayString = displayString;
   }
})();