<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="radio-interface">
    <h2>RADIO MESSAGES</h2>
    <div class="message-log">
        <c:forEach items="${messages}" var="message">
            <div class="message">
                <strong>${message.sender}:</strong> ${message.content}
            </div>
        </c:forEach>
    </div>
</div>