/*global require*/
'use strict';

// Require.js allows us to configure shortcut alias
require.config({
	// The shim config allows us to configure dependencies for
	// scripts that do not call define() to register a module
	shim: {
		underscore: {
			exports: '_'
		},
		backbone: {
			deps: [
				'underscore',
				'jquery'
			]
		}
	},
	paths: {
		jquery: '../lib/jquery/jquery',
		underscore: '../lib/underscore/underscore',
		backbone: '../lib/backbone/backbone',
		text: '../lib/requirejs-text/text',
		domReady:'../lib/requirejs-domReady/domReady',
		handlebars:'../lib/handlebars/handlebars'
	}
});

require([
	'backbone'
], function (backbone) {
	/*jshint nonew:false*/
	// Initialize routing and start Backbone.history()
	console.log("main被调用");
	//new Workspace();

	backbone.history.start();

	// Initialize the application view
	//new AppView();
});
