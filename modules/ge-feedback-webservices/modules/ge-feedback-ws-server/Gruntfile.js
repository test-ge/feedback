'use strict';

var path = require('path');
var fs = require('fs');
var through = require('through');
var mime = require('mime-types');

function buildStyle(basePath, name) {
    return {
        src: [ basePath + '/' + name ],
        dest: '<%= project.path.styles %>/ext/' + path.basename(name).replace(/\.[^.]+$/, '.css')
    }
}


module.exports = function (grunt) {

    var basedir = 'src/main/webapp';
    var distdir = 'target/m2e-wtp/web-resources';

    var pkg = grunt.file.readJSON('package.json');

    var styles = Object.keys(pkg.dependencies || {}).map(function (name) {
        var basePath = 'node_modules/' + name;
        var nfo = grunt.file.readJSON(basePath + '/package.json');
        var styles = (nfo.less || nfo.style);

        if (null == styles) {
            return null;
        } else if (Array.isArray(styles)) {
            return styles.map(function (elm) {
                return buildStyle(basePath, elm);
            });
        } else {
            return [ buildStyle(basePath, styles) ];
        }
    }).filter(function (lst) {
        return null != lst && lst.length > 0;
    }).reduce(function (all, lst) {
        return all.concat(lst);
    }, []);

    styles = grunt.file.expandMapping([ '**/*.less', '**/*.css', '!mixins/**' ], distdir + '/css', {
        cwd: basedir + '/css'
    }).map(function (elm) {
        return {
            src: elm.src,
            dest: elm.dest.replace(/\.[^.]+$/, '.css')
        };
    }).concat(styles);

    grunt.initConfig({
        pkg: pkg,
        project: {
            path: {
                base: basedir,
                dist: distdir
            }
        },
        browserify: {
            dist: {
                files: grunt.file.expandMapping([ '*.js' ], distdir + '/js', {
                    cwd: basedir + '/js'
                }),
                options: {
                    transform: [ function (file) {
                        return through(function write(buffer, encoding, next) {
                            this.push(buffer.toString('utf8').replace(/%%INC:([^%]+)%%/g, function (match, path, offset, str) {
                                return 'data:' + mime.lookup(path) + ';base64,' + new Buffer(fs.readFileSync(distdir + '/' + path)).toString('base64');
                            }));
                        })
                    } ]
                }
            }
        },
        less: {
            dist: {
                options: {
                    paths : [ basedir + '/css/mixins', 'node_modules/bootstrap/less' ]
                },
                files: styles
            }
        },
        extract: {
            dist: {
                files: styles
            }
        },
        copy: {
            all: {
                files: grunt.file.expandMapping([ '**/*.*', '!**/*.less', '!**/*.css', '!**/*.js' ], distdir, {
                    cwd: basedir
                })
            }
        },
        watch: {
            styles: {
                files: [ 'Gruntfile.js' ],
                tasks: [ 'default' ],
                options: {
                    spawn: false
                }
            },
            scripts: {
                files: [ basedir + '/js/**/*.js' ],
                tasks: [ 'browserify' ],
                options: {
                    spawn: false
                }
            },
            packaging: {
                files: [ basedir + '/css/**/*.*' ],
                tasks: [ 'less', 'extract', 'copy', 'browserify' ],
                options: {
                    spawn: false
                }
            },
            others: {
                files: [ basedir + '/**/*.*', '!' + basedir + '/**/*.less', '!' + basedir + '/**/*.css', '!' + basedir + '/**/*.js' ],
                tasks: [ 'copy:all' ],
                options: {
                    spawn: false
                }
            }
        }
    });

    require('load-grunt-tasks')(grunt);
    require('./contrib/grunt-contrib-extract.js')(grunt);

    grunt.registerTask('default', [ 'less', 'extract', 'copy', 'browserify' ]);

};