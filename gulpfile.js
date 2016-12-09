var gulp           = require('gulp');
var concat         = require('gulp-concat');
var concatVendor   = require('gulp-concat-vendor');
var uglify         = require('gulp-uglify');
var ngAnnotate     = require('gulp-ng-annotate')
var minify         = require('gulp-minify-css')
var mainBowerFiles = require('main-bower-files');
var inject         = require('gulp-inject');
var runSequence    = require('gulp-run-sequence');
var gzip           = require('gulp-gzip');
var clone          = require('gulp-clone');
var series         = require('stream-series');
 
var vendorJs;
var vendorCss;

// JS
gulp.task('lib-js-files', function () {
    vendorJs = gulp.src(mainBowerFiles('**/*.js'),{ base: 'bower_components' })
        .pipe(concatVendor('app.min.js'))
        .pipe(ngAnnotate())
        .pipe(uglify())
        .pipe(gulp.dest('src/main/resources/static/js'));
 
    vendorJs.pipe(clone())
        .pipe(gzip())
        .pipe(gulp.dest('src/main/resources/static/js'));
});

// CSS
gulp.task('lib-css-files', function () {
    vendorCss = gulp.src(mainBowerFiles('**/*.css'), {base: 'bower_components'})
        .pipe(concat('app.min.css'))
        .pipe(minify())
        .pipe(gulp.dest('src/main/resources/static/styles'));
 
    vendorCss.pipe(clone())
        .pipe(clone())
        .pipe(gzip())
        .pipe(gulp.dest('src/main/resources/static/styles'));
});

// Copy font if any....
gulp.task('copyFonts', function() {
    gulp.src(mainBowerFiles('**/dist/fonts/*.{ttf,woff,woff2,eof,svg}')).pipe(gulp.dest('src/main/resources/static/fonts'));
    // ui-grid fonts
    gulp.src(mainBowerFiles('**/ui-grid.{ttf,woff,woff2,eof,svg}')).pipe(gulp.dest('src/main/resources/static/styles'));
});
 
// Default Task
gulp.task('default', function () {
	// Run tasks
    runSequence('lib-js-files', 'lib-css-files', /*'index',*/ 'copyFonts');
});