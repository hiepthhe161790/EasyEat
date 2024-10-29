<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
    <!-- Link tới Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Màu nền sáng */
            padding-top: 20px;
            padding-bottom: 20px;
        }
        .container {
            background-color: #ffffff; /* Nền container trắng */
            border-radius: 8px; /* Bo tròn viền container */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
            padding: 30px; /* Khoảng cách nội dung trong container */
            margin-top: 20px; /* Khoảng cách với phần trên cùng */
        }
        .table-responsive {
            margin-top: 20px; /* Khoảng cách với bảng khi có nội dung dài */
        }
        .btn-continue-shopping {
            margin-top: 20px; /* Khoảng cách với nút Continue Shopping */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Order History</h1>
        
        <c:if test="${empty orders}">
            <p class="alert alert-info">No orders found.</p>
        </c:if>
        
        <c:if test="${not empty orders}">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Delivery Date</th>
                            <th>Status</th>
                            <th>Total Amount</th>
                              <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.orderID}</td>
                                <td>${order.orderDate}</td>
                                <td>${order.deliveryDate}</td>
                                <td>${order.status}</td>
                                <td>${order.totalAmount}</td>
                                  <td>
                                    <a href="orderDetails?orderId=${order.orderID}" class="btn btn-info btn-sm">View Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="shop" class="btn btn-primary btn-continue-shopping">Continue Shopping</a>
        </c:if>
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
