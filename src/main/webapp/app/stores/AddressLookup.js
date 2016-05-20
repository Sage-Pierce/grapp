(function() {
   "use strict";

   angular.module("App")
      .service("AddressLookup", AddressLookup);

   AddressLookup.$inject = ["$q"];
   function AddressLookup($q) {
      var self = this;
      self.lookupLocation = lookupLocation;

      var geocoder = new google.maps.Geocoder();

      ////////////////////

      function lookupLocation(location) {
         var deferred = $q.defer();
         geocoder.geocode({"location": location}, function(results, status) {
            deferred.resolve(status === google.maps.GeocoderStatus.OK ? extractAddressFromLookupResults(results) : "(Unavailable)");
         });
         return deferred.promise;
      }

      function extractAddressFromLookupResults(results) {
         return results[0] ? results[0].formatted_address : "Not Found";
      }
   }
})();