<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="<c:url value='/dwellers/save' />" class="pipboy-form">
    <input type="hidden" name="id" value="${dweller.dwellerId}">
    <div class="form-group">
        <label>Name:</label>
        <input type="text" name="name" value="${dweller.name}" class="pipboy-input">
    </div>
    <div class="form-group">
        <label>Status:</label>
        <select name="status" class="pipboy-select">
            <option value="ACTIVE" ${dweller.status == 'ACTIVE' ? 'selected' : ''}>Active</option>
            <option value="INACTIVE" ${dweller.status == 'INACTIVE' ? 'selected' : ''}>Inactive</option>
        </select>
    </div>
    <button type="submit" class="pipboy-button">SAVE</button>
</form>