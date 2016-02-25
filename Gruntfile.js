"use strict";

module.exports = function (grunt) {
   grunt.initConfig({
      pkg: grunt.file.readJSON("package.json"),
      watch: {
         config: {
            files: ["src/main/webapp/app/GrappConfigInit.js"],
            tasks: ["jshint", "concat:config"]
         },
         mapping: {
            files: ["src/main/webapp/app/mapping/*.js"],
            tasks: ["jshint", "concat:mapping"]
         },
         models: {
            files: ["src/main/webapp/app/models/*.js"],
            tasks: ["jshint", "concat:models"]
         },
         shoppinglists: {
            files: ["src/main/webapp/app/shoppinglists/*.js"],
            tasks: ["jshint", "concat:shoppinglists"]
         },
         storemap: {
            files: ["src/main/webapp/app/storemap/*.js"],
            tasks: ["jshint", "concat:storemap"]
         },
         storemaplayout: {
            files: ["src/main/webapp/app/storemap/layout/*.js"],
            tasks: ["jshint", "concat:storemaplayout"]
         },
         storemapnodes: {
            files: ["src/main/webapp/app/storemap/nodes/*.js"],
            tasks: ["jshint", "concat:storemapnodes"]
         },
         stores: {
            files: ["src/main/webapp/app/stores/*.js"],
            tasks: ["jshint", "concat:stores"]
         },
         welcome: {
            files: ["src/main/webapp/app/welcome/*.js"],
            tasks: ["jshint", "concat:welcome"]
         }
      },
      jshint: {
         options: {
            jshintrc: ".jshintrc"
         },
         all: [
            "src/main/webapp/app/*.js",
            "src/main/webapp/app/mapping/*.js",
            "src/main/webapp/app/models/*.js",
            "src/main/webapp/app/shoppinglists/*.js",
            "src/main/webapp/app/storemap/*.js",
            "src/main/webapp/app/stores/*.js",
            "src/main/webapp/app/welcome/*.js"
         ]
      },
      concat: {
         config: {
            src: ["src/main/webapp/app/GrappConfigInit.js"],
            dest: "src/main/webapp/concat/GrappConfigInit.js"
         },
         mapping: {
            src: ["src/main/webapp/app/mapping/*.js"],
            dest: "src/main/webapp/concat/mapping-concat.js"
         },
         models: {
            src: ["src/main/webapp/app/models/*.js"],
            dest: "src/main/webapp/concat/models-concat.js"
         },
         shoppinglists: {
            src: ["src/main/webapp/app/shoppinglists/*.js"],
            dest: "src/main/webapp/concat/shoppinglists-concat.js"
         },
         storemap: {
            src: ["src/main/webapp/app/storemap/*.js"],
            dest: "src/main/webapp/concat/storemap-concat.js"
         },
         storemaplayout: {
            src: ["src/main/webapp/app/storemap/layout/*.js"],
            dest: "src/main/webapp/concat/storemaplayout-concat.js"
         },
         storemapnodes: {
            src: ["src/main/webapp/app/storemap/nodes/*.js"],
            dest: "src/main/webapp/concat/storemapnodes-concat.js"
         },
         stores: {
            src: ["src/main/webapp/app/stores/*.js"],
            dest: "src/main/webapp/concat/stores-concat.js"
         },
         welcome: {
            src: ["src/main/webapp/app/welcome/*.js"],
            dest: "src/main/webapp/concat/welcome-concat.js"
         }
      },
      connect: {
         server: {
            options: {
               port: 8000,
               base: "src/main/webapp/"
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