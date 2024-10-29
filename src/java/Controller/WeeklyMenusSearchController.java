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
import java.util.List;

/**
 *
 * @author 
 */
@WebServlet(name="WeeklyMenusSearchController", urlPatterns={"/search"})
public class WeeklyMenusSearchController extends HttpServlet {
   
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
            out.println("<title>Servlet WeeklyMenusSearchController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WeeklyMenusSearchController at " + request.getContextPath () + "</h1>");
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
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<WeeklyMenus> menus = null;
        WeeklyMenusDAO weeklyMenusDAO = new WeeklyMenusDAO();
        if (keyword != null && !keyword.isEmpty()) {
            menus = weeklyMenusDAO.searchMenusByName(keyword);
        }
        request.setAttribute("menus", menus);
        request.getRequestDispatcher("/shop.jsp").forward(request, response);
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
    // Retrieve selected menu ID from form as int
    int selectedMenuId = Integer.parseInt(request.getParameter("selectedMenu"));
    
    // Initialize DAO and selected menu object
    WeeklyMenusDAO weeklyMenusDAO = new WeeklyMenusDAO();
    WeeklyMenus selectedMenu = null;
    
    // Optionally, fetch the full menu details based on the selected ID
    selectedMenu = weeklyMenusDAO.getMenuByID(selectedMenuId);
    
    // Pass the selected menu object to your JSP or process as needed
    request.setAttribute("selectedMenu", selectedMenu);
    request.getRequestDispatcher("/selectedMenuDetails.jsp").forward(request, response);
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
