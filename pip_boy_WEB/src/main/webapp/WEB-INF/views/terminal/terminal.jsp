<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
<div class="pipboy-terminal">
    <div class="status-bar">
        VAULT ${vaultStatus.number} | ${vaultStatus.status} | RAD LEVEL: ${vaultStatus.radiationLevel} REM
    </div>
    <div class="main-menu">
    	<button onclick="window.location.href='dwellers'" class="pipboy-button">
    		1. DWELLER MANAGEMENT
		</button>
        <button onclick="window.location.href='inventory'" class="pipboy-button">
    		2. INVENTORY 
		</button>
        <button onclick="window.location.href='radio'" class="pipboy-button">
    		3. RADIO 
		</button>
        <button onclick="window.location.href='alerts'" class="pipboy-button">
    		4. ALERTS 
		</button>
    </div>
</div>