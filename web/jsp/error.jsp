<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Error Page</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h3>${requestScope.result}</h3>
        <c:if test="${not empty sessionScope}">
            <c:remove var="*" scope="session" />
            <c:remove var="javax.servlet.error.exception" scope="request" />
        </c:if>
        <%@ include file="footer.jsp" %>
    </body>
</html>
