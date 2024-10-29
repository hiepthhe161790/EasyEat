<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Recipe Form</title>
        <!-- Link t?i Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                padding-top: 20px;
                padding-bottom: 20px;
            }
            .container {
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 30px;
                margin-top: 20px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            textarea {
                height: 150px;
            }
            button[type="submit"] {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
            }
            button[type="submit"]:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>${recipe != null ? "Update Recipe" : "Create Recipe"}</h1>
            <form action="recipe" method="post">
                <input type="hidden" name="recipeID" value="${recipe != null ? recipe.recipeID : ''}" />
                <div class="form-group">
                    <input type="hidden" class="form-control" id="menuID" name="menuID" value="${recipe != null ? recipe.weeklyMenus.menuID : param.menuID}" required />
                </div>

                <div class="form-group">
                    <label for="recipeName">Recipe Name:</label>
                    <input type="text" class="form-control" id="recipeName" name="recipeName" value="${recipe != null ? recipe.recipeName : ''}" required />
                </div>
                <div class="form-group">
                    <label for="ingredients">Ingredients:</label>
                    <textarea class="form-control" id="ingredients" name="ingredients" required>${recipe != null ? recipe.ingredients : ''}</textarea>
                </div>
                <div class="form-group">
                    <label for="instructions">Instructions:</label>
                    <textarea class="form-control" id="instructions" name="instructions" required>${recipe != null ? recipe.instructions : ''}</textarea>
                </div>
                <div>
                    <button type="submit" class="btn btn-success">${recipe != null ? "Update" : "Create"}</button>
                    <a href="weeklyMenus" class="btn btn-secondary mt-3">Cancel</a>
                </div>
            </form>
        </div>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
                integrity="sha384-ugrmHQ0GdR6v+YwXYbEBP0wSgq0loRtyRyc2do/J/0EwA6aZg3+vu4nSV6efE7hB"
        crossorigin="anonymous"></script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
    </body>
</html>
