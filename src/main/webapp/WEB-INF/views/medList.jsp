<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список лекарств</title>
</head>
<body>
<div align="center">
    <h1>Пациенты</h1>
    <h3>
        <a href="/new">Добавить пациентов</a>
        <a href="/main">Список пациентов</a>
        <a href="/medList">Список лекарств</a>
    </h3>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Список лекарств</h2></caption>
        <tr>
            <th rowspan="2">Навзание лекарства</th>
            <th rowspan="2">Дозировка</th>
            <th colspan="3">Время приема</th>
        </tr>
        <tr>
            <th>Утро</th>
            <th>День</th>
            <th>Вечер</th>
        </tr>
        <c:forEach var="med" items="${medicine}">
                <tr>
                    <td>${med.name}</td>
                    <td>${med.count}</td>
                    <td>${med.morning}</td>
                    <td>${med.noon}</td>
                    <td>${med.evening}</td>
                    <td>
                        <a href="/deleteMed?id=${med.id}">Удалить</a>
                        <a href="/editMed?id=${med.id}">Редактировать</a>
                    </td>
                </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
