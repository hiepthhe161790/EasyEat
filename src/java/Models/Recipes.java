/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *

 */
public class Recipes {
  public int recipeID;
    public WeeklyMenus weeklyMenus;
    public String recipeName;
    public String ingredients;
    public String instructions;

    public Recipes() {
    }

    public Recipes(int recipeID, WeeklyMenus weeklyMenus, String recipeName, String ingredients, String instructions) {
        this.recipeID = recipeID;
        this.weeklyMenus = weeklyMenus;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public WeeklyMenus getWeeklyMenus() {
        return weeklyMenus;
    }

    public void setWeeklyMenus(WeeklyMenus weeklyMenus) {
        this.weeklyMenus = weeklyMenus;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    
    
}
