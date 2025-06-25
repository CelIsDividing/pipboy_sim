<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pipboy-terminal">
    <div class="pipboy-screen">
        <h2>DWELLER #${dwellerId} INVENTORY</h2>
        <table class="pipboy-table">
            <thead>
                <tr class="pipboy-table-header">
                    <th>ITEM</th>
                    <th>QUANTITY</th>
                    <th>CONDITION</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item">
                    <tr class="pipboy-table-row">
                        <td>${item.name}</td>
                        <td>${item.quantity}</td>
                        <td>${item.conditionPercentage}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pipboy-controls">
            <a href="<c:url value='/inventory'/>" class="pipboy-button">BACK TO INVENTORY</a>
        </div>
    </div>
</div>