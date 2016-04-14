(function() {
   "use strict";

   angular.module("App")
      .service("User", User);

   User.$inject = ["Root"];
   function User(Root) {
      var self = this;
      self.loadById = loadById;
      self.load = load;

      ////////////////////

      function loadById(userId) {
         return Root.loadResourceModelById("user", userId, createModel);
      }

      function load(userRsc) {
         return Root.mergeResourceIntoModel(userRsc, createModel(userRsc));
      }

      function createModel(userRsc) {
         return new UserModel(userRsc);
      }

      function UserModel(userRsc) {
         var self = this;
         self.updateDisplayName = updateDisplayName;

         ////////////////////

         function updateDisplayName(displayName) {
            return userRsc.$put("self", {name: displayName})
               .then(function() { self.name = displayName; });
         }
      }
   }
})();