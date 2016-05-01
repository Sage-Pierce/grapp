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
      },
      mergeLeft: function(dest, src) {
         return addObjectMappingsToResult(src, dest);
      },
      forceMergeLeft: function(dest, src) {
         return addObjectMappingsToResult(src, dest, true);
      },
      calculateBounds: function(locations) {
         var position = locations.length > 0 ? locations[0] : {lat: 0, lng: 0};
         var result = {north: position.lat, east: position.lng, south: position.lat, west: position.lng};
         for (var i = 1; i < locations.length; i++) {
            result.north = Math.max(result.north, locations[i].lat);
            result.east = Math.max(result.east, locations[i].lng);
            result.south = Math.min(result.south, locations[i].lat);
            result.west = Math.min(result.west, locations[i].lng);
         }
         return result;
      },
      extractVerticesFromGMapPolygon: function(gMapPolygon) {
         return this.extractPathFromGMapPolygon(gMapPolygon).map(this.convertPositionToLocation);
      },
      extractPathFromGMapPolygon: function(gMapPolygon) {
         return gMapPolygon.getPath().getArray();
      },
      convertLocationToPosition: function(location) {
         return {latitude: _.isFunction(location.lat) ? location.lat() : location.lat, longitude: _.isFunction(location.lng) ? location.lng() : location.lng};
      },
      convertPositionToLocation: function(position) {
         return {lat: position.latitude || position.lat(), lng: position.longitude || position.lng()};
      },
      stringifyVerticesIntoPolygon: function(vertices) {
         return JSON.stringify({vertices: vertices});
      }
   });

   function addObjectMappingsToResult(object, result, force) {
      for (var key in object) {
         if (object.hasOwnProperty(key) && (force || !result.hasOwnProperty(key))) {
            result[key] = object[key];
         }
      }
      return result;
   }
})();