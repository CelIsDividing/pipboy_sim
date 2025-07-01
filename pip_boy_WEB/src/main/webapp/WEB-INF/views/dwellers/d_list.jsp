<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/pipboy.css' />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
<body>
	<img src="<c:url value='/images/pipboy.png' />" class="pipboy-image">
    <div class="pipboy-terminal">
        <div class="pipboy-screen">
      		<div class="vault-header">
                <c:choose>
                    <c:when test="${not empty currentVault}">
                        <h3 class="title-header">VAULT ${currentVault} DWELLER REGISTRY</h3>
                    </c:when>
                </c:choose>
            </div>
            
            <div class="pipboy-list">
                <table class="pipboy-table">
                    <thead>
                        <tr class="pipboy-table-header">
                            <th>ID</th>
                            <th>NAME</th>
                            <th>STATUS</th>
                            <th>ACTIONS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dwellers}" var="dweller">
                            <tr class="pipboy-table-row">
                                <td class="pipboy-table-cell">${dweller.dwellerId}</td>
                                <td class="pipboy-table-cell">${dweller.name}</td>
                                <td class="pipboy-table-cell">${dweller.status}</td>
                                <td class="pipboy-table-cell pipboy-actions">
                                    <a href="dwellers/form?id=${dweller.dwellerId}" class="pipboy-table-link">[EDIT]</a>
                                    <a href="<c:url value='/inventory/dweller/${dweller.dwellerId}'/>" class="pipboy-table-link">[INVENTORY]</a>
                                    <a href="<c:url value='/dwellers/delete/${dweller.dwellerId}'/>" 
                   						class="pipboy-table-link"
                   						onclick="return confirm('Terminate dweller record ${dweller.name}?')">
                    					[DELETE]
                					</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pipboy-controls">
                <a href="dwellers/form" class="pipboy-button">[ADD_NEW_DWELLER]</a>
                <c:if test="${not empty currentVault}">
                    <a href="<c:url value='/terminal?vaultNumber=${currentVault}'/>" 
                       class="pipboy-button">[MAIN_MENU]</a>
                </c:if>
            </div>
        </div>
    </div>
</body>