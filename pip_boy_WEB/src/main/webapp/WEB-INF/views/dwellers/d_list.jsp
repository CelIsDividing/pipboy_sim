<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="pipboy-table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${dwellers}" var="dweller">
        <tr>
            <td>${dweller.dwellerId}</td>
            <td>${dweller.name}</td>
            <td>${dweller.status}</td>
            <td>
                <a href="dwellers/form?id=${dweller.dwellerId}">Edit</a>
                <a href="/inventory/vaultDwellers/${dweller.dwellerId}/inventory">Inventory</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="dwellers/form" class="pipboy-button">ADD NEW DWELLER</a>