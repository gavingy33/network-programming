require([ 'mmState' ], function(mmState) {
	var root = avalon.define('root', function(vm) {
		$id : 'root'
		vm.page = "";
	});

	// 定义一个全局抽象状态，用来渲染通用不会改变的视图，比如header，footer
	avalon.state("app", {
		url : "/",
		abstract : true, // 抽象状态，不会对应到url上
		views : {
			"footer@" : {
				templateProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ 'text!/view/footer/footer.html' ], function($ctrl) {
							rs($ctrl)
						})
					})
				},
				controllerProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ '/view/footer/footer.js' ], function($ctrl) {
							rs($ctrl)
						})
					})
				}
			}
		}
	});

	avalon.state.config({
		onError : function() {
			console.log(arguments);
		}
	});

	avalon.state('app.controller', {
		url : 'controller',
		views : {
			"@" : {
				templateProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ 'text!/view/controller/controller.html' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/controller/controller.html' successful !");
						})
					})
				},
				controllerProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ '/view/controller/controller.js' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/controller/controller.js' successful !");
						})
					})
				}
			}
		}
	}).state('app.ms_skip', {
		url : 'ms_skip',
		views : {
			"@" : {
				templateProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ 'text!/view/ms_skip/ms_skip.html' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/ms_skip/ms_skip.html' successful !");
						})
					})
				},
				controllerProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ '/view/ms_skip/ms_skip.js' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/ms_skip/ms_skip.js' successful !");
						})
					})
				}
			}
		}
	}).state('app.ms_important', {
		url : 'ms_important',
		views : {
			"@" : {
				templateProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ 'text!/view/ms_important/ms_important.html' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/ms_important/ms_important.html' successful !");
						})
					})
				},
				controllerProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ '/view/ms_important/ms_important.js' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/ms_important/ms_important.js' successful !");
						})
					})
				}
			}
		}
	}).state('app.ms_attr', {
		url : 'ms_attr',
		views : {
			"@" : {
				templateProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ 'text!/view/ms_attr/ms_attr.html' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/ms_attr/ms_attr.html' successful !");
						})
					})
				},
				controllerProvider : function() {
					return new Promise(function(rs, rj) {
						requirejs([ '/view/ms_attr/ms_attr.js' ], function($ctrl) {
							rs($ctrl);
							console.log("load '/view/ms_attr/ms_attr.js' successful !");
						})
					})
				}
			}
		}
	});
	avalon.router.error(function() {
		avalon.router.navigate('root')
	});
	avalon.history.start({});
	avalon.scan();
})