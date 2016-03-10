(function() {
   "use strict";

   angular.module("Grapp")
      .value("GrappStoreNodeType", createEnum());

   function createEnum() {
      return {
         REGULAR: new GrappStoreNodeType("REGULAR", "Regular", "content/img/marker_blue.png"),
         ENTRANCE: new GrappStoreNodeType("ENTRANCE", "Entrance", "content/img/marker_green.png"),
         EXIT: new GrappStoreNodeType("EXIT", "Exit", "content/img/marker_red.png"),
         values: function() {
            return _.pick(this, function(value) {
               return value instanceof GrappStoreNodeType;
            });
         },
         fromCode: function(code) {
            for (var key in this) {
               var value = this[key];
               if (value instanceof GrappStoreNodeType && value.code === code) {
                  return value;
               }
            }
         }
      };
   }

   function GrappStoreNodeType(code, displayString, iconUrl) {
      this.code = code;
      this.displayString = displayString;
      this.iconUrl = iconUrl;
   }
})();