package DAO;

import Context.DBContext;
import Models.DietaryRequirements;
import Models.Recipes;
import Models.WeeklyMenus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeeklyMenusDAO extends DBContext {
public List<WeeklyMenus> getAllMenus() {
        List<WeeklyMenus> menus = new ArrayList<>();
        String query = "SELECT wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl,wm.price, dr.DietaryRequirementID, dr.Name AS DietaryName, dr.Description AS DietaryDescription "
                     + "FROM WeeklyMenus wm "
                     + "JOIN DietaryRequirements dr ON wm.DietaryRequirementID = dr.DietaryRequirementID";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int menuID = rs.getInt("MenuID");
                String menuName = rs.getString("MenuName");
                String description = rs.getString("Description");
                int dietaryRequirementID = rs.getInt("DietaryRequirementID");
                String imageUrl = rs.getString("imageUrl");
                double price = rs.getDouble("price");
                String dietaryName = rs.getString("DietaryName");
                String dietaryDescription = rs.getString("DietaryDescription");

                DietaryRequirements dietaryRequirement = new DietaryRequirements(dietaryRequirementID, dietaryName, dietaryDescription);
                WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, dietaryRequirement,imageUrl,price);
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return menus;
    }
   public List<WeeklyMenus> getAllMenuses(int page, int pageSize) {
    List<WeeklyMenus> menus = new ArrayList<>();
    String query = "SELECT wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl,wm.price, dr.DietaryRequirementID, dr.Name AS DietaryName, dr.Description AS DietaryDescription "
                 + "FROM WeeklyMenus wm "
                 + "JOIN DietaryRequirements dr ON wm.DietaryRequirementID = dr.DietaryRequirementID "
                 + "ORDER BY wm.MenuID "
                 + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, (page - 1) * pageSize);
        ps.setInt(2, pageSize);
        rs = ps.executeQuery();
        while (rs.next()) {
            int menuID = rs.getInt("MenuID");
            String menuName = rs.getString("MenuName");
            String description = rs.getString("Description");
            int dietaryRequirementID = rs.getInt("DietaryRequirementID");
            String imageUrl = rs.getString("imageUrl");
            double price = rs.getDouble("price");
            String dietaryName = rs.getString("DietaryName");
            String dietaryDescription = rs.getString("DietaryDescription");

            DietaryRequirements dietaryRequirement = new DietaryRequirements(dietaryRequirementID, dietaryName, dietaryDescription);
            WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, dietaryRequirement, imageUrl,price);
            menus.add(menu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return menus;
}

public int getTotalMenusCount() {
    int total = 0;
    String query = "SELECT COUNT(*) AS total FROM WeeklyMenus";
    try {
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

public List<WeeklyMenus> getMenusByCategory(int dietaryRequirementID, int page, int pageSize) {
    List<WeeklyMenus> menus = new ArrayList<>();
    String query = "SELECT wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price, dr.DietaryRequirementID, dr.Name AS DietaryName, dr.Description AS DietaryDescription "
                 + "FROM WeeklyMenus wm "
                 + "JOIN DietaryRequirements dr ON wm.DietaryRequirementID = dr.DietaryRequirementID "
                 + "WHERE dr.DietaryRequirementID = ? "
                 + "ORDER BY wm.MenuID "
                 + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, dietaryRequirementID);
        ps.setInt(2, (page - 1) * pageSize);
        ps.setInt(3, pageSize);
        rs = ps.executeQuery();
        while (rs.next()) {
            int menuID = rs.getInt("MenuID");
            String menuName = rs.getString("MenuName");
            String description = rs.getString("Description");
            String imageUrl = rs.getString("imageUrl");
            double price = rs.getDouble("price");
            String dietaryName = rs.getString("DietaryName");
            String dietaryDescription = rs.getString("DietaryDescription");

            DietaryRequirements dietaryRequirement = new DietaryRequirements(dietaryRequirementID, dietaryName, dietaryDescription);
            WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, dietaryRequirement, imageUrl, price);
            menus.add(menu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return menus;
}

public int getMenusCountByCategory(int dietaryRequirementID) {
    int total = 0;
    String query = "SELECT COUNT(*) AS total FROM WeeklyMenus WHERE DietaryRequirementID = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, dietaryRequirementID);
        rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

 public void addMenu(WeeklyMenus menu) {
    String query = "INSERT INTO WeeklyMenus (MenuName, Description, DietaryRequirementID, imageUrl, price) VALUES (?, ?, ?, ?, ?)";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, menu.getMenuName());
        ps.setString(2, menu.getDescription());
        ps.setInt(3, menu.getDietaryRequirements().getDietaryRequirementID());
        ps.setString(4, menu.getImageUrl());
        ps.setDouble(5, menu.getPrice()); // Set giá trị cho tham số thứ 5 (price)
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void updateMenu(WeeklyMenus menu) {
        String query = "UPDATE WeeklyMenus SET MenuName = ?, Description = ?, DietaryRequirementID = ?, imageUrl=?,price=? WHERE MenuID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, menu.getMenuName());
            ps.setString(2, menu.getDescription());
            ps.setInt(3, menu.getDietaryRequirements().getDietaryRequirementID());
              ps.setString(4, menu.getImageUrl());
              ps.setDouble(5, menu.getPrice());
            ps.setInt(6, menu.getMenuID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(int menuID) {
        String query = "DELETE FROM WeeklyMenus WHERE MenuID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menuID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public WeeklyMenus getMenuByID(int menuID) {
        WeeklyMenus menu = null;
        String query = "SELECT wm.MenuID, wm.MenuName, wm.Description,wm.imageUrl,wm.price, dr.DietaryRequirementID, dr.Name AS DietaryName, dr.Description AS DietaryDescription "
                     + "FROM WeeklyMenus wm "
                     + "JOIN DietaryRequirements dr ON wm.DietaryRequirementID = dr.DietaryRequirementID "
                     + "WHERE MenuID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menuID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String menuName = rs.getString("MenuName");
                String description = rs.getString("Description");
                int dietaryRequirementID = rs.getInt("DietaryRequirementID");
                String imageUrl = rs.getString("imageUrl");
                double price = rs.getDouble("price");
                String dietaryName = rs.getString("DietaryName");
                String dietaryDescription = rs.getString("DietaryDescription");

                DietaryRequirements dietaryRequirement = new DietaryRequirements(dietaryRequirementID, dietaryName, dietaryDescription);
                menu = new WeeklyMenus(menuID, menuName, description, dietaryRequirement,imageUrl,price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return menu;
    }
   public Recipes getRecipesByMenuID(int menuID) {
    Recipes recipe = null;
    String query = "SELECT r.RecipeID, r.RecipeName, r.Ingredients, r.Instructions " +
                   "FROM Recipes r " +
                   "WHERE r.MenuID = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, menuID);
        rs = ps.executeQuery();
        if (rs.next()) {
            int recipeID = rs.getInt("RecipeID");
            String recipeName = rs.getString("RecipeName");
            String ingredients = rs.getString("Ingredients");
            String instructions = rs.getString("Instructions");

            recipe = new Recipes(recipeID, null, recipeName, ingredients, instructions); // Tham chiếu WeeklyMenus sẽ được cập nhật sau khi thêm phương thức.
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return recipe;
}
public List<WeeklyMenus> getLatestMenus(int count) {
    List<WeeklyMenus> menus = new ArrayList<>();
    String query = "SELECT TOP (?) wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price " +
                   "FROM WeeklyMenus wm " +
                   "ORDER BY wm.MenuID DESC";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, count);
        rs = ps.executeQuery();
        while (rs.next()) {
            int menuID = rs.getInt("MenuID");
            String menuName = rs.getString("MenuName");
            String description = rs.getString("Description");
            String imageUrl = rs.getString("imageUrl");
            double price = rs.getDouble("price");

            WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, null, imageUrl, price);
            menus.add(menu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return menus;
}
public List<WeeklyMenus> getRelatedMenusByMenuID(int menuID) {
    List<WeeklyMenus> relatedMenus = new ArrayList<>();
    String query = "SELECT TOP 3 wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price, dr.DietaryRequirementID, dr.Name AS DietaryName, dr.Description AS DietaryDescription " +
                   "FROM WeeklyMenus wm " +
                   "JOIN DietaryRequirements dr ON wm.DietaryRequirementID = dr.DietaryRequirementID " +
                   "WHERE wm.DietaryRequirementID = (SELECT DietaryRequirementID FROM WeeklyMenus WHERE MenuID = ?) " +
                   "AND wm.MenuID != ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, menuID);
        ps.setInt(2, menuID);
        rs = ps.executeQuery();
        while (rs.next()) {
            int relatedMenuID = rs.getInt("MenuID");
            String relatedMenuName = rs.getString("MenuName");
            String relatedDescription = rs.getString("Description");
            int relatedDietaryRequirementID = rs.getInt("DietaryRequirementID");
            String relatedImageUrl = rs.getString("imageUrl");
            double relatedPrice = rs.getDouble("price");
            String relatedDietaryName = rs.getString("DietaryName");
            String relatedDietaryDescription = rs.getString("DietaryDescription");

            DietaryRequirements relatedDietaryRequirement = new DietaryRequirements(relatedDietaryRequirementID, relatedDietaryName, relatedDietaryDescription);
            WeeklyMenus relatedMenu = new WeeklyMenus(relatedMenuID, relatedMenuName, relatedDescription, relatedDietaryRequirement, relatedImageUrl, relatedPrice);
            relatedMenus.add(relatedMenu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return relatedMenus;
}

public List<WeeklyMenus> searchMenusByName(String keyword) {
    List<WeeklyMenus> menus = new ArrayList<>();
    String query = "SELECT wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price, " +
                   "dr.DietaryRequirementID, dr.Name AS DietaryName, dr.Description AS DietaryDescription " +
                   "FROM WeeklyMenus wm " +
                   "JOIN DietaryRequirements dr ON wm.DietaryRequirementID = dr.DietaryRequirementID " +
                   "WHERE wm.MenuName LIKE ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + keyword + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            int menuID = rs.getInt("MenuID");
            String menuName = rs.getString("MenuName");
            String description = rs.getString("Description");
            int dietaryRequirementID = rs.getInt("DietaryRequirementID");
            String imageUrl = rs.getString("imageUrl");
            double price = rs.getDouble("price");
            String dietaryName = rs.getString("DietaryName");
            String dietaryDescription = rs.getString("DietaryDescription");

            DietaryRequirements dietaryRequirement = new DietaryRequirements(dietaryRequirementID, dietaryName, dietaryDescription);
            WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, dietaryRequirement, imageUrl, price);
            menus.add(menu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return menus;
}

      public static void main(String[] args) {
        WeeklyMenusDAO weeklyMenusDAO = new WeeklyMenusDAO();
        
     WeeklyMenus menus = weeklyMenusDAO.getMenuByID(3);
          System.out.println(menus.getImageUrl());

    }
}
