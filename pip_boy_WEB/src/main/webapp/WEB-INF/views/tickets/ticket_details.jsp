<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">

<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
<div class="pipboy-terminal">
	<div class="pipboy-screen" style="margin-left: -15px">
	    <div class="status-bar">
	        <h3 class="title-header">TICKET #${ticket.id}: ${ticket.title}</h3>
	        <h4>STATUS: <span class="severity-${ticket.status == 'RESOLVED' ? '1' : ticket.status == 'AWAITING_RESPONSE' ? '2' : '3'}">
	            ${ticket.status}
	        </span></h4>
	    </div>
	    
	    <div class="message-log">
	        <div class="message" style="padding-left: 60px">
	            <strong>${ticket.submitter.name}:</strong>
	            <p>${ticket.description}</p>
	            <span class="timestamp">
	                <fmt:formatDate value="${ticket.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
	            </span>
	        </div>
	        
	        <c:forEach items="${ticket.messages}" var="message">
	            <div class="message" style="padding-left: 60px">
	                <strong>${message.sender.name}:</strong>
	                <p>${message.content}</p>
	                <span class="timestamp">
	                    <fmt:formatDate value="${message.sentAt}" pattern="yyyy-MM-dd HH:mm"/>
	                </span>
	            </div>
	        </c:forEach>
	    </div>
	    
	    <form method="post"  style="padding-left: 60px" action="${pageContext.request.contextPath}/tickets/${ticket.id}/respond" class="pipboy-form">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <div class="form-group">
	            <textarea name="content" class="pipboy-ticket-input" rows="3" required 
	                      placeholder="Enter your response..."></textarea>
	            <button type="submit" id="send" class="pipboy-controls pipboy-button" style="font-size: 24px; font-weight: bold; margin: 20px">[ SEND ]</button>
	        </div>
	        
	        <div class="pipboy-controls" style="margin-left: -250px; margin-top: 50px">
	            <button type="submit" name="finalize" value="true" class="pipboy-button" style="font-size: 24px; font-weight: bold;">[MARK RESOLVED]</button>
	            <a href="<c:url value='/tickets' />" id="back-to-list" class="pipboy-button" style="font-size: 24px; font-weight: bold;">[BACK_TO_LIST]</a>
	        </div>
	    </form>
	    <br><br><br>
   	</div>
</div>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	        'Enter': 'send',
	        'Escape': 'back-to-list'
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