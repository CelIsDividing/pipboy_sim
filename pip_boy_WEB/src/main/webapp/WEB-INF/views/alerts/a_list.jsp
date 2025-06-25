<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC SECURITY ALERTS</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
        <div class="pipboy-screen">
            <div class="vault-header">
                <h3>VAULT-TEC SECURITY ALERT SYSTEM</h3>
            </div>
            
            <div class="pipboy-list">
                <table class="pipboy-table">
                    <thead>
                        <tr class="pipboy-table-header">
                            <th>SEV</th>
                            <th>EVENT TYPE</th>
                            <th>STATUS</th>
                            <th>DESCRIPTION</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${alerts}" var="alert">
                            <tr class="pipboy-table-row severity-${alert.severity}">
                                <td class="pipboy-table-cell">
                                    ${alert.severity}/3
                                </td>
                                <td class="pipboy-table-cell">
                                    >> ${alert.eventType}
                                </td>
                                <td class="pipboy-table-cell">
                                    <c:choose>
                                        <c:when test="${alert.severity == 1}">MONITOR</c:when>
                                        <c:when test="${alert.severity == 2}">INVESTIGATE</c:when>
                                        <c:when test="${alert.severity == 3}">CRITICAL</c:when>
                                    </c:choose>
                                </td>
                                <td class="pipboy-table-cell">
                                    ${alert.description}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="pipboy-controls">
                <a href="<c:url value='/alerts/clear'/>" class="pipboy-button">[CLEAR_ALERTS]</a>
                <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" class="pipboy-button">[MAIN_MENU]</a>
            </div>
        </div>
    </div>
</body>
</html>