<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User Form</title>
        <!-- Link tới Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">${empty users? "New User" : "Edit User"}</h1>
            <form action="manageUsers?action=${empty users? 'insert' : 'update'}" method="post">
                <input type="hidden" name="userID" value="${empty users? '' : users.userID}" />

                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" name="name" id="name" value="${empty users? '' : users.name}" required />
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" name="email" id="email" value="${empty users? '' : users.email}" required />
                </div>

                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" class="form-control" name="phone" id="phone" value="${empty users? '' : users.phone}" required />
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" name="address" id="address" value="${empty users? '' : users.address}" required />
                </div>

                <div class="form-group">
                    <label for="role">Role:</label>
                    <select class="form-control" name="role" id="role" required>
                        <option value="Customer" ${empty users ? '' : users.role == 'Customer' ? 'selected' : ''}>Customer</option>
                        <option value="Admin" ${empty users ? '' : users.role == 'Admin' ? 'selected' : ''}>Admin</option>

                    </select>
                </div>


                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password" id="password" value="${empty users? '' : users.password}" required />
                </div>
                <div class="form-group">
                    <label>Active:</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="active" id="active-yes" value="true" ${empty users || users.active ? 'checked' : ''}>
                        <label class="form-check-label" for="active-yes">Yes</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="active" id="active-no" value="false" ${not empty users && not users.active ? 'checked' : ''}>
                        <label class="form-check-label" for="active-no">No</label>
                    </div>
                </div>

                <input type="submit" class="btn btn-primary" value="${empty users? "Insert" : "Update"}" />
            </form>

            <a href="manageUsers" class="btn btn-secondary mt-3">Cancel</a>
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
