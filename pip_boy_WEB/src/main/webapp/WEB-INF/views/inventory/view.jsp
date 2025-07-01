<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC DWELLER INVENTORY</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
        <div class="pipboy-screen"  style="margin-top: -41px">
            <div class="vault-header">
                <h2 class="title-header">DWELLER_[No.${dwellerId}]_INVENTORY</h2>
                <c:if test="${not empty dwellerName}">
                    <h3>> ${dwellerName}</h3>
                </c:if>
            </div>
            
            <div class="pipboy-list" style="margin-top: -30px">
                <table class="pipboy-table">
                    <thead>
                        <tr class="pipboy-table-header">
                            <th>QTY</th>
                            <th>ITEM</th>
                            <th>COND</th>
                            <th>ACTIONS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr class="pipboy-table-row">
                                <td class="pipboy-table-cell">${item.quantity}</td>
                                <td class="pipboy-table-cell">>> ${item.name}</td>
                                <td class="pipboy-table-cell">${item.conditionPercentage}%</td>
                                <td class="pipboy-table-cell pipboy-actions">
                                    <a href="<c:url value='/inventory/transfer/${item.itemId}'/>" 
                                       class="pipboy-table-link">[TRANSFER]</a>
                                    <a href="<c:url value='/inventory/drop/${item.itemId}'/>" 
                                       class="pipboy-table-link"
                                       onclick="return confirm('Drop ${item.name}?')">[DROP]</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="pipboy-controls">
                <a href="<c:url value='/inventory/dweller/${dwellerId}/add'/>" 
                   class="pipboy-button">[ADD_ITEM]</a>
                <a href="<c:url value='/dwellers'/>" 
                   class="pipboy-button">[BACK_TO_DWELLERS]</a>
                <c:if test="${not empty currentVault}">
                    <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" 
                       class="pipboy-button">[MAIN_MENU]</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>