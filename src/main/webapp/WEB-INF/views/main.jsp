<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Window</title>
</head>
<body>
<form name="deleteButton" method="post" action="/hello">
    <div align="center">
        <h1>Пациенты</h1>
        <h2>
            <a href="/new">Добавить пациентов</a>
            <a href="/main">Список пациентов</a>
        </h2>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Список пацентов</h2></caption>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Возраст</th>
                <th>Болезнь</th>
                <th>Тип</th>
            </tr>
            <c:forEach var="pts" items="${patients}">
                <tr>
                    <td><input type="checkbox" name="id" value="${pts.id}">${pts.id}</td>
                    <td>${pts.name}</td>
                    <td>${pts.surname}</td>
                    <td>${pts.age}</td>
                    <td>${pts.disease}</td>
                    <td>
                        <c:if test="${pts.type == true}">
                            Стационар
                        </c:if>
                        <c:if test="${pts.type == false}">
                            Амбулаторный
                        </c:if>
                    </td>
                    <td>
                        <a href="/edit?id=${pts.id}">Редактировать</a>
                        &nbsp&nbsp&nbsp&nbsp
                        <a href="/delete?id=${pts.id}">Удалить</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</form>
</body>
</html>


