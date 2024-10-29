<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Place Order</title>
</head>
<body>
    <h1>Place Order</h1>
    <form action="order" method="post">
        <input type="hidden" name="userID" value="<!-- Set userID here, possibly from session -->">
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" required><br>
        <label for="deliveryDate">Delivery Date:</label>
        <input type="date" id="deliveryDate" name="deliveryDate" required><br>
        <label for="status">Status:</label>
        <input type="text" id="status" name="status" required><br>
        <label for="totalAmount">Total Amount:</label>
        <input type="number" step="0.01" id="totalAmount" name="totalAmount" required><br>
        <button type="submit">Place Order</button>
    </form>
</body>
</html>
