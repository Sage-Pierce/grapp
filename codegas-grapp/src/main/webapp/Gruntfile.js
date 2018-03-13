"use strict";

module.exports = function (grunt) {
    var webappRoot = "./";
    var appDirectory = "app/";
    var features = grunt.file.expand({filter: "isDirectory", cwd: appDirectory}, ["*"]);
    grunt.initConfig({
        pkg: grunt.file.readJSON("package.json"),
        jshint: {
            options: {
                jshintrc: ".jshintrc"
            },
            all: [appDirectory + "**/*.js"]
        },
        concat: features.reduce(function (result, feature) {
            result[feature] = {
                src: [
                    webappRoot + appDirectory + feature + "/*.js",
                    webappRoot + appDirectory + feature + "/model/*.js",
                    webappRoot + appDirectory + feature + "/handler/*.js"
                ],
                dest: webappRoot + "concat/" + feature + "-concat.js"
            };
            return result;
        }, {}),
        connect: {
            server: {
                options: {
                    port: 8000
                }
            }
        },
        watch: features.reduce(function(result, feature) {
            result[feature] = {
                files: [webappRoot + appDirectory + feature + "/**/*.js"],
                tasks: ["jshint", "concat:" + feature]
            };
            return result;
        }, {})
    });

    grunt.loadNpmTasks("grunt-contrib-jshint");
    grunt.loadNpmTasks("grunt-contrib-concat");
    grunt.loadNpmTasks("grunt-contrib-connect");
    grunt.loadNpmTasks("grunt-contrib-watch");

    grunt.registerTask("default", ["jshint", "concat"]);
    grunt.registerTask("devServer", ["jshint", "concat", "connect:server", "watch"]);
};