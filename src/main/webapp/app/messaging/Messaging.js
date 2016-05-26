(function() {
   "use strict";

   angular.module("App")
      .service("Messaging", Messaging);

   Messaging.$inject = ["$uibModal"];
   function Messaging($uibModal) {
      var self = this;
      self.requestConfirmation = requestConfirmation;

      ////////////////////

      function requestConfirmation(title, message) {
         return $uibModal.open({
            animation: true,
            templateUrl: "app/messaging/ModalConfirmation.html",
            controller: "ModalConfirmation",
            controllerAs: "modalConfirmationVM",
            resolve: {
               title: function() { return title; },
               message: function() { return message; }
            }
         }).result;
      }
   }
})();