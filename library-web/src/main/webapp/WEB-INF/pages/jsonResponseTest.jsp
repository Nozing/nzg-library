<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>Página para probar el funcionamiento de las llamadas Ajax</p>

<ul>
	<c:url var="successUrl" value="/ajax/test/response/success.do" />
	<li>Hacer llamada de <em>success</em>: <a class="success" href="${successUrl }">click!</a></li>
	<li>Hacer llamada de <em>warning</em>: click!</li>
	<li>Hacer llamada de <em>error</em>: click!</li>
</ul>

<div class="modal hide" id="loadingId" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-header">
		<h1>Processing...</h1>
	</div>
	<div class="modal-body">
		<div class="progress progress-striped active">
			<div class="bar" style="width: 100%;"></div>
		</div>
	</div>
</div>
<script type="text/javascript">

var successAction = $('a.success');
if (successAction) {
	
	successAction.click(function(ev) {
	
		if (ev) ev.preventDefault();
		
		successLink = $(this);
		AjaxUtil.GET({
			url : successLink.attr('href'),
			beforeSend: function (qXHR, settings) { 

				console.log("Ponemos el velo");
				Loading.show();
				console.log("Arrancamos el 'loading'");
			},
			complete : function (jqXHR, textStatus) { 
				
				console.log("Cerramos el 'loading'");
				
			},
			error : function (jqXHR, textStatus, errorThrown) { 
				
				console.log("Modificamos el 'loading' para indicar un error")
			},
			success : function () {
				
				console.log("Quitamos el velo");
				Loading.hide();
			}
		});
	});
}

</script>