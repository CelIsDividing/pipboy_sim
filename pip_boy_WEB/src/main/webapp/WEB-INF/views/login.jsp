<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>VAULT-TEC Login</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    <div class="pipboy-terminal">
        <div class="status-bar">
            <h3 class="title-header">[VAULT-TEC_SECURITY_ACCESS]</h3>
        </div>
        <div class="main-menu">
            <form method="post" action="${pageContext.request.contextPath}/login" class="pipboy-form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label>>> USER :::: </label>
                    <input type="text" name="username" class="pipboy-input" required>
                </div>
                <div class="form-group">
                    <label>>> PASS :::: </label>
                    <input type="password" name="password" class="pipboy-input" required>
                </div>
                
                <div style="margin-right: 27%; margin-top: 5%">
                    <button type="submit" class="pipboy-button" id="authenticate">AUTHENTICATE</button>
                </div>
                <c:if test="${param.error != null}">
                    <p class="message severity-3">ACCESS DENIED: INVALID CREDENTIALS</p>
                </c:if>
            </form>
        </div>
    </div>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	        'Enter': 'authenticate'
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