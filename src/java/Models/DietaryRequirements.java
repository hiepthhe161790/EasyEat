/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *

 */
public class DietaryRequirements {
    private int dietaryRequirementID;
    private String name;
    private String description;

    public DietaryRequirements() {
    }

    public DietaryRequirements(int dietaryRequirementID, String name, String description) {
        this.dietaryRequirementID = dietaryRequirementID;
        this.name = name;
        this.description = description;
    }

    public int getDietaryRequirementID() {
        return dietaryRequirementID;
    }

    public void setDietaryRequirementID(int dietaryRequirementID) {
        this.dietaryRequirementID = dietaryRequirementID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
