<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Система учета сувениров</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <div class="text-center mb-5">
        <h1 class="display-5">📦 Система учета сувениров</h1>
        <p class="lead text-muted">Управляйте информацией о производителях и сувенирах с лёгкостью.</p>
    </div>

    <div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
        <a href="${pageContext.request.contextPath}/search" class="btn btn-primary btn-lg px-4">🔍 Поиск информации</a>
        <a href="${pageContext.request.contextPath}/add" class="btn btn-success btn-lg px-4">➕ Добавить данные</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
