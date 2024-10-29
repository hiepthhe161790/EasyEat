/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
public class Cart {
    private WeeklyMenus weeklyMenus;
    private int quantity;

    public Cart() {
    }

    public Cart(WeeklyMenus weeklyMenus, int quantity) {
        this.weeklyMenus = weeklyMenus;
        this.quantity = quantity;
    }

    public WeeklyMenus getWeeklyMenus() {
        return weeklyMenus;
    }

    public void setWeeklyMenus(WeeklyMenus weeklyMenus) {
        this.weeklyMenus = weeklyMenus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
}
