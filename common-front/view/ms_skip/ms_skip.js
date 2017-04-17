define([ 'avalon' ], function(avalon) {
	var ms_skip = avalon.define({
		$id : 'ms_skip',
		value_a : 'a'
	});

	return avalon.controller(function($ctrl) {
		// 视图渲染后，意思是avalon.scan完成
		console.log('controller scan');
		// 进入视图
		$ctrl.$onEnter = function(param, rs, rj) {
			console.log('onEnter');
		};
		// 对应的视图销毁前
		$ctrl.$onBeforeUnload = function() {
			console.log('onBeforeUnload');
		};
		$ctrl.$vmodels = [ ms_skip ];
	});
})