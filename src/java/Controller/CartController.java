/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.WeeklyMenusDAO;
import Models.WeeklyMenus;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
@WebServlet(name="CartController", urlPatterns={"/cart", "/removeFromCart", "/updateCart"})
public class CartController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    HttpSession session = request.getSession();
    List<WeeklyMenus> cart = (List<WeeklyMenus>) session.getAttribute("cart");

    if (cart == null) {
        cart = new ArrayList<>();
    }

    request.setAttribute("cart", cart);
     calculateTotals(request, session);
    request.getRequestDispatcher("/cart.jsp").forward(request, response);
} 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getServletPath();

        switch (action) {
            case "/cart":
                addToCart(request, session);
                 response.sendRedirect("shop");
                break;
            case "/updateCart":
                updateCart(request, session);
                 response.sendRedirect("cart");
                break;
            case "/removeFromCart":
                removeFromCart(request, session);
                 response.sendRedirect("cart");
                break;
        }

       
    }

  private void addToCart(HttpServletRequest request, HttpSession session) {
    int menuID = Integer.parseInt(request.getParameter("menuID"));
    List<WeeklyMenus> cart = (List<WeeklyMenus>) session.getAttribute("cart");

    if (cart == null) {
        cart = new ArrayList<>();
    }

    WeeklyMenusDAO weeklyMenusDAO = new WeeklyMenusDAO();
    WeeklyMenus menu = weeklyMenusDAO.getMenuByID(menuID);
    boolean found = false;

    for (WeeklyMenus item : cart) {
        if (item.getMenuID() == menuID) {
            item.setQuantity(item.getQuantity() + 1); 
            found = true;
            break;
        }
    }

    if (!found) {
        menu.setQuantity(1); 
        cart.add(menu);
    }

    session.setAttribute("cart", cart);
}

    private void updateCart(HttpServletRequest request, HttpSession session) {
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        List<WeeklyMenus> cart = (List<WeeklyMenus>) session.getAttribute("cart");
        if (cart != null) {
            for (WeeklyMenus menu : cart) {
                if (menu.getMenuID() == menuID) {
                    menu.setQuantity(quantity);
                    break;
                }
            }
        }

        session.setAttribute("cart", cart);
    }

    private void removeFromCart(HttpServletRequest request, HttpSession session) {
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        List<WeeklyMenus> cart = (List<WeeklyMenus>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(menu -> menu.getMenuID() == menuID);
        }

        session.setAttribute("cart", cart);
        
    }
private void calculateTotals(HttpServletRequest request, HttpSession session) {
        List<WeeklyMenus> cart = (List<WeeklyMenus>) session.getAttribute("cart");

        if (cart == null) {
            request.setAttribute("cartSubtotal", 0);
            request.setAttribute("shippingCost", 0);
            request.setAttribute("cartTotal", 0);
            return;
        }

        double subtotal = 0;
        for (WeeklyMenus menu : cart) {
            subtotal += menu.getPrice() * menu.getQuantity();
        }

        double shippingCost = calculateShipping(subtotal);
        double total = subtotal + shippingCost;

        request.setAttribute("cartSubtotal", subtotal);
        request.setAttribute("shippingCost", shippingCost);
        request.setAttribute("cartTotal", total);
    }

    private double calculateShipping(double subtotal) {
        return subtotal > 100 ? 0 : 10; // Example shipping calculation
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
