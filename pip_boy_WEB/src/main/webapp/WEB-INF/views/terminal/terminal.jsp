<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pipboy-terminal">
    <div class="status-bar">
        VAULT ${vaultStatus.number} | ${vaultStatus.status} | RAD LEVEL: ${vaultStatus.radiationLevel} REM
    </div>
    <div class="main-menu">
        <a href="/dwellers" class="menu-item">1. DWELLER MANAGEMENT</a>
        <a href="/inventory" class="menu-item">2. INVENTORY</a>
        <a href="/radio" class="menu-item">3. RADIO</a>
        <a href="/alerts" class="menu-item">4. ALERTS</a>
    </div>
</div>