(function() {
   "use strict";

   angular.module("App")
      .controller("Main", Main);

   Main.$inject = ["$state", "$uibModal", "Login"];
   function Main($state, $uibModal, Login) {
      var mainVM = this;
      mainVM.loginPromise = null;
      mainVM.isUserLoggedIn = Login.isUserLoggedIn;
      mainVM.userName = null;
      mainVM.userAvater = null;
      mainVM.logIn = logIn;
      mainVM.logOut = logOut;
      mainVM.showWelcome = showWelcome;
      mainVM.showShoppingLists = showShoppingLists;
      mainVM.showStores = showStores;
      mainVM.showItems = showItems;
      mainVM.openModalDisplayName = openModalDisplayName;

      initialize();

      ////////////////////

      function initialize() {
         mainVM.loginPromise = Login.afterLogIn().then(initUserVariables);
      }

      function logIn(oAuthProvider) {
         mainVM.loginPromise = Login.logInWithOAuth(oAuthProvider).then(initUserVariables).then(showShoppingLists);
      }

      function logOut() {
         Login.logOut();
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

      function showItems() {
         $state.go("main.items");
      }

      function openModalDisplayName() {
         $uibModal.open({
            animation: true,
            templateUrl: "app/welcome/ModalDisplayName.html",
            controller: "ModalDisplayName",
            controllerAs: "modalDisplayNameVM",
            resolve: {
               name: function () {
                  return mainVM.userName;
               }
            }
         }).result.then(updateUserAttributes);
      }

      function updateUserAttributes(attributes) {
         Login.afterLogIn().then(function(user) {
            return user.setAttributes(attributes);
         }).then(function(user) {
            mainVM.userName = user.name;
         });
      }

      function initUserVariables(user) {
         mainVM.userName = user ? user.name : null;
         mainVM.userAvatar = user ? user.avatar : null;
      }
   }
})();