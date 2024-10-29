/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author AnhTH
 */
public class WeeklyMenus {
      public int menuID;
    public String menuName;
    public String description;
    public DietaryRequirements dietaryRequirements;
    public String imageUrl; 
    public  double price;
     private int quantity;
    public WeeklyMenus() {
    }

    public WeeklyMenus(int menuID, String menuName) {
        this.menuID = menuID;
        this.menuName = menuName;
    }

    public WeeklyMenus(int menuID, String menuName, String description, DietaryRequirements dietaryRequirements, String imageUrl, double price) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.description = description;
        this.dietaryRequirements = dietaryRequirements;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DietaryRequirements getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(DietaryRequirements dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public WeeklyMenus(int menuID, String menuName, String description, DietaryRequirements dietaryRequirements, String imageUrl, double price, int quantity) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.description = description;
        this.dietaryRequirements = dietaryRequirements;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

    public WeeklyMenus(int menuID) {
        this.menuID = menuID;
    }

   
}
