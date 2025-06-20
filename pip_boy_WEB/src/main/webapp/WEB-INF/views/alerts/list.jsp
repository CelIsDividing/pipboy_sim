<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="alert-table">
    <tr>
        <th>Event Type</th>
        <th>Description</th>
        <th>Severity</th>
    </tr>
    <c:forEach items="${alerts}" var="alert">
        <tr class="severity-${alert.severity}">
            <td>${alert.eventType}</td>
            <td>${alert.description}</td>
            <td>${alert.severity}/3</td>
        </tr>
    </c:forEach>
</table>