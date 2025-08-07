<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">

<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
<body>
	<div class="pipboy-terminal">
		<div class="pipboy-screen">
		    <div class="status-bar" style="margin-top: -24px">
		        <h3 class="title-header">YOUR SUPPORT REQUESTS</h3>
		    </div>
		    
		    <div class="pipboy-list">
		        <table class="pipboy-table">
		            <thead class="pipboy-table-header">
		                <tr>
		                    <th>ID</th>
		                    <th>TITLE</th>
		                    <th>STATUS</th>
		                    <th>LAST UPDATE</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach items="${tickets}" var="ticket">
		                    <tr class="severity-${ticket.status == 'RESOLVED' ? '1' : ticket.status == 'AWAITING_RESPONSE' ? '2' : '3'}">
		                        <td class="pipboy-table-cell">${ticket.id}</td>
		                        <td class="pipboy-table-cell">
		                            <a href="<c:url value='/tickets/${ticket.id}' />" class="pipboy-link">${ticket.title}</a>
		                        </td>
		                        <td class="pipboy-table-cell">${ticket.status}</td>
		                        <td class="pipboy-table-cell">
		                            <fmt:formatDate value="${ticket.updatedAt}" pattern="yyyy-MM-dd HH:mm"/>
		                        </td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </div>
		    
		    <div class="pipboy-controls">
		        <a href="<c:url value='/tickets/new' />" class="pipboy-button" style="font-size: 24px; font-weight: bold;">[NEW_REQUEST]</a>
		        <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" id="main-menu" class="pipboy-button" style="font-size: 24px; font-weight: bold;">[MAIN_MENU]</a>
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