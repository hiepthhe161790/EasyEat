<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Weekly Menus</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }

        table {
            width: 90%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        td img {
            display: block;
            margin: 0 auto;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .pagination {
            margin: 20px 0;
            text-align: center;
        }

        .pagination a {
            color: #4CAF50;
            margin: 0 5px;
            text-decoration: none;
        }

        .pagination strong {
            margin: 0 5px;
        }

        .add-menu {
            margin: 20px 0;
            text-align: center;
        }

        .add-menu a {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .add-menu a:hover {
            background-color: #45a049;
        }

        @media (max-width: 600px) {
            table {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <h1>Weekly Menus</h1>
   <div style="display: inline-block;">
    <a href="${pageContext.request.contextPath}/Hompage" style="
        display: inline-block;
        padding: 10px 20px;
        font-size: 16px;
        font-weight: bold;
        color: white;
        background-color: #007bff;
        border: none;
        border-radius: 4px;
        text-align: center;
        text-decoration: none;
        transition: background-color 0.3s ease;
    ">Home</a>
</div>


    <div class="add-menu">
        <a href="${pageContext.request.contextPath}/weeklyMenus/createForm">Add new Menu</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>Menu ID</th>
                <th>Menu Image</th>
                <th>Menu Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Dietary Requirement</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="menu" items="${menus}">
                <tr>
                    <td>${menu.menuID}</td>
                    <td><img src="${menu.imageUrl}" alt="Menu Image" width="100" height="100"></td>
                    <td>${menu.menuName}</td>
                    <td>${menu.description}</td>
                    <td>${menu.price}</td>
                    <td>${menu.dietaryRequirements.name}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/weeklyMenus/edit?menuID=${menu.menuID}">Edit</a>|
                        <a href="${pageContext.request.contextPath}/weeklyMenus/delete?menuID=${menu.menuID}">Delete</a>|
                          <a href="${pageContext.request.contextPath}/recipe?menuID=${menu.menuID}">View/Add Recipes</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/weeklyMenus?page=${currentPage - 1}">Previous</a>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/weeklyMenus?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/weeklyMenus?page=${currentPage + 1}">Next</a>
        </c:if>
    </div>
</body>
</html>
