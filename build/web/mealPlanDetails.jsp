<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meal Plan Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Meal Plan Details</h1>
        
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Detail ID</th>
                    <th scope="col">Day of Week</th>
                    <th scope="col">Meal Name</th>
                    <th scope="col">Menu Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detail" items="${mealPlanDetails}">
                    <tr>
                        <td>${detail.detailID}</td>
                        <td>${detail.dayOfWeek}</td>
                        <td>${detail.mealName}</td>
                        <td>${detail.weeklyMenus.menuName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <a class="btn btn-secondary" href="personalMealPlans">Back to Meal Plans</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
