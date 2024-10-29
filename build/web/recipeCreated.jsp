<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipe Created</title>
</head>
<body>
    <h2>Recipe Created Successfully</h2>
    <h3>Recipe Information:</h3>
    <p>Recipe ID: <%= request.getAttribute("recipe").getRecipeID() %></p>
    <p>Recipe Name: <%= request.getAttribute("recipe").getRecipeName() %></p>
    <p>Ingredients: <%= request.getAttribute("recipe").getIngredients() %></p>
    <p>Instructions: <%= request.getAttribute("recipe").getInstructions() %></p>
</body>
</html>
