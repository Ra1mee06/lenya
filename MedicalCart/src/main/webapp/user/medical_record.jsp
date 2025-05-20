<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="medical.title"/></title>
    <style>
        body { font-family: Arial; margin: 20px; }
        h2 { color: #2c3e50; }
        .entry { margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 6px; }
    </style>
</head>
<body>
<h1><fmt:message key="medical.title"/></h1>

<c:if test="${record != null}">
    <p><b><fmt:message key="medical.record_number"/>:</b> ${record.recordNumber}</p>
    <p><b><fmt:message key="medical.created_date"/>:</b> <fmt:formatDate value="${record.creationDate}" pattern="dd.MM.yyyy"/></p>

    <h2><fmt:message key="medical.entries"/></h2>
    <c:if test="${not empty entries}">
        <c:forEach var="entry" items="${entries}">
            <div class="entry">
                <p><b><fmt:message key="medical.entry_date"/>:</b> <fmt:formatDate value="${entry.entryDate}" pattern="dd.MM.yyyy"/></p>
                <p><b><fmt:message key="medical.doctor"/>:</b> ${entry.doctorName}</p>
                <p><b><fmt:message key="medical.diagnosis"/>:</b> ${entry.diagnosis}</p>
                <p><b><fmt:message key="medical.treatment"/>:</b> ${entry.treatment}</p>
                <p><b><fmt:message key="medical.notes"/>:</b> ${entry.notes}</p>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty entries}">
        <p><i><fmt:message key="medical.no_entries"/></i></p>
    </c:if>
</c:if>

<c:if test="${record == null}">
    <p><fmt:message key="medical.no_record"/></p>
</c:if>
</body>
</html>