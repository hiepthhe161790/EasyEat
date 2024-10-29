<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 500px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        textarea,
        select,
        button {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
        }

        textarea {
            resize: vertical;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #4CAF50;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            .container {
                padding: 15px;
            }

            button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Menu</h1>
        <form action="${pageContext.request.contextPath}/weeklyMenus/update" method="post">
            <input type="hidden" name="menuID" value="${menu.menuID}">
            
            <label for="menuName">Menu Name:</label>
            <input type="text" id="menuName" name="menuName" value="${menu.menuName}" required>
            
            <label for="description">Description:</label>
            <textarea id="description" name="description" required>${menu.description}</textarea>
            
            <label for="dietaryRequirements">Dietary Requirement:</label>
            <select id="dietaryRequirements" name="dietaryRequirements" required>
                <c:forEach var="diet" items="${dietaryRequirements}">
                    <option value="${diet.dietaryRequirementID}" 
                            <c:if test="${diet.dietaryRequirementID eq menu.dietaryRequirements.dietaryRequirementID}">selected</c:if>>
                        ${diet.name}
                    </option>
                </c:forEach>
            </select>
              <label for="imageUrl">Image URL:</label>
            <textarea id="imageUrl" name="imageUrl" required>${menu.imageUrl}</textarea>
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" value="${menu.price}" step="0.01" required>
            
            <label for="recipes">Recipes Details:</label>
            <textarea id="recipes" name="recipes" disabled>${recipes.instructions}, ${recipes.ingredients}, ${recipes.instructions}</textarea>
            
            <button type="submit">Update Menu</button>
        </form>
        <a href="${pageContext.request.contextPath}/weeklyMenus">Back to Menus</a>
    </div>
</body>
</html>
