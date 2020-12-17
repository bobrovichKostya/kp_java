<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Patient</title>
</head>
<body>
    <div align="center">
        <h1>Форма пациента</h1>
        <h2>
            <a href="/new">Добавить пациентов</a>
            <a href="/main">Список пациентов</a>
        </h2>
    </div>
    <div align="center">
        <c:if test="${patient != null}">
            <form action="/update" method="post">
        </c:if>
        <c:if test="${patient == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <c:if test="${patient != null}">
                <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
            </c:if>
            <tr>
                <th>Имя</th>
                <td>
                    <input type="text" name="name" value="<c:out value='${patient.name}' />" />
                </td>
            </tr>
            <tr>
                <th>Фамилия</th>
                <td>
                    <input type="text" name="surname" value="<c:out value='${patient.surname}' />" />
                </td>
            </tr>
            <tr>
                <th>Возраст</th>
                <td>
                    <input type="text" name="age" value="<c:out value='${patient.age}' />" />
                </td>
            </tr>
            <tr>
                <th>Болезнь</th>
                <td>
                    <input type="text" name="disease" value="<c:out value='${patient.disease}' />" />
                </td>
            </tr>
            <tr>
                <th>Тип</th>
                <td>
                    <input type="text" name="type" value="<c:out value='${patient.type}' />" />
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="save">
                </td>
            </tr>
        </table>
            </form>
    </div>
</body>
</html>
