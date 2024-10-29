/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *

 */
public class PersonalMealPlans {
       public int mealPlanID;
    public Users users;
    public Date startDate;
    public Date endDate; 

    public PersonalMealPlans() {
    }

    public PersonalMealPlans(int mealPlanID, Users users, Date startDate, Date endDate) {
        this.mealPlanID = mealPlanID;
        this.users = users;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getMealPlanID() {
        return mealPlanID;
    }

    public void setMealPlanID(int mealPlanID) {
        this.mealPlanID = mealPlanID;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    
    
}
