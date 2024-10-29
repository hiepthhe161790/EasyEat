/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *

 */
public class OrderDetails {
        public int orderDetailID;
    public Orders orders;
    public WeeklyMenus weeklyMenus;
    public int quantity;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailID, Orders orders, WeeklyMenus weeklyMenus, int quantity) {
        this.orderDetailID = orderDetailID;
        this.orders = orders;
        this.weeklyMenus = weeklyMenus;
        this.quantity = quantity;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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
