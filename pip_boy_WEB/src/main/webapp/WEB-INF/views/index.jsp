<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC ACCESS</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
	<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
	<div class="pipboy-terminal">
	    <h2 class="title-header">VAULT-TEC TERMINAL ACCESS</h2>
	    <form action="terminal" method="get">
	        <select name="vaultNumber" class="pipboy-select" required>
	            <option value="">[SELECT A VAULT]</option>
	            <c:forEach items="${vaults}" var="vault">
	                <option value="${vault.vaultNumber}">
	                    Vault ${vault.vaultNumber} (${vault.location})
	                </option>
	            </c:forEach>
	        </select>
	        <br>
	        <button type="submit" class="pipboy-button" id="access">[ACCESS TERMINAL]</button>
	    </form>  
    </div>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	        'Enter': 'access'
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