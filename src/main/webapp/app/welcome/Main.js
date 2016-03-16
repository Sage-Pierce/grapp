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
      mainVM.showItems = showItems;
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
                  return mainVM.grappUserName;
               }
            }
         }).result.then(updateDisplayName, function() {});
      }

      function updateDisplayName(updatedDisplayName) {
         mainVM.loading = true;
         GrappLogIn.afterLogIn().then(function(grappUser) {
            return grappUser.updateDisplayName(updatedDisplayName);
         }).then(function() {
            mainVM.grappUserName = updatedDisplayName;
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