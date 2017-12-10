/**
 * <p>Class that creates a modal window of bootstrap</p>
 * 
 * <p>Parameters:
 * <li><code>title</code>: [OPTIONAL] contains the title that will be putted
 * in the modal header</li>
 * <li><code>message</code>: [MANDATORY] contains de text we want to show
 * in the modal window</li>
 * </p>
 */
(function ($) {
    
	/**
	 * <p>
	 * Builder of the class.
	 * </p>
	 * 
	 * @param options Map with values to configure the instance.
	 */
	$.LibWebPopUp = function(options) {
	
		this.popupDiv = $('<div id="myModal" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"></div><div class="modal-footer"><div class="actions"/></div></div></div></div>');
		this._options = $.extend({}, this._options, options);
		this._configure(options);
	};
	
	$.LibWebPopUp.prototype = {
		
		/**
		 * <p>Basic configuration of the object</p>
		 */
		_options : {
			/* [OPTIONAL] Contains the title of the modal.*/
			title : null, 
			/* [MANDATORY] Contains the message that will be showed in the 
			 * modal window */
			message : null
		},
		
		/**
		 * <p>Private method that configure the modal window</p>
		 * 
		 * @params options Map with the configuration parameters
		 */
		_configure : function(options) {
			
			if (this._options.title != null) {
				
				this.popupDiv.find('.modal-content').prepend(
						this._createDivHeader(this._options.title));
			}
			
			if (this._options.message == null) {
				
				alert ('message param is mandatory');
				return;
			} else {
				
				this.popupDiv.find('.modal-body').append(
						this._createDivBody(this._options.message));
			}
		},
		
		/**
		 * <p>Method that shows the modal window</p>
		 */
		show : function () {
			this.popupDiv.modal();
		},
		
		/**
		 * <p>Method that hides the modal window</p>
		 */
		hide : function () {
			this.popupDiv.modal('hide');
		},
		
		/**
		 * <p>Method that adds the message to the modal window
		 */	
		_createDivBody : function (message) {
		
			return $('<p>' + message + '</p>');
		},
		
		/**
		 * <p>Method that adds an <em>accept</em> button to the modal window</p>
		 * 
		 * @param options [OPTIONAL] Map with the behaviour that could be added
		 * to the button.
		 * <ul>
		 * <li><code>onClick(evt)</code> : event that will be fired when the 
		 * <em>click</em> event of the button will be fired
		 * </ul>
		 */
		addBtnAccept : function (options) {
			
			var btnAccept = $('<button type="button" class="btn btn-info" data-dismiss="modal">_aceptar</button>');
			if (options) {
				
				if (options.onClick) {
					
					btnAccept.click(options.onClick)
				}
			}
			this._addBtn(btnAccept); 
		},
		
		/**
		 * <p>Method that adds an <em>confirm</em> button to the modal window</p>
		 * 
		 * @param options [OPTIONAL] Map with the behaviour that could be added
		 * to the button.
		 * <ul>
		 * <li><code>onClick(evt)</code> : event that will be fired when the 
		 * <em>click</em> event of the button will be fired
		 * </ul>
		 */
		addBtnConfirm : function (options) {
			
			var btnConfirm = $('<button type="button" class="btn btn-info" data-dismiss="modal">_confirmar</button>');
			if (options) {
				
				if (options.onClick) {
					
					btnConfirm.click(options.onClick)
				}
			}
			
			this._addBtn(btnConfirm); 
		},
		
		/**
		 * <p>Method that adds an <em>close</em> button to the modal window</p>
		 * 
		 * @param options [OPTIONAL] Map with the behaviour that could be added
		 * to the button.
		 * <ul>
		 * <li><code>onClick(evt)</code> : event that will be fired when the 
		 * <em>click</em> event of the button will be fired
		 * </ul>
		 */
		addBtnClose : function (options) {
			
			var btnClose = $('<button type="button" class="btn btn-default" data-dismiss="modal">_close</button>');
			if (options) {
				
				if (options.onClick) {
					
					btnClose.click(options.onClick)
				}
			}
			
			this._addBtn(btnClose, options);
		},
		
		/**
		 * <p>Private method that returns the header of the modal with a text
		 * as a title</p>
		 * 
		 * @param title [MANDATORY] Text or html with the title to insert in
		 * the header
		 */
		_createDivHeader : function (title) {
			
			return $('<div class="modal-header">' 
					+ '<button type="button" class="close" data-dismiss="modal">&times;</button>' 
					 + title 
					 + '</div>');
		},
		
		/**
		 * <p>Private method that adds a button to div <em>.actions</em> in the
		 * footer of the modal window</p>
		 * 
		 * @param button [MANDATORY] contains the button to be added in the
		 * footer
		 */
		_addBtn : function (button) {
			
			this.popupDiv.find('.modal-footer .actions').append(button);
		}
	};	
}(jQuery));


var LibWebPopUpBuilder = null;
LibWebPopUpBuilder = LibWebPopUpBuilder || (function () {
	
	return {
		/**
		 * <p>Returns a modal window of type confirmation. This modal is for
		 * asking the user if he is sure about making something</p>
		 * 
		 * <p>The modal will be configured with two buttons:
		 * <ul>
		 * <li>Close button: closes the modal</li>
		 * <li>Confirm button: follows the process<li>
		 * </ul>
		 * </p>
		 * 
		 * <p>Some behaviour could be added to the buttons using the 
		 * parameters</p>
		 * 
		 * @param popupOptions Map with the configuration of the modal. (See 
		 * parameters of class LibWebPopUp)
		 * @param behaviour Map with the behaviour to set to the buttons. This 
		 * map can contain:
		 * <ul>
		 * <li><code>onClick(evt)</code> : function callback that will be called
		 * when <em>click</em> event of the mouse is fired</li>
		 * </ul>
		 */
		confirm : function (popupOptions, behaviour) {
			
			var confirmationPopup = new $.LibWebPopUp(options);
			
			confirmationPopup.addBtnClose(behaviour);
			confirmationPopup.addBtnConfirm(behaviour);
			
			return confirmationPopup;		
		},
		
		/**
		 * <p>Returns a modal window of type accept. This modal is for telling
		 * the user somethig has happend</p>
		 * 
		 * <p>The modal will be configured with one button:
		 * <ul>
		 * <li>Accept button: closes the modal</li>
		 * </ul>
		 * </p>
		 * 
		 * <p>Some behaviour could be added to the button using the parameters</p>
		 * 
		 * @param popupOptions Map with the configuration of the modal. (See 
		 * parameters of class LibWebPopUp)
		 * @param behaviour Map with the behaviour to set to the buttons. This 
		 * map can contain:
		 * <ul>
		 * <li><code>onClick(evt)</code> : function callback that will be called
		 * when <em>click</em> event of the mouse is fired</li>
		 * </ul>
		 */
		accept : function (popupOptions, behaviour) {
			
			var popup = new $.LibWebPopUp(popupOptions);
			
			popup.addBtnAccept(behaviour);
			
			return popup;
		},
	}
})();