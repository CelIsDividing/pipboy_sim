<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VAULT-TEC INVENTORY SYSTEM</title>
    <link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
    <img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    
    <div class="pipboy-terminal">
        <div class="pipboy-screen">
            <h2 class="title-header">VAULT-TEC INVENTORY MANAGEMENT</h2>
            
            <div class="pipboy-list">
                <table class="pipboy-table">
                    <thead>
                        <tr class="pipboy-table-header">
                            <th>QTY</th>
                            <th>ITEM</th>
                            <th>COND</th>
                            <th>DWELLER</th>
                            <th>ACTIONS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr class="pipboy-table-row">
                                <td class="pipboy-table-cell">${item.quantity}</td>
                                <td class="pipboy-table-cell">>> ${item.name}</td>
                                <td class="pipboy-table-cell">${item.conditionPercentage}%</td>
                                <td class="pipboy-table-cell">
                                    <c:if test="${item.vaultDweller != null}">
                                        ${item.vaultDweller.name}
                                    </c:if>
                                    <c:if test="${item.vaultDweller == null}">
                                        VAULT STORAGE
                                    </c:if>
                                </td>
                                <td class="pipboy-table-cell pipboy-actions">
                                    <a href="<c:url value='/inventory/transfer/${item.itemId}'/>" 
                                       class="pipboy-table-link">[TRANSFER]</a>
                                    <a href="<c:url value='/inventory/drop/${item.itemId}'/>" 
                                       class="pipboy-table-link">[DROP]</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="pipboy-controls">
                <a href="<c:url value='/inventory/add'/>" class="pipboy-button">[ADD_ITEM]</a>
                <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" class="pipboy-button">[MAIN_MENU]</a>
            </div>
        </div>
    </div>
</body>
</html>