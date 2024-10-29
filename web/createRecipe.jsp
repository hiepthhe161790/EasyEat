<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<!DOCTYPE html>
<html>
<head>
    <title>Create Recipe</title>
</head>
<body>
    <h1>Create Recipe</h1>
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
    </form>
</body>
</html>-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Recipe</title>
</head>
<body>
    <h2>Create Recipe for Weekly Menu ID <%= request.getParameter("menuID") %></h2>
    <form action="manageWeeklyMenu?action=createRecipe" method="post">
        Menu ID: <input type="text" name="menuID" value="<%= request.getParameter("menuID") %>" readonly><br>
        Recipe Name: <input type="text" name="recipeName"><br>
        Ingredients: <textarea name="ingredients"></textarea><br>
        Instructions: <textarea name="instructions"></textarea><br>
        <input type="submit" value="Create Recipe">
    </form>
</body>
</html>