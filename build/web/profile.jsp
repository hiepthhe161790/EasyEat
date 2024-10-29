<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
</head>
<style>
    /* General Styles */
body {
  font-family: 'Helvetica Neue', Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f5f5f5;
}

/* Header Styles */
h1 {
  text-align: center;
  color: #333;
  margin-top: 40px;
  margin-bottom: 20px;
}

/* Message Styles */
.message {
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  font-weight: bold;
}

.message.success {
  background-color: #d4edda;
  color: #155724;
}

.message.error {
  background-color: #f8d7da;
  color: #721c24;
}

/* Form Styles */
form {
  max-width: 500px;
  margin: 0 auto;
  background-color: #fff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

form input[type="text"],
form input[type="password"] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

form input[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

form input[type="submit"]:hover {
  background-color: #45a049;
}

/* Change Password Section */
h2 {
  text-align: center;
  color: #333;
  margin-top: 40px;
  margin-bottom: 20px;
</style>
<body>
    <h1>Profile</h1>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="profile" method="post">
        <input type="hidden" name="action" value="updateProfile">
        <p>Name: <input type="text" name="name" value="${user.name}"></p>
        <p>Phone: <input type="text" name="phone" value="${user.phone}"></p>
        <p>Address: <input type="text" name="address" value="${user.address}"></p>
        <p><input type="submit" value="Update Profile"></p>
    </form>

    <h2>Change Password</h2>
    <form action="profile" method="post">
        <input type="hidden" name="action" value="changePassword">
        <p>Current Password: <input type="password" name="currentPassword"></p>
        <p>New Password: <input type="password" name="newPassword"></p>
        <p><input type="submit" value="Change Password"></p>
    </form>
</body>
</html>
