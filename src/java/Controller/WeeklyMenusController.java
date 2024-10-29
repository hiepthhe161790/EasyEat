/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.DietaryRequirementsDAO;
import DAO.WeeklyMenusDAO;
import Models.DietaryRequirements;
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
@WebServlet(name="WeeklyMenusController", urlPatterns={"/shop"})
public class WeeklyMenusController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int page = 1;
    int pageSize = 5;
    int dietaryRequirementID = -1; // Default value to indicate no category filter

    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }
    if (request.getParameter("category") != null) {
        dietaryRequirementID = Integer.parseInt(request.getParameter("category"));
    }

    WeeklyMenusDAO weeklyMenusDAO = new WeeklyMenusDAO();
    DietaryRequirementsDAO dietaryRequirementsDAO = new DietaryRequirementsDAO();
    int totalMenus;
    List<WeeklyMenus> menus;

    if (dietaryRequirementID == -1) {
        totalMenus = weeklyMenusDAO.getTotalMenusCount();
        menus = weeklyMenusDAO.getAllMenuses(page, pageSize);
    } else {
        totalMenus = weeklyMenusDAO.getMenusCountByCategory(dietaryRequirementID);
        menus = weeklyMenusDAO.getMenusByCategory(dietaryRequirementID, page, pageSize);
    }

    int totalPages = (int) Math.ceil((double) totalMenus / pageSize);
    List<DietaryRequirements> categories = dietaryRequirementsDAO.getAllDietaryRequirements();

    request.setAttribute("menus", menus);
    request.setAttribute("categories", categories);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("selectedCategory", dietaryRequirementID); // Pass selected category ID
    request.getRequestDispatcher("/shop.jsp").forward(request, response);
}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle form submission for adding new menu
        String menuName = request.getParameter("menuName");
        String description = request.getParameter("description");
        int dietaryRequirementID = Integer.parseInt(request.getParameter("dietaryRequirements"));
String imageUrl = request.getParameter("imageUrl"); 
double price = Double.parseDouble(request.getParameter("price"));
        DietaryRequirements dietaryRequirement = new DietaryRequirements(dietaryRequirementID, "", ""); // Dummy values for name and description
        WeeklyMenus menu = new WeeklyMenus(0, menuName, description, dietaryRequirement,imageUrl,price);

        WeeklyMenusDAO dao = new WeeklyMenusDAO();
        dao.addMenu(menu);

        response.sendRedirect("weeklyMenus");
    }

}
