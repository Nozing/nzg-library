(function($) { 
	
	$.LibWebAjax = function(options) { 

		_self = this;
		_self._options = $.extend({}, _self._options, options);
		
		_self._configure(options);
		
	};
	
	$.LibWebAjax.prototype = {
		
		_options : {
			method : 'GET',
			showLoading : true,
			url : null,
			beforeSend: function (qXHR, settings) { 
				
				_self._beforeSendCallBack(qXHR, settings);
				if (_self._options.showLoading) {
					Loading.show();
				}
			},
			complete : function (jqXHR, textStatus) { 
				
				_self._completeCallBack(jqXHR, textStatus);
				if (_self._options.showLoading) {
					Loading.hide();
				}
			},
			error : function (jqXHR, textStatus, errorThrown) { 
				
				if (_self._options.showLoading) {
					Loading.hide();
				}
				_self._errorCallBack(jqXHR, textStatus, errorThrown); 
			},			
			success : function(data, textStatus, response) {
				
			},
		},
				
		_configure : function(options) {
		
		},
		
		GET : function (options) {
			
			settings = $.extend({}, this._options, options);
			$.ajax(settings);
		},
		
		PUT : function (options) {
			
			settings = $.extend({}, this._options, options);
			settings.method = 'PUT';
			$.ajax(settings);
		},
		
		DELETE : function (options) {
			
			settings = $.extend({}, this._options, options);
			settings.method = 'DELETE';
			$.ajax(settings);
		},
		
		POST : function (options) {
			
			settings = $.extend({}, this._options, options);
			settings.method = 'POST';
			$.ajax(settings);
		},
		
		_beforeSendCallBack : function (qXHR, settings) {
			
			console.log('_beforeSendCallBack');
		},
		
		_completeCallBack : function () {
			
			console.log('_completeCallBack');
		},
		
		_errorCallBack : function (jqXHR, textStatus, errorThrown) {
			
			console.log('error: { status : \'' + textStatus + '\', message : \'' + errorThrown + '\' }');
		}
	};
}(jQuery));

var AjaxUtil = new $.LibWebAjax();