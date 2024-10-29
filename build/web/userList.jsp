<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User List</title>
        <!-- Link to Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">User List</h1>
            <a class="btn btn-primary mb-4" href="manageUsers?action=new">New User</a>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>User ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Role</th>
                        <th>Active</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${usersList}">
                        <tr>
                            <td>${user.userID}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td>${user.address}</td>
                            <td>${user.role}</td>
                            <td>${user.active}</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-sm btn-primary" href="manageUsers?action=edit&userID=${user.userID}">Edit</a>
                     <a class="btn btn-sm btn-danger" href="manageUsers?action=delete&userID=${user.userID}" onclick="return confirm('Are you sure?')">Delete</a>
                                    <a class="btn btn-sm btn-warning" href="manageUsers?action=toggleActive&userID=${user.userID}&currentStatus=${user.active}">
                                        ${user.active ? 'Deactivate' : 'Activate'}
                                    </a>
                                     </div>
                            </td>
                           
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Optional: jQuery and Popper.js (required for Bootstrap's JavaScript plugins) -->
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
