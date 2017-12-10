(function($) { 
	
	$.LibWebPagination = function(pagination, options) { 
		
		this.pagination = (pagination instanceof $) ? pagination : $(form);

		this._configure(options);
	};
	
	$.LibWebPagination.prototype = {
		
		_options : {
			test : 'test'
		},
				
		_configure : function(options) {
		
		}
	};
}(jQuery));