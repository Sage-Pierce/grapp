"use strict";

module.exports = function (grunt) {
   var webappRoot = "src/main/webapp/";
   grunt.initConfig({
      pkg: grunt.file.readJSON("package.json"),
      watch: {
         config: {
            files: [webappRoot + "app/GrappConfigInit.js"],
            tasks: ["jshint", "concat:config"]
         },
         general: {
            files: [webappRoot + "app/general/*.js"],
            tasks: ["jshint", "concat:general"]
         },
         items: {
            files: [webappRoot + "app/items/*.js"],
            tasks: ["jshint", "concat:items"]
         },
         itemtree: {
            files: [webappRoot + "app/itemtree/*.js"],
            tasks: ["jshint", "concat:itemtree"]
         },
         map: {
            files: [webappRoot + "app/map/*.js"],
            tasks: ["jshint", "concat:map"]
         },
         shoppinglists: {
            files: [webappRoot + "app/shoppinglists/*.js"],
            tasks: ["jshint", "concat:shoppinglists"]
         },
         storemap: {
            files: [webappRoot + "app/storemap/*.js"],
            tasks: ["jshint", "concat:storemap"]
         },
         storemaplayout: {
            files: [webappRoot + "app/storemap/layout/*.js"],
            tasks: ["jshint", "concat:storemaplayout"]
         },
         storemapnodes: {
            files: [webappRoot + "app/storemap/nodes/*.js"],
            tasks: ["jshint", "concat:storemapnodes"]
         },
         stores: {
            files: [webappRoot + "app/stores/*.js"],
            tasks: ["jshint", "concat:stores"]
         },
         welcome: {
            files: [webappRoot + "app/welcome/*.js"],
            tasks: ["jshint", "concat:welcome"]
         }
      },
      jshint: {
         options: {
            jshintrc: ".jshintrc"
         },
         all: [
            webappRoot + "app/*.js",
            webappRoot + "app/general/*.js",
            webappRoot + "app/items/*.js",
            webappRoot + "app/itemtree/*.js",
            webappRoot + "app/map/*.js",
            webappRoot + "app/shoppinglists/*.js",
            webappRoot + "app/storemap/*.js",
            webappRoot + "app/stores/*.js",
            webappRoot + "app/welcome/*.js"
         ]
      },
      concat: {
         config: {
            src: [webappRoot + "app/GrappConfigInit.js"],
            dest: webappRoot + "concat/GrappConfigInit.js"
         },
         general: {
            src: [webappRoot + "app/general/*.js"],
            dest: webappRoot + "concat/general-concat.js"
         },
         items: {
            src: [webappRoot + "app/items/*.js"],
            dest: webappRoot + "concat/items-concat.js"
         },
         itemtree: {
            src: [webappRoot + "app/itemtree/*.js"],
            dest: webappRoot + "concat/itemtree-concat.js"
         },
         map: {
            src: [webappRoot + "app/map/*.js"],
            dest: webappRoot + "concat/map-concat.js"
         },
         shoppinglists: {
            src: [webappRoot + "app/shoppinglists/*.js"],
            dest: webappRoot + "concat/shoppinglists-concat.js"
         },
         storemap: {
            src: [webappRoot + "app/storemap/*.js"],
            dest: webappRoot + "concat/storemap-concat.js"
         },
         storemaplayout: {
            src: [webappRoot + "app/storemap/layout/*.js"],
            dest: webappRoot + "concat/storemaplayout-concat.js"
         },
         storemapnodes: {
            src: [webappRoot + "app/storemap/nodes/*.js"],
            dest: webappRoot + "concat/storemapnodes-concat.js"
         },
         stores: {
            src: [webappRoot + "app/stores/*.js"],
            dest: webappRoot + "concat/stores-concat.js"
         },
         welcome: {
            src: [webappRoot + "app/welcome/*.js"],
            dest: webappRoot + "concat/welcome-concat.js"
         }
      },
      connect: {
         server: {
            options: {
               port: 8000,
               base: webappRoot
            }
         }
      }
   });

   grunt.loadNpmTasks("grunt-contrib-watch");
   grunt.loadNpmTasks("grunt-contrib-jshint");
   grunt.loadNpmTasks("grunt-contrib-concat");
   grunt.loadNpmTasks("grunt-contrib-connect");

   grunt.registerTask("default", ["concat"]);
   grunt.registerTask("devServer", ["concat", "connect:server", "watch"]);
};