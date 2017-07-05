'use strict';

var path = require('path');
var async = require('async');
var chalk = require('chalk');

module.exports = function (grunt) {

    grunt.registerMultiTask('extract', 'Extract urls from CSS files', function() {
        var done = this.async();
        var searchPattern = /url[ ]*\([ ]*('[^']+'|"[^"]+"|[^\)]+)[ ]*\)/g ;
        var cleanningPattern = /^(['"])([^#?]*)([#?].*)?\1$/ ;
        var cnt = 0;

        if (this.files.length < 1) {
            grunt.verbose.warn('No source files were provided.');
        }

        async.eachSeries(this.files, function (elm, next) {
            if (elm.src.length < 1) {
                grunt.verbose.warn('No source files were provided.');
                return next();
            }

            var srcBasePath = path.dirname(elm.src[0]);
            var dstBasePath = path.dirname(elm.dest);
            var buffer = grunt.file.read(elm.dest);
            var m;

            while ((m = searchPattern.exec(buffer))) {
                var url = m[1].replace(cleanningPattern, '$2');
                grunt.file.copy(path.resolve(srcBasePath, url), path.resolve(dstBasePath, url));
                cnt += 1;
            }

            return next();
        }, function () {
            grunt.log.ok(chalk.cyan(cnt) + ' ' + grunt.util.pluralize(cnt, 'file/files') + ' extracted');
            done();
        });
    });

};
