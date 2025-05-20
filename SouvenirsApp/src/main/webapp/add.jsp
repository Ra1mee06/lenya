<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление данных</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <div class="text-center mb-5">
        <h1 class="display-6">➕ Добавление данных</h1>
        <p class="text-muted">Добавьте нового производителя или сувенир в базу</p>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>

    <div class="card mb-4">
        <div class="card-header bg-primary text-white">1. Добавить производителя</div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/process" method="POST">
                <input type="hidden" name="queryType" value="addData">
                <input type="hidden" name="dataType" value="manufacturer">

                <div class="mb-3">
                    <label for="manufacturerName" class="form-label">Название:</label>
                    <input type="text" class="form-control" id="manufacturerName" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="country" class="form-label">Страна:</label>
                    <input type="text" class="form-control" id="country" name="country" required>
                </div>
                <button type="submit" class="btn btn-success">Добавить</button>
            </form>
        </div>
    </div>

    <div class="card">
        <div class="card-header bg-success text-white">2. Добавить сувенир</div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/process" method="POST">
                <input type="hidden" name="queryType" value="addData">
                <input type="hidden" name="dataType" value="souvenir">

                <div class="mb-3">
                    <label for="souvenirName" class="form-label">Название сувенира:</label>
                    <input type="text" class="form-control" id="souvenirName" name="name" required>
                </div>

                <div class="mb-3">
                    <label for="manufacturerId" class="form-label">Производитель:</label>
                    <select class="form-select" id="manufacturerId" name="manufacturerId" required>
                        <c:forEach var="m" items="${manufacturers}">
                            <option value="${m.manufacturerId}">${m.name} (${m.country})</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="releaseDate" class="form-label">Дата выпуска:</label>
                    <input type="date" class="form-control" id="releaseDate" name="releaseDate" required>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Цена:</label>
                    <input type="number" class="form-control" id="price" name="price" step="0.01" min="0" required>
                </div>

                <button type="submit" class="btn btn-success">Добавить</button>
            </form>
        </div>
    </div>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">← На главную</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
