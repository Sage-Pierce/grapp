(function() {
   "use strict";

   angular.module("App")
      .service("AuthUser", AuthUser);

   AuthUser.$inject = ["AuthRoot", "halClient"];
   function AuthUser(AuthRoot, halClient) {
      var self = this;
      self.authenticate = authenticate;

      ////////////////////

      function authenticate(authorization) {
         return AuthRoot.afterLoad().then(function(authHref) {
            return halClient.$get("/auth/user", { transformUrl: _.urlTransformer(authHref), headers: { Authorization: authorization } });
         }).then(createModel);
      }

      function createModel(authUserRsc) {
         return _.mergeLeft(new AuthUserModel(authUserRsc), authUserRsc);
      }

      function AuthUserModel(authUserRsc) {
         var self = this;
         self.hasRole = hasRole;
         self.logOut = logOut;
         self.getName = getName;
         self.getAvatar = getAvatar;
         self.setAttributes = setAttributes;

         ////////////////////

         function hasRole(role) {
            return self.roles.indexOf(role) > -1;
         }

         function logOut() {
            return authUserRsc.$del("self");
         }

         function getName() {
            return self.attributes["NAME"] || self.attributes["EMAIL"];
         }

         function getAvatar() {
            return self.attributes["AVATAR"];
         }

         function setAttributes(attributes) {
            return authUserRsc.$put("self", attributes)
               .then(function(userRsc) {
                  self.attributes = userRsc.attributes;
                  return self;
               });
         }
      }
   }
})();