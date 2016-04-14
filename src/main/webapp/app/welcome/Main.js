(function() {
   "use strict";

   angular.module("App")
      .controller("Main", Main);

   Main.$inject = ["$state", "$uibModal", "Login"];
   function Main($state, $uibModal, Login) {
      var mainVM = this;
      mainVM.isLoading = false;
      mainVM.isUserLoggedIn = Login.isUserLoggedIn;
      mainVM.userName = null;
      mainVM.userAvater = null;
      mainVM.logIn = logIn;
      mainVM.logOut = logOut;
      mainVM.showWelcome = showWelcome;
      mainVM.showShoppingLists = showShoppingLists;
      mainVM.showStores = showStores;
      mainVM.showItems = showItems;
      mainVM.openModalUpdateDisplayName = openModalUpdateDisplayName;

      initialize();

      ////////////////////

      function initialize() {
         mainVM.isLoading = true;
         Login.afterLogIn().then(function(user) {
            initUserVariables(user);
         }).finally(function() {
            mainVM.isLoading = false;
         });
      }

      function logIn(oAuthProvider) {
         mainVM.isLoading = true;
         Login.logInWithOAuth(oAuthProvider).then(function(user) {
            initUserVariables(user);
            mainVM.showShoppingLists();
         }).finally(function() {
            mainVM.isLoading = false;
         });
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

      function openModalUpdateDisplayName() {
         $uibModal.open({
            animation: true,
            templateUrl: "app/welcome/ModalUpdateDisplayName.html",
            controller: "ModalUpdateDisplayName",
            controllerAs: "modalUpdateDisplayNameVM",
            resolve: {
               displayName: function () {
                  return mainVM.userName;
               }
            }
         }).result.then(updateDisplayName, function() {});
      }

      function updateDisplayName(updatedDisplayName) {
         mainVM.loading = true;
         Login.afterLogIn().then(function(user) {
            return user.updateDisplayName(updatedDisplayName);
         }).then(function() {
            mainVM.userName = updatedDisplayName;
         }).finally(function() {
            mainVM.loading = false;
         });
      }

      function initUserVariables(user) {
         mainVM.userName = user ? user.name : null;
         mainVM.userAvatar = user ? user.avatar : null;
      }
   }
})();