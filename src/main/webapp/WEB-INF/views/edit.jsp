<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Patient</title>
</head>
<body>
<table border=1>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Возраст</th>
        <th>Болезнь</th>
        <th>Тип</th>
    </tr>
    <c:forEach var="pts" items="${editPatient}">
        <tr>
            <td>${pts.name}</td>
            <td>${pts.surname}</td>
            <td>${pts.age}</td>
            <td>${pts.disease}</td>
            <td>${pts.type}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
