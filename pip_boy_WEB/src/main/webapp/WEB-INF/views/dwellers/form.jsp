<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
    	<div class="pipboy-screen">
	         <form method="post" action="<c:url value='/dwellers/save' />" class="pipboy-form">
	            <h2 class="form-title">DWELLER RECORD EDITOR</h2>
	            
	            <input type="hidden" name="id" value="${dweller.dwellerId}">
	            
	            <div class="form-group">
	                <label>>> [NAME]:::::::::::::::::::::</label>
	                <input type="text" name="name" value="${dweller.name}" class="pipboy-input" required>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [STATUS]:::::::::::::::::::</label>
	                <select name="status" class="pipboy-form-select" required>
	                    <option value="ACTIVE" ${dweller.status == 'ACTIVE' ? 'selected' : ''}>ACTIVE</option>
	                    <option value="INACTIVE" ${dweller.status == 'INACTIVE' ? 'selected' : ''}>INACTIVE</option>
	                </select>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [GENDER]:::::::::::::::::::</label>
	                <select name="gender" class="pipboy-form-select" required>
	                    <option value="MALE" ${dweller.gender == 'MALE' ? 'selected' : ''}>MALE</option>
	                    <option value="FEMALE" ${dweller.gender == 'FEMALE' ? 'selected' : ''}>FEMALE</option>
	                    <option value="OTHER" ${dweller.gender == 'OTHER' ? 'selected' : ''}>OTHER</option>
	                </select>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [STRENGTH]:::::::::::::::::</label>
	                <input type="number" name="strength" value="${dweller.strength}" class="pipboy-input" min="1" max="10" required>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [INTELLIGENCE]:::::::::::::</label>
	                <input type="number" name="intelligence" value="${dweller.intelligence}" class="pipboy-input" min="1" max="10" required>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [RADIATION LEVEL]::::::::::</label>
	                <input type="number" step="0.01" name="radiationLevel" value="${dweller.radiationLevel}" class="pipboy-input" min="0" required>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [JOIN DATE]::::::::::::::::</label>
	                <input type="date" name="joinDate" value="<fmt:formatDate value='${dweller.joinDate}' pattern='yyyy-MM-dd' />" class="pipboy-input" required>
	            </div>
	            
	            <div class="form-group">
	                <label>>> [LAST SEEN]::::::::::::::::</label>
	                <input type="datetime-local" name="lastSeen" value="<fmt:formatDate value='${dweller.lastSeen}' pattern='yyyy-MM-dd\'T\'HH:mm' />" class="pipboy-input" required>
	            </div>
	            <br>
	            <button type="submit" class="form-button pipboy-button">SAVE RECORD</button>
	            <br><br>
	        </form>
        </div>
    </div>
    
</body>