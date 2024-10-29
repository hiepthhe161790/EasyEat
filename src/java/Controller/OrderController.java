/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.OrdersDAO;
import Models.Orders;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author 
 */
@WebServlet(name="OrderController", urlPatterns={"/order"})
public class OrderController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to order form JSP
        request.getRequestDispatcher("orderForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        Date deliveryDate = Date.valueOf(request.getParameter("deliveryDate"));
        String status = request.getParameter("status");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        Users user = new Users(userID); // Assuming Users class has a constructor that accepts userID
        Orders order = new Orders(0, user, orderDate, deliveryDate, status, totalAmount);

        OrdersDAO dao = new OrdersDAO();
        dao.addOrder(order);

        response.sendRedirect("orderHistory");
    }
}
