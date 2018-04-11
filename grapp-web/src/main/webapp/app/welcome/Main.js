(function() {
   "use strict";

   angular.module("App")
      .controller("Main", Main);

   Main.$inject = ["$state", "$uibModal", "Auth"];
   function Main($state, $uibModal, Auth) {
      var mainVM = this;
      mainVM.loginPromise = null;
      mainVM.isUserLoggedIn = Auth.isUserLoggedIn;
      mainVM.userName = null;
      mainVM.userAvater = null;
      mainVM.admin = false;
      mainVM.manager = false;
      mainVM.logIn = Auth.logIn;
      mainVM.logOut = logOut;
      mainVM.showWelcome = showWelcome;
      mainVM.showShoppingLists = showShoppingLists;
      mainVM.showStores = showStores;
      mainVM.showItems = showItems;
      mainVM.openModalDisplayName = openModalDisplayName;

      initialize();

      ////////////////////

      function initialize() {
         mainVM.loginPromise = Auth.afterLogIn().then(showUserShoppingLists, showWelcome);
      }

      function showUserShoppingLists(user) {
         mainVM.userName = user.getName();
         mainVM.userAvatar = user.getAvatar();
         mainVM.admin = user.hasRole("ADMIN");
         mainVM.manager = user.hasRole("STORE_MANAGER");
         showShoppingLists();
      }

      function logOut() {
         Auth.logOut();
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
         Auth.afterLogIn().then(function(user) {
            return user.setAttributes(attributes);
         }).then(function(user) {
            mainVM.userName = user.getName();
         });
      }
   }
})();