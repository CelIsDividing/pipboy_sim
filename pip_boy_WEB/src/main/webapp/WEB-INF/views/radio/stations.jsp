<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="radio-interface">
    <h2>RADIO STATIONS</h2>
    <ul class="station-list">
        <c:forEach items="${stations}" var="station">
            <li>
                <a href="/radio/tune/${station.frequency}">
                    ${station.name} (${station.frequency} MHz)
                </a>
            </li>
        </c:forEach>
    </ul>
</div>