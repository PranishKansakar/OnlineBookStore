<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Thank You</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
    <%@ include file="header.jsp" %>
    <h2>Thank You For Shopping at Bookstore</h2>
    <hr />
    <h3>Your credit card details are being validated</h3>

    <c:if test="${not empty sessionScope}">
        <c:remove var="*" scope="session" />
    </c:if>
    <%@ include file="footer.jsp" %>
</body>
</html>
