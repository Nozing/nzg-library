(function($) { 
	
	/*
	 * Clase que encapsula los comportamientos comunes de los formularios de
	 * la aplicación.
	 * 
	 * Para instanciarlos será necesario pasarle una instancia de jQuery que
	 * sea un formulario o algo que nos permita identificar al formulario
	 * que queremos envolver.
	 * 
	 * Como parámetros opcional puede recibir un objeto de opciones para 
	 * sobreesribir cierto comportamiento. Los valores por defecto del mismo
	 * son los siguientes:
	 * 
	 * 'btnClean' : atributo que indica un selector que permite identificar
	 * el botón al que queremos añadir la funcionalidad de limpiar  
	 */
	$.LibWebForm = function(form, options) { 
		
		this.form = (form instanceof $) ? form : $(form);

		this._options = $.extend({}, this._options, options);
		this._configure(options);
	};
	
	$.LibWebForm.prototype = {
		
		_options : {
			btnClean : '.btn.clean'
		},
				
		_configure : function(options) {
			
			instance = this;
			
			var cleanBut = this.form.find(this._options.btnClean);
			if (cleanBut) {
				
				cleanBut.click(function (event) {
					
					if (event) event.preventDefault();
					
					instance.clean();
				});	
			} else {
				
				console.log("No '" + this._options.btnClean + "' found");
			}
		},
		
		/**
		 * Método que limpia todos los campos del formulario 
		 */
		clean : function () {
			
			this.form.find("input").removeAttr('checked')
			  .removeAttr('selected')
			  .not(':button, :submit, :reset, :hidden, :radio, :checkbox')
			  .val('');
		}
	}

}(jQuery));