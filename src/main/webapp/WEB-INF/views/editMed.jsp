<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать лекарства</title>
</head>
<body>
    <div align="center">
        <c:if test="${patient != null}">
        <form action="updateMed" method="post">
        </c:if>
        <c:if test="${patient == null}">
        <form action="insertMed" method="post">
        </c:if>
            <table border="1" cellpadding="5">
                <c:if test="${medicine != null}">
                    <input type="hidden" name="id" value="${medicine.id}" />
                </c:if>
                <tr>
                    <th>Название лекарства</th>
                    <td>
                        <input type="text" name="name" value="${medicine.name}">
                    </td>
                </tr>
                <tr>
                    <th>Дозировка</th>
                    <td>
                        <input type="text" name="count" value="${medicine.count}">
                    </td>
                </tr>
                <tr>
                    <th colspan="2">Время приема лекарства</th>
                </tr>
                <tr>
                    <th>Утро</th>
                    <td>
                        <input type="checkbox" name="type" value="${medicine.morning}">
                    </td>
                </tr>
                <tr>
                    <th>День</th>
                    <td>
                        <input type="checkbox" name="type" value="${medicine.noon}">
                    </td>
                </tr>
                <tr>
                    <th>Вечер</th>
                    <td>
                        <input type="checkbox" name="type" value="${medicine.evening}">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="save">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
