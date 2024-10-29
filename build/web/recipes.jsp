<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recipes</title>
</head>
<body>
    <h1>Recipes</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Recipe ID</th>
                <th>Menu Name</th>
                <th>Recipe Name</th>
                <th>Ingredients</th>
                <th>Instructions</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="recipe" items="${recipes}">
                <tr>
                    <td>${recipe.recipeID}</td>
                    <td>${recipe.weeklyMenus.menuName}</td>
                    <td>${recipe.recipeName}</td>
                    <td>${recipe.ingredients}</td>
                    <td>${recipe.instructions}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/recipes/edit?recipeID=${recipe.recipeID}">Edit</a>
                        <a href="${pageContext.request.contextPath}/recipes/delete?recipeID=${recipe.recipeID}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<!--    <h2>Add New Recipe</h2>
    <form action="${pageContext.request.contextPath}/recipes/create" method="post">
        <label for="menuID">Menu:</label>
        <select id="menuID" name="menuID" required>
             Loop through available menus 
            <c:forEach var="menu" items="${menus}">
                <option value="${menu.menuID}">
                    ${menu.menuName}
                </option>
            </c:forEach>
        </select><br>
        
        <label for="recipeName">Recipe Name:</label>
        <input type="text" id="recipeName" name="recipeName" required><br>
        
        <label for="ingredients">Ingredients:</label>
        <textarea id="ingredients" name="ingredients" required></textarea><br>
        
        <label for="instructions">Instructions:</label>
        <textarea id="instructions" name="instructions" required></textarea><br>
        
        <button type="submit">Add Recipe</button>
    </form>-->
</body>
</html>
