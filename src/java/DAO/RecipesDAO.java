package DAO;

import Context.DBContext;
import Models.Recipes;
import Models.WeeklyMenus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipesDAO extends DBContext {

    public List<Recipes> getAllRecipes() {
        List<Recipes> recipes = new ArrayList<>();
        String query = "SELECT r.RecipeID, r.MenuID, r.RecipeName, r.Ingredients, r.Instructions, " +
                       "m.MenuName, m.Description,m.imageUrl,m.price " +
                       "FROM Recipes r " +
                       "INNER JOIN WeeklyMenus m ON r.MenuID = m.MenuID";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int recipeID = rs.getInt("RecipeID");
                int menuID = rs.getInt("MenuID");
                String recipeName = rs.getString("RecipeName");
                String ingredients = rs.getString("Ingredients");
                String instructions = rs.getString("Instructions");
                String menuName = rs.getString("MenuName");
                String description = rs.getString("Description");
                String imageUrl = rs.getString("imageUrl");
                double price = rs.getDouble("price");
                // Create WeeklyMenus object
                WeeklyMenus weeklyMenus = new WeeklyMenus(menuID, menuName, description, null,imageUrl,price);

                // Create Recipes object
                Recipes recipe = new Recipes(recipeID, weeklyMenus, recipeName, ingredients, instructions);

                recipes.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception properly (log it, throw custom exception, etc.)
        }

        return recipes;
    }

    public void addRecipe(Recipes recipe) {
        String query = "INSERT INTO Recipes (MenuID, RecipeName, Ingredients, Instructions) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, recipe.getWeeklyMenus().getMenuID());
            ps.setString(2, recipe.getRecipeName());
            ps.setString(3, recipe.getIngredients());
            ps.setString(4, recipe.getInstructions());
            ps.executeUpdate();

            // Retrieve the auto-generated RecipeID
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                recipe.setRecipeID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception properly (log it, throw custom exception, etc.)
        }
    }

    public void updateRecipe(Recipes recipe) {
        String query = "UPDATE Recipes SET MenuID = ?, RecipeName = ?, Ingredients = ?, Instructions = ? WHERE RecipeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, recipe.getWeeklyMenus().getMenuID());
            ps.setString(2, recipe.getRecipeName());
            ps.setString(3, recipe.getIngredients());
            ps.setString(4, recipe.getInstructions());
            ps.setInt(5, recipe.getRecipeID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception properly (log it, throw custom exception, etc.)
        }
    }

    public void deleteRecipe(int recipeID) {
        String query = "DELETE FROM Recipes WHERE RecipeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, recipeID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception properly (log it, throw custom exception, etc.)
        }
    }

    public Recipes getRecipeByID(int recipeID) {
    Recipes recipe = null;
    String query = "SELECT r.RecipeID, r.MenuID, r.RecipeName, r.Ingredients, r.Instructions, " +
                   "m.MenuName, m.Description,m.imageUrl,m.price " +
                   "FROM Recipes r " +
                   "INNER JOIN WeeklyMenus m ON r.MenuID = m.MenuID " +
                   "WHERE r.RecipeID = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, recipeID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int menuID = rs.getInt("MenuID");
                String recipeName = rs.getString("RecipeName");
                String ingredients = rs.getString("Ingredients");
                String instructions = rs.getString("Instructions");
                String menuName = rs.getString("MenuName");
                String description = rs.getString("Description");
               String imageUrl = rs.getString("imageUrl");
               double price = rs.getDouble("price");
                // Create WeeklyMenus object
                WeeklyMenus weeklyMenus = new WeeklyMenus(menuID, menuName, description, null,imageUrl,price);

                // Create Recipes object
                recipe = new Recipes(recipeID, weeklyMenus, recipeName, ingredients, instructions);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception properly (log it, throw custom exception, etc.)
    }
    return recipe;
}

  public Recipes getRecipeByMenuID(int menuID) {
    Recipes recipe = null;
    String query = "SELECT r.RecipeID, r.MenuID, r.RecipeName, r.Ingredients, r.Instructions, " +
                   "m.MenuName, m.Description, m.imageUrl, m.price " +
                   "FROM Recipes r " +
                   "INNER JOIN WeeklyMenus m ON r.MenuID = m.MenuID " +
                   "WHERE r.MenuID = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, menuID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int recipeID = rs.getInt("RecipeID");
                String recipeName = rs.getString("RecipeName");
                String ingredients = rs.getString("Ingredients");
                String instructions = rs.getString("Instructions");
                String menuName = rs.getString("MenuName");
                String description = rs.getString("Description");
                String imageUrl = rs.getString("imageUrl");
                double price = rs.getDouble("price");

                // Create WeeklyMenus object
                WeeklyMenus weeklyMenus = new WeeklyMenus(menuID, menuName, description, null, imageUrl, price);

                // Create Recipes object
                recipe = new Recipes(recipeID, weeklyMenus, recipeName, ingredients, instructions);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception properly (log it, throw custom exception, etc.)
    }

    return recipe;
}

    public static void main(String[] args) {
        RecipesDAO recipesDAO = new RecipesDAO();
        List<Recipes> list = recipesDAO.getAllRecipes();
        for (Recipes recipe : list) {
//            System.out.println(recipe);
        }
        Recipes recipes = recipesDAO.getRecipeByMenuID(3);
        System.out.println(recipes.getRecipeName());
    }
}
