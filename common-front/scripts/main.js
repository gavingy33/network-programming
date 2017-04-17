require.config({
	baseUrl : 'scripts/libs',
	paths : {
		'avalon' : 'avalon/avalon.shim',
		'mmHistory' : 'avalon/mmHistory',
		'mmRouter' : 'avalon/mmRouter',
		'mmPromise' : 'avalon/mmPromise',
		'mmState' : 'avalon/mmState',
		'mmRequest' : 'avalon/mmRequest',
		'text' : 'require/text'
	},
	shim : {
		'avalon' : {
			exports : [ 'avalon' ]
		},
		'mmHistory' : {
			deps : [ 'avalon' ],
			exports : 'mmHistory'
		},
		'mmRouter' : {
			deps : [ 'avalon' ],
			exports : 'mmRouter'
		},
		'mmPromise' : {
			deps : [ 'avalon' ],
			exports : 'mmPromise'
		},
		'mmState' : {
			deps : [ 'mmPromise', 'mmRouter' ],
			exports : 'mmState'
		},
		'mmRequest' : {
			deps : [ 'avalon', 'mmPromise' ],
			exports : 'mmRequest'
		}
	}
});

require([ 'mmState', 'mmRequest' ], function(mmState, mmRequest) {
	avalon.require = requirejs;
	avalon.state.templateLoader = function(url, resolve, reject, reason) {
		avalon.require([ 'text!' + url ], function(templateString) {
			resolve(templateString)
		})
	};
	console.log('callback !');
	requirejs([ '/scripts/router.js' ]);
});