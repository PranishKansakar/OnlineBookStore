<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Thank You</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h2>Online Bookstore</h2>
        <hr />
        <h3>Thank you for shopping with us.</h3>

        <c:if test="${not empty sessionScope}">
            <c:remove var="*" scope="session" />
        </c:if>

        <table>
            <tr>
                <td>${requestScope.result}</td>
            </tr>
        </table>
        <%@ include file="footer.jsp" %>
    </body>
</html>
