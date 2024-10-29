<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <!-- Link Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <style>
        /* Tùy chỉnh CSS cho bố cục bảng */
        .table-container {
            width: 80%;
            margin: auto;
            margin-top: 20px;
            background-color: #ffffff; /* Nền trắng cho bảng */
            border-radius: 8px; /* Bo tròn viền */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
            padding: 20px; /* Khoảng cách nội dung trong bảng */
        }
        h2 {
            margin-top: 30px; /* Khoảng cách tiêu đề với container */
            text-align: center; /* Căn giữa tiêu đề */
        }
        th {
            text-align: center; /* Căn giữa các cột tiêu đề */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order Details</h2>
        <div class="table-container">
            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th>Menu Name</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orderDetailsList}" var="orderDetail">
                        <tr>
                            <td>${orderDetail.weeklyMenus.menuName}</td>
                            <td>${orderDetail.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Link Bootstrap JS và các thư viện khác của Bootstrap (nếu cần) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
</body>
</html>
