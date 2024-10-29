<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <!-- Link tới Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Order List</h1>
        <a class="btn btn-primary mb-4" href="manageOrders?action=new">New Order</a>
          <a class="btn btn-primary mb-4" href="groupedOrders">Split lots by address</a>
        
            <!-- Form tìm kiếm -->
        <form class="mb-4" action="manageOrders" method="get">
            <input type="hidden" name="action" value="search" />
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="orderDate">Order Date:</label>
                    <input type="date" class="form-control" name="orderDate" id="orderDate" />
                </div>
                <div class="form-group col-md-4">
                    <label for="userContact">User Phone/Email:</label>
                    <input type="text" class="form-control" name="userContact" id="userContact" />
                </div>
                <div class="form-group col-md-4 align-self-end">
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </div>
        </form> 
          <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Order ID</th>
                    
                    <th>User name</th>
                    <th>User Email</th>
                    <th>User phone</th>
                    <th>User Address</th>
                    <th>Order Date</th>
                    <th>Delivery Date</th>
                    <th>Status</th>
                    <th>Total Amount</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${ordersList}">
                    <tr>
                        <td>${order.orderID}</td>
                       
                         <td>${order.users.name}</td>
                            <td>${order.users.email}</td>
                              <td>${order.users.phone}</td>
                                <td>${order.users.address}</td>
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
