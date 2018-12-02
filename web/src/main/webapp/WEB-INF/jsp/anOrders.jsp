<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/orders" method="post">
    <table style="background-color: white; border-radius: 3px">
        <tr>
            <td>Номер заказа</td>
            <td>
                <select name='orderNumber'>
                    <c:forEach items="${sessionScope.orderNumbers}" var="orderNumber">
                        <c:if test="${orderNumber != sessionScope.ordersFilter.orderNumber}">
                            <option value="${orderNumber}">${orderNumber}</option>
                        </c:if>
                        <c:if test="${orderNumber == sessionScope.ordersFilter.orderNumber}">
                            <option value="${orderNumber}" selected>${orderNumber}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Дата заказа</td>
            <td>
                <select name='orderDate'>
                    <c:forEach items="${sessionScope.orderDates}" var="orderDate">
                        <c:if test="${orderDate != sessionScope.ordersFilter.orderDate}">
                            <option value="${orderDate}">${orderDate}</option>
                        </c:if>
                        <c:if test="${orderDate == sessionScope.ordersFilter.orderDate}">
                            <option value="${orderDate}" selected>${orderDate}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Максимальная стоимость</td>
            <td>
                <c:if test="${sessionScope.ordersFilter.maxPrice == ''}">
                    <input type="text" name="maxPrice">
                </c:if>
                <c:if test="${sessionScope.ordersFilter.maxPrice != ''}">
                    <input type="text" name="maxPrice" value="${sessionScope.ordersFilter.maxPrice}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Записей на странице</td>
            <td>
                <c:if test="${sessionScope.ordersFilter.pageLimit == ''}">
                    <input type="text" name="pageLimit">
                </c:if>
                <c:if test="${sessionScope.ordersFilter.pageLimit != ''}">
                    <input type="text" name="pageLimit" value="${sessionScope.ordersFilter.pageLimit}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="hidden" name="form" value="filterForm">
                <input type="submit"
                       style="background-color: #32CD32; font-size: 15px; border-bottom-color: #32CD32; color: white; border-radius: 3px"
                       name="form"
                       value="Фильтр">
            </td>
        </tr>
        <tr>
            <td>Выбор страницы</td>
            <td>
                <c:forEach var="page" begin="1" end="${requestScope.numberOfPages}" step="1">
                    <a href="${pageContext.request.contextPath}/orders?page=${page}">${page}</a>
                </c:forEach>
            </td>
        </tr>
    </table>
</form>

<table style="border-radius: 15px; background-color: white; width: 100%">
    <tr>
        <td style="width: 30%; font-size: 25px" align="center">Номер заказа</td>
        <td style="width: 40%; font-size: 25px" align="center">Дата заказа</td>
        <td style="width: 30%; font-size: 25px" align="center">Стоимость</td>
    </tr>
    <c:forEach var="order" items="${requestScope.orders}">
        <form action="${pageContext.request.contextPath}/orders" method="post">
            <tr>
                <td style="width: 30%; font-size: 20px" align="center">
                    <p>${order.number}</p>
                </td>
                <td style="width: 40%; font-size: 20px" align="center">
                    <p>${order.date}</p>
                </td>
                <td style="width: 30%; font-size: 20px" align="center">
                    <p>${order.price} BYN</p>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
