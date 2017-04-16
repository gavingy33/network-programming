require.config({
	baseUrl : 'scripts/libs',
	paths : {
		'avalon' : 'avalon/avalon',
		'mmHistory' : 'avalon/mmHistory',
		'mmRouter' : 'avalon/mmRouter'
	},
	shim : {
		'avalon' : {
			exports : 'avalon'
		},
		'mmHistory' : {
			deps : [ 'avalon' ]
		},
		'mmRouter' : {
			deps : 'avalon'
		}
	}
});

require([ 'avalon' ], function(avalon) {
	require([ '/scripts/router.js' ])
	console.log('callback !');
});