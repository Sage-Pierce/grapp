(function() {
   "use strict";

   angular.module("App")
      .service("User", User);

   User.$inject = ["Root"];
   function User(Root) {
      var self = this;
      self.loadByEmail = loadByEmail;
      self.load = load;

      ////////////////////

      function loadByEmail(email) {
         return Root.loadResourceModel("user", {email: email}, createModel);
      }

      function load(userRsc) {
         return _.merge(createModel(userRsc), userRsc);
      }

      function createModel(userRsc) {
         return new UserModel(userRsc);
      }

      function UserModel(userRsc) {
         var self = this;
         self.setDisplayName = setDisplayName;

         ////////////////////

         function setDisplayName(displayName) {
            return userRsc.$put("self", {name: displayName})
               .then(function() { self.name = displayName; });
         }
      }
   }
})();