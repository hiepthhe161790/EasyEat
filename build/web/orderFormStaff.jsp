<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order Form</title>
        <!-- Link tới Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">${order == null ? "New Order" : "Edit Order"}</h1>
            <form action="manageOrders" method="post">
                <input type="hidden" name="action" value="${order == null ? "insert" : "update"}" />
                <input type="hidden" name="orderID" value="${order.orderID}" />

                <div class="form-group">
                    <label for="userID">User:</label>
                    <select class="form-control" name="userID" id="userID" required>
                        <option value="">Select a user</option>
                        <c:forEach var="user" items="${usersList}">
                            <option value="${user.userID}" ${order != null && order.users.userID == user.userID ? 'selected' : ''}>
                                ${user.name} (${user.email})
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="orderDate">Order Date:</label>
                    <input type="date" class="form-control" name="orderDate" id="orderDate" value="${order == null ? '' : order.orderDate}" required />
                </div>

                <div class="form-group">
                    <label for="deliveryDate">Delivery Date:</label>
                    <input type="date" class="form-control" name="deliveryDate" id="deliveryDate" value="${order == null ? '' : order.deliveryDate}" required />
                </div>

                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" name="status" id="status" required>

                        <c:if test="${order != null && order.status == 'Pending'}">
                            <option value="Pending" selected>Pending</option>
                            <option value="Processing">Processing</option>
                        </c:if>

                        <c:if test="${order != null && order.status == 'Processing'}">
                            <option value="Processing" selected>Processing</option>
                            <option value="Completed">Completed</option>
                            <option value="Cancelled">Cancelled</option>
                        </c:if>

                        <c:if test="${order != null && order.status == 'Completed'}">
                            <option value="Completed" selected>Completed</option>
                        </c:if>

                        <c:if test="${order != null && order.status == 'Cancelled'}">
                            <option value="Cancelled" selected>Cancelled</option>
                        </c:if>

                        <c:if test="${order == null}">
                            <option value="Pending">Pending</option>
                            <option value="Processing">Processing</option>
                        </c:if>
                        <c:if test="${order != null && order.status != 'Pending' && order.status != 'Processing' && order.status != 'Completed' && order.status != 'Cancelled'}">
                            <option value="Pending">Pending</option>
                            <option value="Processing">Processing</option>
                            <option value="Completed">Completed</option>
                            <option value="Cancelled">Cancelled</option>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label for="totalAmount">Total Amount:</label>
                    <input type="text" class="form-control" name="totalAmount" id="totalAmount" value="${order == null ? '' : order.totalAmount}" required />
                </div>

                <input type="submit" class="btn btn-primary" value="${order == null ? "Insert" : "Update"}" />
            </form>

            <a href="manageOrders" class="btn btn-secondary mt-3">Back to Order List</a>
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


<!--    <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" name="status" id="status" required>
                        <option value="Pending" 
<c:if test="${order != null && order.status == 'Pending'}">selected</c:if>
<c:if test="${order != null && (order.status == 'Processing' || order.status == 'Completed' || order.status == 'Cancelled')}">style="display:none;"</c:if>
>Pending</option>
<option value="Processing" 
<c:if test="${order != null && order.status == 'Processing'}">selected</c:if>
<c:if test="${order != null && (order.status == 'Completed' || order.status == 'Cancelled')}">style="display:none;"</c:if>
>Processing</option>
<option value="Completed" 
<c:if test="${order != null && order.status == 'Completed'}">selected</c:if>
>Completed</option>
<option value="Cancelled" 
<c:if test="${order != null && order.status == 'Cancelled'}">selected</c:if>
>Cancelled</option>
</select>
</div>-->
