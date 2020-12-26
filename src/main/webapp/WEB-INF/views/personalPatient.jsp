<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Персональная страница</title>
</head>
<body>
    <div align="center">
        <h2>Личное дело</h2>
        <h3>${patient.surname}&nbsp&nbsp&nbsp&nbsp${patient.name}</h3>
        <h3>
            <a href="/new">Добавить пациентов</a>
            <a href="/main">Список пациентов</a>
        </h3>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <table border="1" cellpadding="5">
                <c:if test="${patient != null}">
                    <input type="hidden" name="id" value="${patient.id}" />
                </c:if>
                <tr>
                    <th>Фамилия</th>
                    <td>
                        ${patient.surname}
                    </td>
                </tr>
                <tr>
                    <th>Имя</th>
                    <td>
                        ${patient.name}
                    </td>
                </tr>
                <tr>
                    <th>Возраст</th>
                    <td>
                        ${patient.age}
                    </td>
                </tr>
                <tr>
                    <th>Диагноз</th>
                    <td>
                        ${patient.disease}
                    </td>
                </tr>
                <tr>
                    <th>Тип</th>
                    <td>
                        <c:if test="${patient.type == true}">
                            Стационар
                        </c:if>
                        <c:if test="${patient.type == false}">
                            Амбулаторный
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>Лечащий врач</th>
                    <td>
                        <input type="text" name="doctor" value="" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="save">
                    </td>
                </tr>
            </table>
        </table>
    </div>
</body>
</html>
