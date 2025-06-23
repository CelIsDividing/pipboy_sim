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
	    <h2>VAULT-TEC TERMINAL ACCESS</h2>
	    <form action="terminal" method="get">
	        <select name="vaultNumber" class="pipboy-select" required>
	            <option value="">SELECT A VAULT</option>
	            <c:forEach items="${vaults}" var="vault">
	                <option value="${vault.vaultNumber}">
	                    Vault ${vault.vaultNumber} (${vault.location})
	                </option>
	            </c:forEach>
	        </select>
	        <br>
	        <button type="submit" class="pipboy-button">ACCESS TERMINAL</button>
	    </form>  
    </div>
</body>
</html>