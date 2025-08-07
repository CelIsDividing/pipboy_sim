<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ACCESS DENIED</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
<div class="pipboy-terminal">
    
    <div class="screen">
        <div class="status-bar">
            <h3 class="title-header">[VAULT-TEC_SECURITY_ALERT]</h3>
        </div>
        <div class="main-menu">
            <div class="message-log" style="text-align: left; margin-left: 18%">
                <div class=" severity-3">
                    <strong>WARNING: UNAUTHORIZED ACCESS ATTEMPT</strong>
                </div>
                <br>
                <div class="">
                    <span class="">[SYSTEM_NOTICE]</span> LACKING REQUIRED CLEARANCE LEVEL
                </div>
                <div class="">
                    <span class="">[SECURITY_LOG]</span> THIS INCIDENT HAS BEEN RECORDED
                </div>
            </div>
            
            <div class="pipboy-controls">
                <a href="${pageContext.request.contextPath}/terminal" class="pipboy-button" id="main-menu">
                    [MAIN_MENU]
                </a>
            </div>
        </div>
    </div>
</div>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	        'Escape': 'main-menu'
	    };
	
	    document.addEventListener('keydown', function(event) {
	        const key = event.key.toLowerCase();
	        const actionId = hotkeys[key] || hotkeys[event.key];
	        
	        if (actionId) {
	            event.preventDefault();
	            const element = document.getElementById(actionId);
	            if (element) {
	                // Visual feedback
	                element.classList.add('pipboy-button-active');
	                setTimeout(() => element.classList.remove('pipboy-button-active'), 200);
	                
	                // Trigger click after visual feedback starts
	                setTimeout(() => element.click(), 50);
	            }
	        }
	    });
	});
</script>
</html>