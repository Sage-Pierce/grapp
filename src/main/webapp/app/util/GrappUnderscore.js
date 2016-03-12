(function() {
   _.mixin({
      arrayify: function(object) {
         return _.isArray(object) ? object : [object];
      }
   });
})();