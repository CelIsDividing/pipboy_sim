<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
<div class="pipboy-terminal">
    <div class="status-bar">
    	<h3 class="title-header">VAULT ${vaultStatus['number']} | ${vaultStatus['status']} | RAD LEVEL: ${vaultStatus['radiationLevel']} REM</h3>
    </div>
    <div class="main-menu">
    	<p class="paragraph-style" id="typing-text"></p>
    </div>
</div>
<script>
    const CONTEXT_URLS = {
        dwellers: '<c:url value="/dwellers"/>',
        inventory: '<c:url value="/inventory"/>',
        radio: '<c:url value="/radio"/>',
        alerts: '<c:url value="/alerts"/>',
        home: '<c:url value="/"/>'
    };
</script>
<script src="<c:url value='/javascript/typing-text.js' />"></script>