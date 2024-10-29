/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *

 */
public class MealPlanDetails {
        public int detailID;
    public PersonalMealPlans personalMealPlans;
    public String dayOfWeek;
    public String mealName;
    public WeeklyMenus weeklyMenus;

    public MealPlanDetails() {
    }

    public MealPlanDetails(PersonalMealPlans personalMealPlans, String dayOfWeek, String mealName, WeeklyMenus weeklyMenus) {
        this.personalMealPlans = personalMealPlans;
        this.dayOfWeek = dayOfWeek;
        this.mealName = mealName;
        this.weeklyMenus = weeklyMenus;
    }

    public MealPlanDetails(int detailID, PersonalMealPlans personalMealPlans, String dayOfWeek, String mealName, WeeklyMenus weeklyMenus) {
        this.detailID = detailID;
        this.personalMealPlans = personalMealPlans;
        this.dayOfWeek = dayOfWeek;
        this.mealName = mealName;
        this.weeklyMenus = weeklyMenus;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public PersonalMealPlans getPersonalMealPlans() {
        return personalMealPlans;
    }

    public void setPersonalMealPlans(PersonalMealPlans personalMealPlans) {
        this.personalMealPlans = personalMealPlans;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public WeeklyMenus getWeeklyMenus() {
        return weeklyMenus;
    }

    public void setWeeklyMenus(WeeklyMenus weeklyMenus) {
        this.weeklyMenus = weeklyMenus;
    }

   
}
