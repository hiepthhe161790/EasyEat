<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Orders Grouped by Address</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .address-header {
            background-color: #f9f9f9;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Orders Grouped by Address</h1>
    <c:forEach var="order" items="${orders}">
        <c:choose>
            <c:when test="${empty previousAddress or previousAddress != order.users.address}">
                <c:set var="previousAddress" value="${order.users.address}" />
                <table>
                    <thead>
                        <tr>
                            <th colspan="6" class="address-header">Address: ${order.users.address}</th>
                        </tr>
                        <tr>
                            <th>Order ID</th>
                            <th>User ID</th>
                            <th>Order Date</th>
                            <th>Delivery Date</th>
                            <th>Status</th>
                            <th>Total Amount</th>
                        </tr>
                    </thead>
                    <tbody>
            </c:when>
        </c:choose>
        <tr>
            <td>${order.orderID}</td>
            <td>${order.users.userID}</td>
            <td>${order.orderDate}</td>
            <td>${order.deliveryDate}</td>
            <td>${order.status}</td>
            <td>${order.totalAmount}</td>
        </tr>
        <c:choose>
            <c:when test="${not empty ordersList and order eq ordersList[ordersList.size() - 1] or previousAddress != ordersList[ordersList.indexOf(order) + 1].users.address}">
                </tbody>
                </table>
            </c:when>
        </c:choose>
    </c:forEach>
</body>
</html>
