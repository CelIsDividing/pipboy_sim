<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC DWELLER INVENTORY</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
        <div class="pipboy-screen"  style="margin-top: -41px">
            <div class="vault-header">
                <h2 class="title-header">DWELLER_[No.${dwellerId}]_INVENTORY</h2>
                <c:if test="${not empty dwellerName}">
                    <h3>> ${dwellerName}</h3>
                </c:if>
            </div>
            
            <div class="pipboy-list" style="margin-top: -30px">
                <table class="pipboy-table">
                    <thead>
                        <tr class="pipboy-table-header">
                            <th>QTY</th>
                            <th>ITEM</th>
                            <th>COND</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr class="pipboy-table-row">
                                <td class="pipboy-table-cell">${item.quantity}</td>
                                <td class="pipboy-table-cell">>> ${item.name}</td>
                                <td class="pipboy-table-cell">${item.conditionPercentage}%</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="pipboy-controls">
                <a href="<c:url value='/dwellers'/>" 
                   class="pipboy-button" id="main-menu">[BACK_TO_DWELLERS]</a>
                <c:if test="${not empty currentVault}">
                    <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" 
                       class="pipboy-button" id="main-menu">[MAIN_MENU]</a>
                </c:if>
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