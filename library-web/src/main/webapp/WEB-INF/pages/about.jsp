<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<p>_version: <span id="versionValue"></span></p>
</div>

<script type="text/javascript">
	
	var spanId = $('#versionValue');
	if (spanId) {
		
		spanId.text("<c:out value='${version}'/>");
	}
</script>