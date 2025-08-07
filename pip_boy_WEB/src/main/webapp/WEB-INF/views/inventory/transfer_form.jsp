<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Item</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
</head>
<body>
	<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    <div class="pipboy-terminal">
        <div class="pipboy-screen" style="margin-top: -41px">
	        <div class="vault-header">
	        	<h2 class="title-header">TRANSFER ITEM</h2>
	        </div>
            
            <form action="<c:url value='/inventory/transfer/${itemId}'/>" method="post">
                <div class="pipboy-form-group">
                    <label>SELECT DWELLER:</label>
                    <select name="dwellerId" class="pipboy-select">
                        <c:forEach items="${dwellers}" var="dweller">
                            <option value="${dweller.dwellerId}">${dweller.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="pipboy-controls" style="margin-top: 112px">
	                <button type="submit" class="pipboy-button" style="font-size: 26px;" id="confirm">[CONFIRM]</button>
	                <a href="<c:url value='/inventory'/>" class="pipboy-button" id="main-menu" style="font-size: 26px;">[CANCEL]</a>
                </div>
            </form>
        </div>
    </div>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	    	'Enter': 'confirm',
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