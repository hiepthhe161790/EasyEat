<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Recipe</title>
</head>
<body>
    <h1>Edit Recipe</h1>
    <form action="${pageContext.request.contextPath}/recipes/update" method="post">
        <input type="hidden" name="recipeID" value="${recipe.recipeID}">
        
        <label for="menuID">Menu:</label>
        <select id="menuID" name="menuID" required>
            <!-- Loop through available menus -->
            <c:forEach var="menu" items="${menus}">
                <option value="${menu.menuID}"
                        <c:if test="${menu.menuID eq recipe.weeklyMenus.menuID}">selected</c:if>>
                    ${menu.menuName}
                </option>
            </c:forEach>
        </select><br>
        
        <label for="recipeName">Recipe Name:</label>
        <input type="text" id="recipeName" name="recipeName" value="${recipe.recipeName}" required><br>
        
        <label for="ingredients">Ingredients:</label>
        <textarea id="ingredients" name="ingredients" required>${recipe.ingredients}</textarea><br>
        
        <label for="instructions">Instructions:</label>
        <textarea id="instructions" name="instructions" required>${recipe.instructions}</textarea><br>
        
        <button type="submit">Update Recipe</button>
    </form>
</body>
</html>
