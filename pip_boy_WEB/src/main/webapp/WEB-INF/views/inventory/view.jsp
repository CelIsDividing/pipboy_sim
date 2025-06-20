<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="pipboy-table">
    <tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Condition</th>
    </tr>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.quantity}</td>
            <td>${item.condition}%</td>
        </tr>
    </c:forEach>
</table>