<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Meal Plans</title>
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
        <h1 class="mt-4 mb-4">Personal Meal Plans</h1>
        <c:choose>
        <c:when test="${empty mealPlans}">
            <div class="alert alert-info">
                Bạn có thể thêm bất kỳ bữa ăn nào của hệ thống vào kế hoạch bữa ăn cá nhân. Kế hoạch bữa ăn cá nhân nên bao gồm một số bữa ăn từ thứ Hai đến thứ Bảy/tuần. Bạn có thể lên kế hoạch cho bữa ăn cá nhân của mình từ 1 đến 4 tuần.
            </div>
        </c:when>
        <c:otherwise>
        <!-- Table -->
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Meal Plan ID</th>
                    <th scope="col">User ID</th>
                    <th scope="col">Start Date</th>
                    <th scope="col">End Date</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mealPlan" items="${mealPlans}">
                    <tr>
                        <td>${mealPlan.mealPlanID}</td>
                        <td>${mealPlan.users.userID}</td>
                        <td>${mealPlan.startDate}</td>
                        <td>${mealPlan.endDate}</td>
                        <td>
                            <a class="btn btn-primary btn-sm mr-1" href="personalMealPlans?action=edit&mealPlanID=${mealPlan.mealPlanID}">Edit</a>
                            <a class="btn btn-danger btn-sm" href="personalMealPlans?action=delete&mealPlanID=${mealPlan.mealPlanID}">Delete</a>
                          <a class="btn btn-info btn-sm" href="mealPlanDetails?mealPlanID=${mealPlan.mealPlanID}">Show Details</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</c:otherwise>
    </c:choose>
        <!-- Create New Meal Plan Button -->
           <a class="btn btn-secondary" href="shop">Back Home</a>
        <a class="btn btn-success" href="createMealPlan.jsp">Create New Meal Plan</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
