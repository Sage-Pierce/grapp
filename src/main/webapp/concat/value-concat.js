(function() {
   "use strict";

   angular.module("Grapp")
      .value("GrappStoreNodeType", createEnum());

   function createEnum() {
      return {
         REGULAR: new GrappStoreNodeType("REGULAR", "Regular"),
         ENTRANCE: new GrappStoreNodeType("ENTRANCE", "Entrance"),
         EXIT: new GrappStoreNodeType("EXIT", "Exit"),
         values: function() {
            return _.pick(this, function(value) {
               return value instanceof GrappStoreNodeType;
            });
         },
         fromCode: function(code) {
            for (var key in this) {
               var value = this.key;
               if (value instanceof GrappStoreNodeType && value.code === code) {
                  return value;
               }
            }
         }
      };
   }

   function GrappStoreNodeType(code, displayString) {
      this.code = code;
      this.displayString = displayString;
   }
})();