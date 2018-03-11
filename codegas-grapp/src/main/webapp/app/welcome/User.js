(function() {
   "use strict";

   angular.module("App")
      .service("User", User);

   User.$inject = ["UsersRoot"];
   function User(UsersRoot) {
      var self = this;
      self.loadByEmail = loadByEmail;
      self.load = load;

      ////////////////////

      function loadByEmail(email) {
         return UsersRoot.loadResourceModel("user", {email: email}, createModel);
      }

      function load(userRsc) {
         return _.mergeLeft(createModel(userRsc), userRsc);
      }

      function createModel(userRsc) {
         return new UserModel(userRsc);
      }

      function UserModel(userRsc) {
         var self = this;
         self.setAttributes = setAttributes;

         ////////////////////

         function setAttributes(attributes) {
            return userRsc.$put("self", attributes)
               .then(function(userRsc) {
                  self.name = userRsc.name;
                  return self;
               });
         }
      }
   }
})();