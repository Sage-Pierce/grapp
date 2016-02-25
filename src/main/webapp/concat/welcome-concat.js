(function() {
   "use strict";

   angular.module("Grapp")
      .service("GrappLogIn", GrappLogIn)
      .run(logInCachedUser);

   GrappLogIn.$inject = ["$cookies", "$q", "GrappRoot", "GrappUser"];
   function GrappLogIn($cookies, $q, GrappRoot, GrappUser) {
      var self = this;
      self.logInCachedUser = logInCachedUser;
      self.logInWithOAuth = logInWithOAuth;
      self.afterLogIn = afterLogIn;
      self.logOut = logOut;
      self.isUserLoggedIn = isUserLoggedIn;

      var deferred = $q.defer();
      var userLoggedIn = false;

      ////////////////////

      function logInCachedUser() {
         var grappUserID = $cookies.get("grapp-user-id");
         if (grappUserID) {
            GrappUser.loadByID(grappUserID).then(resolveUser, function() {
               deferred.reject("Problem logging User in on Server.");
            });
         }
         else {
            deferred.reject("NO CACHED USER.");
         }
      }

      function logInWithOAuth(oAuthProvider) {
         deferred = $q.defer();
         OAuth.popup(oAuthProvider).done(function(oauthResponse) {
            oauthResponse.me().done(function(me) {
               return logIn(me.email, me.avatar).then(resolveUser);
            });
         }).fail(function(oauthError) {
            deferred.reject(oauthError);
         });
         return deferred.promise;
      }

      function afterLogIn() {
         return deferred.promise;
      }

      function logOut() {
         $cookies.remove("grapp-user-id");
         deferred = $q.defer();
         deferred.reject("NO USER LOGGED IN");
         userLoggedIn = false;
      }

      function isUserLoggedIn() {
         return userLoggedIn;
      }

      function logIn(email, avatar) {
         return GrappRoot.logIn({email: email, avatar: avatar}).then(cacheUser).then(GrappUser.load);
      }

      function cacheUser(grappUser) {
         $cookies.put("grapp-user-id", grappUser.id);
         return grappUser;
      }

      function resolveUser(grappUser) {
         deferred.resolve(grappUser);
         userLoggedIn = true;
      }
   }

   logInCachedUser.$inject = ["GrappLogIn"];
   function logInCachedUser(GrappLogIn) {
      GrappLogIn.logInCachedUser();
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .controller("Main", Main);

   Main.$inject = ["$state", "$uibModal", "GrappLogIn"];
   function Main($state, $uibModal, GrappLogIn) {
      var mainVM = this;
      mainVM.isLoading = false;
      mainVM.isUserLoggedIn = GrappLogIn.isUserLoggedIn;
      mainVM.grappUserName = null;
      mainVM.grappUserAvater = null;
      mainVM.logIn = logIn;
      mainVM.logOut = logOut;
      mainVM.showWelcome = showWelcome;
      mainVM.showShoppingLists = showShoppingLists;
      mainVM.showStores = showStores;
      mainVM.openModalUpdateDisplayName = openModalUpdateDisplayName;

      initialize();

      ////////////////////

      function initialize() {
         mainVM.isLoading = true;
         GrappLogIn.afterLogIn().then(function(grappUser) {
            initUserVariables(grappUser);
         }).finally(function() {
            mainVM.isLoading = false;
         });
      }

      function logIn(oAuthProvider) {
         mainVM.isLoading = true;
         GrappLogIn.logInWithOAuth(oAuthProvider).then(function(grappUser) {
            initUserVariables(grappUser);
            mainVM.showShoppingLists();
         }).finally(function() {
            mainVM.isLoading = false;
         });
      }

      function logOut() {
         GrappLogIn.logOut();
         mainVM.showWelcome();
      }

      function showWelcome() {
         $state.go("main.welcome");
      }

      function showShoppingLists() {
         $state.go("main.shoppingLists");
      }

      function showStores() {
         $state.go("main.stores");
      }

      function openModalUpdateDisplayName() {
         $uibModal.open({
            animation: true,
            templateUrl: "app/welcome/ModalUpdateDisplayName.html",
            controller: "ModalUpdateDisplayName",
            controllerAs: "modalUpdateDisplayNameVM",
            resolve: {
               displayName: function () {
                  return mainVM.grappUserName;
               }
            }
         }).result.then(updateDisplayName, function() {});
      }

      function updateDisplayName(updatedDisplayName) {
         mainVM.loading = true;
         GrappLogIn.afterLogIn().then(function(grappUser) {
            return grappUser.updateDisplayName(updatedDisplayName);
         }).finally(function() {
            mainVM.loading = false;
         });
      }

      function initUserVariables(grappUser) {
         mainVM.grappUserName = grappUser ? grappUser.name : null;
         mainVM.grappUserAvatar = grappUser ? grappUser.avatar : null;
      }
   }
})();
(function() {
   "use strict";

   angular.module("Grapp")
      .controller("ModalUpdateDisplayName", ModalUpdateDisplayName);

   ModalUpdateDisplayName.$inject = ["$uibModalInstance", "displayName"];
   function ModalUpdateDisplayName($uibModalInstance, displayName) {
      var modalUpdateDisplayNameVM = this;
      modalUpdateDisplayNameVM.displayName = displayName;
      modalUpdateDisplayNameVM.update = update;
      modalUpdateDisplayNameVM.cancel = cancel;

      ////////////////////

      function update() {
         $uibModalInstance.close(modalUpdateDisplayNameVM.displayName);
      }

      function cancel() {
         $uibModalInstance.dismiss();
      }
   }
})();

(function() {
   "use strict";

   angular.module("Grapp")
      .config(GrappConfig);

   GrappConfig.$inject = ["$stateProvider"];
   function GrappConfig($stateProvider) {
      $stateProvider
         .state("main", {
            url: "/",
            abstract: true,
            views: {
               "main": {
                  templateUrl: "app/welcome/Main.html",
                  controller: "Main",
                  controllerAs: "mainVM"
               }
            }
         })
         .state("main.welcome", {
            url: "welcome",
            views: {
               "content": {
                  templateUrl: "app/welcome/MainWelcome.html"
               }
            }
         });
   }
})();