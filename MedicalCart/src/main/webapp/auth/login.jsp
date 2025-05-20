<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Вход</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f0f2f5;
            text-align: center;
            padding-top: 100px;
        }
        .login-box {
            background: white;
            padding: 40px;
            border-radius: 10px;
            width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        input {
            margin: 10px;
            padding: 10px;
            width: 80%;
        }
        button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h2>Вход в систему</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="username" placeholder="Логин" required /><br/>
        <input type="password" name="password" placeholder="Пароль" required /><br/>
        <button type="submit">Войти</button>
    </form>
    <p style="color: red;">${error != null ? error : ""}</p>
</div>
</body>
</html>