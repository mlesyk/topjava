<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>

<table border="1px">
<c:forEach items="${exceedMealList}" var="exceedMeal">
        <tr ${exceedMeal.isExceed() ? 'style="color:red"' : 'style="color:green"'} >
            <td>
                <c:set var="cleanedDateTime" value="${fn:replace(exceedMeal.getDateTime(), 'T', ' ')}" />
                <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
            </td>
            <td>${exceedMeal.getDescription()}</td>
            <td>${exceedMeal.getCalories()}</td>
        </tr>
</c:forEach>
</table>
</body>
</html>
