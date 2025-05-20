<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Пациент - Панель</title>
    <style>
        body { font-family: Arial; padding: 40px; background-color: #eef2f5; }
        .card { background: white; padding: 30px; border-radius: 8px; max-width: 500px; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        a { display: inline-block; margin-top: 20px; }
    </style>
</head>
<body>
<div class="card">
    <h1>Добро пожаловать, пациент!</h1>
    <p><a href="${pageContext.request.contextPath}/user/medical">📄 Просмотреть медицинскую карту</a></p>
    <form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit">🚪 Выйти</button>
</form>

</div>
</body>
</html>