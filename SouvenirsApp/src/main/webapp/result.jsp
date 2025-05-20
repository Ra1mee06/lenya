<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результаты</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h1 class="mb-4">Результаты запроса</h1>

    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>

    <c:if test="${not empty results}">
        <c:choose>
            <c:when test="${resultType eq 'manufacturers'}">
                <table class="table table-bordered table-striped">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Страна</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${results}" var="manufacturer">
                            <tr>
                                <td>${manufacturer.manufacturerId}</td>
                                <td>${manufacturer.name}</td>
                                <td>${manufacturer.country}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>

            <c:when test="${resultType eq 'souvenirs'}">
                <table class="table table-bordered table-striped">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>ID производителя</th>
                            <th>Дата выпуска</th>
                            <th>Цена</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${results}" var="souvenir">
                            <tr>
                                <td>${souvenir.souvenirId}</td>
                                <td>${souvenir.name}</td>
                                <td>${souvenir.manufacturerId}</td>
                                <td>${souvenir.releaseDate}</td>
                                <td>${souvenir.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
    </c:if>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">← На главную</a>
    </div>
</div>
</body>
</html>
