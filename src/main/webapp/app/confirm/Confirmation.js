(function() {
   "use strict";

   angular.module("App")
      .service("Confirmation", Confirmation);

   Confirmation.$inject = ["$uibModal"];
   function Confirmation($uibModal) {
      var self = this;
      self.showModalDialog = showModalDialog;

      ////////////////////

      function showModalDialog(title, message) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/confirm/ModalConfirm.html",
            controller: "ModalConfirm",
            controllerAs: "modalConfirmVM",
            resolve: {
               title: function() { return title; },
               message: function() { return message; }
            }
         }).result;
      }
   }
})();