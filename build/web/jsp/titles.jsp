<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Online Bookshop</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>

    <body>
        <%@ include file="header.jsp" %>
        <h1>Items in your Shopping Cart</h1>
        <table>
            <thead>
                <tr>
                    <th>Item</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty sessionScope.cart}">
                        <c:set var="totalCostOfOrder" value="0" scope="page" />
                        <c:forEach var="entry" items="${sessionScope.cart.entrySet()}">
                            <c:set var="item" value="${entry.value}" />
                            <c:set var="cost" value="${item.orderCost}" />
                            <c:set var="totalCostOfOrder" value="${totalCostOfOrder + cost}" />
                            <tr>
                                <td>${item}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                Order Total: $
                                <fmt:formatNumber value="${totalCostOfOrder}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>No Items in Cart</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <hr />
        <h2>Welcome to the Online Book Store</h2>

        <form name="form1" method="post" action="./books">
            <input type="hidden" name="action" value="add_to_cart" />
            <table>
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${sessionScope.Books}">
                        <tr>
                            <td>${book.isbn}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.dollarPrice}</td>
                            <td>
                                <select name="${book.isbn}" size="1">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                </select>
                            </td>
                            <td>
                                <div align="center">
                                    <input type="checkbox" name="add" value="${book.isbn}" />
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6">
                            <input type="submit" name="Details" value="Add to Cart" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>

        <div class="link-container">
            <p><a href="./books?action=view_cart">View Shopping Cart</a></p>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
