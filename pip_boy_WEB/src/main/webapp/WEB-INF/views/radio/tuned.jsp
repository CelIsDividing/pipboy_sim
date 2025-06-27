<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC RADIO MESSAGES</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
        <div class="pipboy-screen">
            <h2 class="radio-header">${station.name} (${station.frequency} MHz)</h2>
            
            <div class="message-log">
                <c:forEach items="${messages}" var="message">
                    <div class="message">
                        <strong>${message.sender}:</strong> 
                        ${message.content}
                        <span class="timestamp">
                            <fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/>
                        </span>
                    </div>
                </c:forEach>
            </div>
            
            <div class="pipboy-controls">
                <a href="<c:url value='/radio'/>" class="pipboy-button">[BACK_TO_STATIONS]</a>
            </div>
        </div>
    </div>
</body>
</html>