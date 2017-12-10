/**
 * 
 * 
 */
var Loading;

Loading = Loading || (function () {
    
	var loadingDiv = $('<div id="myModal" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"><div class="progress"><div class="progress-bar progress-bar-striped active" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%" role="progressbar" >Loading...</div></div></div></div></div></div>');
    return {
        show: function() {
        	loadingDiv.modal();
        },
        hide: function () {
        	loadingDiv.modal('hide');
        },
    };
})();