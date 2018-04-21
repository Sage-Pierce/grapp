(function () {
   "use strict";

   angular.module("App")
      .service("AuthUser", AuthUser);

   AuthUser.$inject = ["AuthRoot", "halClient"];
   function AuthUser(AuthRoot, halClient) {
      var self = this;
      self.authorize = authorize;
      self.authenticate = authenticate;
      self.reauthenticate = reauthenticate;

      ////////////////////

      function authorize() {
         AuthRoot.authorize();
      }

      function authenticate(authorization) {
         return AuthRoot.afterLoad().then(function(authHref) {
            return getAuthUser(authHref, authorization);
         }).then(createModel);
      }

      function reauthenticate(authorization) {
         return AuthRoot.afterLoad().then(function(authHref) {
            return AuthRoot.reauthenticate(authorization).then(function(reauthenticatedAuthorization) {
               return getAuthUser(authHref, reauthenticatedAuthorization);
            });
         }).then(createModel);
      }

      function getAuthUser(authHref, authorization) {
         return halClient.$get("/auth/user", {transformUrl: _.urlTransformer(authHref), headers: {Authorization: authorization}});
      }

      function createModel(authUserRsc) {
         return _.mergeLeft(new AuthUserModel(authUserRsc), authUserRsc);
      }

      function AuthUserModel(authUserRsc) {
         var self = this;
         self.attributes = authUserRsc.attributes;
         self.roles = authUserRsc.roles;
         self.hasRole = hasRole;
         self.logOut = logOut;
         self.getName = getName;
         self.getEmail = getEmail;
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
            return self.attributes.NAME || getEmail();
         }

         function getEmail() {
            return self.attributes.EMAIL;
         }

         function getAvatar() {
            return self.attributes.AVATAR;
         }

         function setAttributes(attributes) {
            return authUserRsc.$put("self", attributes)
               .then(function (userRsc) {
                  self.attributes = userRsc.attributes;
                  return self;
               });
         }
      }
   }
})();