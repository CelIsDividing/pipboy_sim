<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC RADIO INTERFACE</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
        <div class="pipboy-screen">
            <h2 class="radio-header">VAULT-TEC RADIO INTERFACE</h2>
            
            <div class="station-list">
                <c:forEach items="${stations}" var="station">
                    <div class="station-item">
                        <a href="<c:url value='/radio/tune/${station.frequency}'/>" class="station-link">
                            [${station.name}]
                            <span class="frequency"> >> ${station.frequency} MHz</span>
                        </a>
                    </div>
                </c:forEach>
            </div>
            
            <div class="pipboy-controls">
                <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" class="pipboy-button">BACK TO TERMINAL</a>
            </div>
        </div>
    </div>
</body>
</html>