(function() {
   _.mixin({
      arrayify: function(object) {
         return _.isArray(object) ? object : [object];
      },
      merge: function(object1, object2) {
         var result = {};
         addObjectMappingsToResult(object1, result);
         addObjectMappingsToResult(object2, result);
         return result;

         function addObjectMappingsToResult(object, result) {
            for (var key in object) {
               if (object.hasOwnProperty(key) && !result.hasOwnProperty(key)) {
                  result[key] = object[key];
               }
            }
         }
      },
      convertLocationToPosition: function(location) {
         return {latitude: _.isFunction(location.lat) ? location.lat() : location.lat, longitude: _.isFunction(location.lng) ? location.lng() : location.lng};
      },
      convertPositionToLocation: function(position) {
         return {lat: position.latitude || position.lat(), lng: position.longitude || position.lng()};
      }
   });
})();