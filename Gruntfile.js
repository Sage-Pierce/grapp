"use strict";

module.exports = function (grunt) {
   var webappRoot = "src/main/webapp/";
   grunt.initConfig({
      pkg: grunt.file.readJSON("package.json"),
      watch: {
         config: {
            files: [webappRoot + "app/config/*.js"],
            tasks: ["jshint", "concat:config"]
         },
         itemlist: {
            files: [webappRoot + "app/itemlist/*.js"],
            tasks: ["jshint", "concat:itemlist"]
         },
         items: {
            files: [webappRoot + "app/items/*.js"],
            tasks: ["jshint", "concat:items"]
         },
         itemtree: {
            files: [webappRoot + "app/itemtree/*.js"],
            tasks: ["jshint", "concat:itemtree"]
         },
         shoppinglist: {
            files: [webappRoot + "app/shoppinglist/*.js"],
            tasks: ["jshint", "concat:shoppinglist"]
         },
         shoppinglists: {
            files: [webappRoot + "app/shoppinglists/*.js"],
            tasks: ["jshint", "concat:shoppinglists"]
         },
         storelayout: {
            files: [webappRoot + "app/storelayout/*.js"],
            tasks: ["jshint", "concat:storelayout"]
         },
         storelayoutfeatures: {
            files: [webappRoot + "app/storelayout/features/*.js"],
            tasks: ["jshint", "concat:storelayoutfeatures"]
         },
         storelayoutnodes: {
            files: [webappRoot + "app/storelayout/nodes/*.js"],
            tasks: ["jshint", "concat:storelayoutnodes"]
         },
         storemap: {
            files: [webappRoot + "app/storemap/*.js"],
            tasks: ["jshint", "concat:storemap"]
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
            webappRoot + "app/config/*.js",
            webappRoot + "app/itemlist/*.js",
            webappRoot + "app/items/*.js",
            webappRoot + "app/itemtree/*.js",
            webappRoot + "app/shoppinglist/*.js",
            webappRoot + "app/shoppinglists/*.js",
            webappRoot + "app/storelayout/*.js",
            webappRoot + "app/storelayout/features/*.js",
            webappRoot + "app/storelayout/layout/*.js",
            webappRoot + "app/storemap/*.js",
            webappRoot + "app/stores/*.js",
            webappRoot + "app/welcome/*.js"
         ]
      },
      concat: {
         config: {
            src: [webappRoot + "app/config/*.js"],
            dest: webappRoot + "concat/config-concat.js"
         },
         itemlist: {
            src: [webappRoot + "app/itemlist/*.js"],
            dest: webappRoot + "concat/itemlist-concat.js"
         },
         items: {
            src: [webappRoot + "app/items/*.js"],
            dest: webappRoot + "concat/items-concat.js"
         },
         itemtree: {
            src: [webappRoot + "app/itemtree/*.js"],
            dest: webappRoot + "concat/itemtree-concat.js"
         },
         shoppinglist: {
            src: [webappRoot + "app/shoppinglist/*.js"],
            dest: webappRoot + "concat/shoppinglist-concat.js"
         },
         shoppinglists: {
            src: [webappRoot + "app/shoppinglists/*.js"],
            dest: webappRoot + "concat/shoppinglists-concat.js"
         },
         storelayout: {
            src: [webappRoot + "app/storelayout/*.js"],
            dest: webappRoot + "concat/storelayout-concat.js"
         },
         storelayoutfeatures: {
            src: [webappRoot + "app/storelayout/features/*.js"],
            dest: webappRoot + "concat/storelayoutfeatures-concat.js"
         },
         storelayoutnodes: {
            src: [webappRoot + "app/storelayout/nodes/*.js"],
            dest: webappRoot + "concat/storelayoutnodes-concat.js"
         },
         storemap: {
            src: [webappRoot + "app/storemap/*.js"],
            dest: webappRoot + "concat/storemap-concat.js"
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