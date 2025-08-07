<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap"
	rel="stylesheet">
<body>
	<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">

	<div class="pipboy-terminal">
		<div class="pipboy-screen">
			<form method="post" action="<c:url value='/dwellers/save' />"
				class="pipboy-form">
				<h2 class="form-title title-header">DWELLER RECORD EDITOR</h2>

				<input type="hidden" name="dwellerId" value="${dweller.dwellerId}">

				<c:if test="${not empty currentVault}">
					<input type="hidden" name="vault.vaultNumber"
						value="${currentVault}">
				</c:if>

				<div class="form-group">
					<label>>> [NAME]:::::::::::::::::::::</label> <input type="text"
						name="name" value="${dweller.name}" class="pipboy-input" required id="name">
				</div>

				<div class="form-group">
					<label>>> [STATUS]:::::::::::::::::::</label> <select name="status"
						class="pipboy-form-select" required>
						<option value="ACTIVE"
							${dweller.status == 'ACTIVE' ? 'selected' : ''}>ACTIVE</option>
						<option value="MISSING"
							${dweller.status == 'MISSING' ? 'selected' : ''}>MISSING</option>
						<option value="DECEASED"
							${dweller.status == 'DECEASED' ? 'selected' : ''}>DECEASED</option>
						<option value="MEDICAL_BAY"
							${dweller.status == 'MEDICAL_BAY' ? 'selected' : ''}>MEDICAL_BAY</option>
					</select>
				</div>

				<div class="form-group">
					<label>>> [GENDER]:::::::::::::::::::</label> <select name="gender"
						class="pipboy-form-select" required>
						<option value="MALE" ${dweller.gender == 'MALE' ? 'selected' : ''}>MALE</option>
						<option value="FEMALE"
							${dweller.gender == 'FEMALE' ? 'selected' : ''}>FEMALE</option>
						<option value="OTHER"
							${dweller.gender == 'OTHER' ? 'selected' : ''}>OTHER</option>
					</select>
				</div>

				<div class="form-group">
					<label>>> [STRENGTH]:::::::::::::::::</label> <input type="number"
						name="strength" value="${dweller.strength}" class="pipboy-input"
						min="1" max="10" required>
				</div>

				<div class="form-group">
					<label>>> [INTELLIGENCE]:::::::::::::</label> <input type="number"
						name="intelligence" value="${dweller.intelligence}"
						class="pipboy-input" min="1" max="10" required>
				</div>

				<div class="form-group">
					<label>>> [RADIATION_LEVEL]::::::::::</label> <input type="number"
						step="0.01" name="radiationLevel"
						value="${dweller.radiationLevel}" class="pipboy-input" min="0"
						required>
				</div>

				<div class="form-group">
					<label>>> [JOIN_DATE]::::::::::::::::</label> <input type="date"
						name="joinDate"
						value="<fmt:formatDate value='${dweller.joinDate}' pattern='yyyy-MM-dd' />"
						class="pipboy-input" required>
				</div>

				<div class="form-group">
					<label>>> [LAST_SEEN]::::::::::::::::</label> <input
						type="datetime-local" name="lastSeen"
						value="<fmt:formatDate value='${dweller.lastSeen}' pattern='yyyy-MM-dd\'T\'HH:mm' />"
						class="pipboy-input" required>
				</div>

				<div class="form-group">
					<label>>> [ACCESS_LEVEL]:::::::::::::</label> <select
						name="securityClearance" class="pipboy-form-select" required
						id="accessLevel">
						<option value="ADMIN"
							${dweller.securityClearance == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
						<option value="SECURITY"
							${dweller.securityClearance == 'SECURITY' ? 'selected' : ''}>SECURITY</option>
						<option value="SCIENTIST"
							${dweller.securityClearance == 'SCIENTIST' ? 'selected' : ''}>SCIENTIST</option>
						<option value="DWELLER"
							${dweller.securityClearance == 'DWELLER' ? 'selected' : ''}>DWELLER</option>
					</select>
				</div>

				<input type="hidden" name="username" id="usernameField"> 
				<input type="hidden" name="passwordHash" id="passwordField"> 
				<br>
				<button type="submit" class="form-button pipboy-button" id="save">[SAVE_RECORD]</button>
				<br>
				<br>
			</form>
		</div>
	</div>
	
	<script src="<c:url value='/javascript/password_hashing.js' />"></script>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const hotkeys = {
	        'Enter': 'save'
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