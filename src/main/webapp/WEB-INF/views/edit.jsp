<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Patient</title>
</head>
<body>
    <div align="center">
        <h1>Форма пациента</h1>
        <h3>
            <a href="/new">Добавить пациентов</a>
            <a href="/main">Список пациентов</a>
        </h3>
    </div>
    <div align="center">
        <c:if test="${patient != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${patient == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <c:if test="${patient != null}">
                <input type="hidden" name="id" value="${patient.id}" />
            </c:if>
            <tr>
                <th>Фамилия</th>
                <td>
                    <input type="text" name="surname" value="${patient.surname}" />
                </td>
            </tr>
            <tr>
                <th>Имя</th>
                <td>
                    <input type="text" name="name" value="${patient.name}" />
                </td>
            </tr>
            <tr>
                <th>Возраст</th>
                <td>
                    <input type="text" name="age" value="${patient.age}" />
                </td>
            </tr>
            <tr>
                <th>Диагноз</th>
                <td>
                    <input type="text" name="disease" value="${patient.disease}" />
                </td>
            </tr>
            <tr>
                <th>Тип</th>
                <td>
                    <select size="1" name="type">
                        <c:if test="${patient.type == true}">
                            <option selected value="true">Стационар</option>
                            <option value="false">Амбулаторный</option>
                        </c:if>
                        <c:if test="${patient.type == false}">
                            <option value="true">Стационар</option>
                            <option selected value="false">Амбулаторный</option>
                        </c:if>
                        <c:if test="${patient.type == null}">
                            <option value="true">Стационар</option>
                            <option value="false">Амбулаторный</option>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Лечащий врач</th>
                <td>
                    <select size="1" name="doc">
                        <c:forEach var="doc" items="${doctors}">
                            <c:if test="${patient.docId == doc.id}">
                                <option selected value="${doc.id}">${doc.surname}&nbsp${doc.name}</option>
                            </c:if>
                            <option value="${doc.id}">${doc.surname}&nbsp${doc.name}</option>
                        </c:forEach>
                    </select>
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
