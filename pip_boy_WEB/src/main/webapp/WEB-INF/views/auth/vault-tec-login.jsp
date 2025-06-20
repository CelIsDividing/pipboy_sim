<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>VAULT-TEC Login</title>
    <link rel="stylesheet" href="/css/pipboy.css">
</head>
<body class="pipboy-terminal">
    <div class="screen">
        <h1>VAULT-TEC SECURE ACCESS</h1>
        <form method="post" action="/auth/login">
            <div class="form-group">
                <label>Vault ID:</label>
                <input type="text" name="vaultId" class="pipboy-input">
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" class="pipboy-input">
            </div>
            <button type="submit" class="pipboy-button">AUTHENTICATE</button>
        </form>
    </div>
</body>
</html>