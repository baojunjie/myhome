
module.exports = function (grunt) {

    var project = require("./project.json");
    require('time-grunt')(grunt);

    grunt.initConfig({
        bower: {
            install: {
                options: {
                    targetDir: "res/lib",
                    verbose: true
                }
            }
        },
        autoprefixer: {
            options: {
                browsers: [
                  "Android 2.3",
                  "Android >= 4",
                  "Chrome >= 20",
                  "Firefox >= 24",
                  "Explorer >= 8",
                  "iOS >= 6",
                  "Opera >= 12",
                  "Safari >= 6"
                ]
            },
            core: {
                src: 'res/css/*.css'
            }
        },
        sass: {
            options: {
                loadPath: ['scss'],
                precision: 6,
                sourcemap: 'auto',
                style: 'expanded',
                trace: true
            },
            core: {
                files: {
                    'res/css/main.css': 'res/css/main.scss'
                }
            }
        }
    });

    grunt.loadNpmTasks("grunt-bower-task");
    grunt.loadNpmTasks("grunt-autoprefixer");
    grunt.loadNpmTasks("grunt-contrib-clean");
    grunt.loadNpmTasks("grunt-contrib-sass");
};
