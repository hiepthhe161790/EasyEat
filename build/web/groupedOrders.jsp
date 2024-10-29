<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grouped Orders</title>
    <!-- Link tới Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Grouped Orders by Address   <a class="btn btn-primary mb-4" href="manageOrders">Back</a></h1>
       
        <c:forEach var="entry" items="${groupedOrders}">
            <div class="mb-4">
                <h2>Address: ${entry.key}</h2>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="thead-light">
                            <tr>
                                <th>Order ID</th>
                                <th>User ID</th>
                                <th>Order Date</th>
                                <th>Delivery Date</th>
                                <th>Status</th>
                                <th>Total Amount</th>
                                 <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${entry.value}">
                                <tr>
                                    <td>${order.orderID}</td>
                                    <td>${order.users.userID}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.deliveryDate}</td>
                                    <td>${order.status}</td>
                                    <td>${order.totalAmount}</td>
                                     <td>
                            <a class="btn btn-sm btn-primary" href="manageOrders?action=edit&orderID=${order.orderID}">Edit</a>
                            <a class="btn btn-sm btn-danger" href="manageOrders?action=delete&orderID=${order.orderID}" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Optional: jQuery và Popper.js (được yêu cầu cho các tính năng JavaScript của Bootstrap) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
            integrity="sha384-ugrmHQ0GdR6v+YwXYbEBP0wSgq0loRtyRyc2do/J/0EwA6aZg3+vu4nSV6efE7hB"
            crossorigin="anonymous"></script>
    <!-- Optional: Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
</body>
</html>
