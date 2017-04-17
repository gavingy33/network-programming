define([ 'avalon' ], function(avalon) {
	var ms_crtl_1 = avalon.define({
		$id : 'ms_crtl_1',
		value_a : 'ms_crtl_1_a',
		value_b : 'ms_crtl_1_b'
	});
	var ms_crtl_2 = avalon.define({
		$id : 'ms_crtl_2',
		value_a : 'ms_crtl_2_a',
		value_b : 'ms_crtl_2_b'
	});
	var ms_crtl_3 = avalon.define({
		$id : 'ms_crtl_3',
		value_a : 'ms_crtl_3_a',
	});

	var ms_impt = avalon.define({
		$id : 'ms_impt',
		value_a : 'ms_impt_a'
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
		$ctrl.$vmodels = [ ms_crtl_1 ];
	});
})