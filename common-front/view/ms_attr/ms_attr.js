define([ 'avalon' ], function(avalon) {
	var ms_attr = avalon.define({
		$id : 'ms_attr',
		obj : {
			title : '普通 ',
			algin : 'left'
		},
		active : {
			title : '激活'
		},
		width : 111,
		height : 222,
		arr : [ {
			img : 'aaa'
		}, {
			img : 'bbb'
		}, {
			img : 'ccc'
		} ],
		path : '../aaa/image.jpg',
		toggle : false,
		array : [ {
			width : 1
		}, {
			height : 2
		} ]
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
		$ctrl.$vmodels = [ ms_attr ];
	}
	);
})