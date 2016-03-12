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
         mapping: {
            files: [webappRoot + "app/mapping/*.js"],
            tasks: ["jshint", "concat:mapping"]
         },
         model: {
            files: [webappRoot + "app/model/*.js"],
            tasks: ["jshint", "concat:model"]
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
         util: {
            files: [webappRoot + "app/util/*.js"],
            tasks: ["jshint", "concat:util"]
         },
         value: {
            files: [webappRoot + "app/value/*.js"],
            tasks: ["jshint", "concat:value"]
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
            webappRoot + "app/mapping/*.js",
            webappRoot + "app/model/*.js",
            webappRoot + "app/shoppinglists/*.js",
            webappRoot + "app/storemap/*.js",
            webappRoot + "app/stores/*.js",
            webappRoot + "app/value/*.js",
            webappRoot + "app/util/*.js",
            webappRoot + "app/welcome/*.js"
         ]
      },
      concat: {
         config: {
            src: [webappRoot + "app/GrappConfigInit.js"],
            dest: webappRoot + "concat/GrappConfigInit.js"
         },
         mapping: {
            src: [webappRoot + "app/mapping/*.js"],
            dest: webappRoot + "concat/mapping-concat.js"
         },
         model: {
            src: [webappRoot + "app/model/*.js"],
            dest: webappRoot + "concat/model-concat.js"
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
         util: {
            src: [webappRoot + "app/util/*.js"],
            dest: webappRoot + "concat/util-concat.js"
         },
         value: {
            src: [webappRoot + "app/value/*.js"],
            dest: webappRoot + "concat/value-concat.js"
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