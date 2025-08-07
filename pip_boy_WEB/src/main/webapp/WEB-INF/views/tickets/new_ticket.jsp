<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">

<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
<div class="pipboy-terminal">
    <div class="status-bar">
        <h3 class="title-header">NEW SUPPORT REQUEST</h3>
    </div>
    
    <form method="POST" action="<c:url value="/tickets/new" />" class="pipboy-form">
        <div class="form-group">
            <label>[REQUEST_TITLE] >> </label>
            <input type="text" name="title" class="pipboy-input" required id="title"/>
        </div>
        
        <div class="form-group">
            <textarea name="description" id="description" class="pipboy-ticket-input" rows="5" required style="width: 67%; margin-left: 0px"
	                      placeholder="Enter description..."></textarea>
        </div>
        
        <div class="pipboy-controls">
            <button type="submit" class="form-button pipboy-button" id="submit" style="font-size: 24px; font-weight: bold; margin-left: -220px">[SUBMIT_REQUEST]</button>
            <a href="<c:url value='/tickets' />" class="pipboy-button" id="back" style="font-size: 24px; font-weight: bold; margin-left: -100px">[CANCEL]</a>
        </div>
    </form>
</div>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	        'Enter': 'submit',
	        'Escape': 'back'
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