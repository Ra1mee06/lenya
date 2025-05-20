<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Поиск информации</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h1 class="mb-4">Поиск информации о сувенирах и производителях</h1>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <!-- 1 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">1. Сувениры производителя</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="souvenirsByManufacturer">
                    <div class="mb-3">
                        <label for="manufacturerId" class="form-label">ID производителя:</label>
                        <input type="number" class="form-control" id="manufacturerId" name="manufacturerId" required>
                    </div>
                    <button type="submit" class="btn btn-success">Найти</button>
                </form>
            </div>
        </div>

        <!-- 2 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">2. Сувениры из страны</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="souvenirsByCountry">
                    <div class="mb-3">
                        <label for="country" class="form-label">Страна:</label>
                        <input type="text" class="form-control" id="country" name="country" required>
                    </div>
                    <button type="submit" class="btn btn-success">Найти</button>
                </form>
            </div>
        </div>

        <!-- 3 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">3. Производители с ценами ниже заданной</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="manufacturersWithPricesBelow">
                    <div class="mb-3">
                        <label for="price" class="form-label">Максимальная цена:</label>
                        <input type="number" class="form-control" id="price" name="price" step="0.01" required>
                    </div>
                    <button type="submit" class="btn btn-success">Найти</button>
                </form>
            </div>
        </div>

        <!-- 4 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">4. Производители сувенира за год</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="manufacturersBySouvenirAndYear">
                    <div class="mb-3">
                        <label for="souvenirName" class="form-label">Название сувенира:</label>
                        <input type="text" class="form-control" id="souvenirName" name="souvenirName" required>
                    </div>
                    <div class="mb-3">
                        <label for="year" class="form-label">Год выпуска:</label>
                        <input type="number" class="form-control" id="year" name="year" min="1900" max="2100" required>
                    </div>
                    <button type="submit" class="btn btn-success">Найти</button>
                </form>
            </div>
        </div>

        <!-- 5 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">5. Удаление производителя</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="deleteManufacturer">
                    <div class="mb-3">
                        <label for="manufacturerIdToDelete" class="form-label">ID производителя:</label>
                        <input type="number" class="form-control" id="manufacturerIdToDelete" name="manufacturerId" required>
                    </div>
                    <button type="submit" class="btn btn-danger">Удалить</button>
                </form>
            </div>
        </div>

        <!-- 6 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">6. Все производители</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="allManufacturers">
                    <button type="submit" class="btn btn-primary">Показать всех</button>
                </form>
            </div>
        </div>

        <!-- 7 -->
        <div class="col">
            <div class="card shadow-sm p-3">
                <h5 class="card-title">7. Все сувениры</h5>
                <form action="${pageContext.request.contextPath}/process" method="POST">
                    <input type="hidden" name="queryType" value="allSouvenirs">
                    <button type="submit" class="btn btn-primary">Показать все</button>
                </form>
            </div>
        </div>
    </div>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">← На главную</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
