"use strict";

module.exports = function (grunt) {
   grunt.initConfig({
      pkg: grunt.file.readJSON("package.json"),
      watch: {
         config: {
            files: ["app/GrappConfigInit.js"],
            tasks: ["jshint", "concat:config"]
         },
         mapping: {
            files: ["app/mapping/*.js"],
            tasks: ["jshint", "concat:mapping"]
         },
         models: {
            files: ["app/models/*.js"],
            tasks: ["jshint", "concat:models"]
         },
         shoppinglists: {
            files: ["app/shoppinglists/*.js"],
            tasks: ["jshint", "concat:shoppinglists"]
         },
         storemap: {
            files: ["app/storemap/*.js"],
            tasks: ["jshint", "concat:storemap"]
         },
         storemaplayout: {
            files: ["app/storemap/layout/*.js"],
            tasks: ["jshint", "concat:storemaplayout"]
         },
         storemapnodes: {
            files: ["app/storemap/nodes/*.js"],
            tasks: ["jshint", "concat:storemapnodes"]
         },
         stores: {
            files: ["app/stores/*.js"],
            tasks: ["jshint", "concat:stores"]
         },
         welcome: {
            files: ["app/welcome/*.js"],
            tasks: ["jshint", "concat:welcome"]
         }
      },
      jshint: {
         options: {
            jshintrc: ".jshintrc"
         },
         all: [
            "app/*.js",
            "app/mapping/*.js",
            "app/models/*.js",
            "app/shoppinglists/*.js",
            "app/storemap/*.js",
            "app/stores/*.js",
            "app/welcome/*.js"
         ]
      },
      concat: {
         config: {
            src: ["app/GrappConfigInit.js"],
            dest: "concat/GrappConfigInit.js"
         },
         mapping: {
            src: ["app/mapping/*.js"],
            dest: "concat/mapping-concat.js"
         },
         models: {
            src: ["app/models/*.js"],
            dest: "concat/models-concat.js"
         },
         shoppinglists: {
            src: ["app/shoppinglists/*.js"],
            dest: "concat/shoppinglists-concat.js"
         },
         storemap: {
            src: ["app/storemap/*.js"],
            dest: "concat/storemap-concat.js"
         },
         storemaplayout: {
            src: ["app/storemap/layout/*.js"],
            dest: "concat/storemaplayout-concat.js"
         },
         storemapnodes: {
            src: ["app/storemap/nodes/*.js"],
            dest: "concat/storemapnodes-concat.js"
         },
         stores: {
            src: ["app/stores/*.js"],
            dest: "concat/stores-concat.js"
         },
         welcome: {
            src: ["app/welcome/*.js"],
            dest: "concat/welcome-concat.js"
         }
      },
      connect: {
         server: {
            options: {
               port: 8000
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