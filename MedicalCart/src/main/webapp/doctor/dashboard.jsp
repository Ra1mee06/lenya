<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Панель врача</title>
    <style>
        body { font-family: Arial; background: #f0f4f8; padding: 40px; }
        .container { background: white; padding: 30px; max-width: 600px; margin: auto; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        input, textarea { width: 100%; padding: 8px; margin: 5px 0; border-radius: 4px; border: 1px solid #ccc; }
        button { background: #007BFF; color: white; padding: 10px 15px; border: none; border-radius: 5px; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Добавить запись в медкарту</h2>
    <form method="post" action="${pageContext.request.contextPath}/doctor/addEntry">
        ID карты (record_id): <input type="number" name="recordId" required><br>
        Дата записи: <input type="date" name="entryDate" required><br>
        Врач: <input type="text" name="doctorName" required><br>
        Диагноз: <textarea name="diagnosis" required></textarea><br>
        Лечение: <textarea name="treatment"></textarea><br>
        Примечания: <textarea name="notes"></textarea><br>
        <button type="submit">Добавить запись</button>
    </form>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit">🚪 Выйти</button>
</form>

</div>
</body>
</html>