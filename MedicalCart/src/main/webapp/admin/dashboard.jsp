<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Панель администратора</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; padding: 40px; }
        .container { background: white; padding: 30px; max-width: 700px; margin: auto; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        input, select { width: 100%; padding: 10px; margin: 6px 0; border-radius: 4px; border: 1px solid #ccc; }
        button { background: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 5px; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Создать медицинскую карту</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/addRecord">
        ID пациента: <input type="number" name="userId" required><br>
        Дата создания: <input type="date" name="createdDate" required><br>
        <button type="submit">Создать карту</button>
    </form>

    <h2 style="margin-top:40px;">Добавить пациента</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/addUser">
        Логин: <input type="text" name="username" required><br>
        Пароль: <input type="password" name="password" required><br>
        Роль: <select name="role">
            <option value="PATIENT">Пациент</option>
            <option value="DOCTOR">Врач</option>
        </select><br>
        ФИО: <input type="text" name="fullName"><br>
        Дата рождения: <input type="date" name="birthDate"><br>
        Номер страховки: <input type="text" name="insuranceNumber"><br>
        <button type="submit">Добавить пользователя</button>
    </form>

    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit">🚪 Выйти</button>
</form>

</div>
</body>
</html>