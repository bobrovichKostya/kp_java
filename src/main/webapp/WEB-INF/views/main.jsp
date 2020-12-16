<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Window</title>
</head>
<body>
<form name="deleteButton" method="post" action="/hello">
    <table border=1>
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
                <td><input type="checkbox" name="id" value="${pts.id}"></td>
                <td>${pts.name}</td>
                <td>${pts.surname}</td>
                <td>${pts.age}</td>
                <td>${pts.disease}</td>
                <td>${pts.type}</td>
            </tr>
        </c:forEach>
        <input type="submit" name="delete" value="Action">
        <input type="checkbox" name="check" value="delete">
        <%-- NPE --%>
        <input type="checkbox" name="check" value="edit">

    </table>
</form>
</body>
</html>


