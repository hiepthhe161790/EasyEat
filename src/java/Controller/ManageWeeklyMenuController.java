/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.DietaryRequirementsDAO;
import DAO.WeeklyMenusDAO;
import Models.DietaryRequirements;
import Models.Recipes;
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
@WebServlet(name="ManageWeeklyMenuController",  urlPatterns = {"/weeklyMenus","/weeklyMenus/createForm", "/weeklyMenus/create", "/weeklyMenus/update", "/weeklyMenus/delete", "/weeklyMenus/edit"})
public class ManageWeeklyMenuController extends HttpServlet {

    private WeeklyMenusDAO weeklyMenusDAO;
    private DietaryRequirementsDAO dietaryRequirementsDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        weeklyMenusDAO = new WeeklyMenusDAO();
        dietaryRequirementsDAO = new DietaryRequirementsDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/weeklyMenus":
                showAllMenus(request, response);
                break;
            case "/weeklyMenus/createForm":
                showCreateForm(request, response);
                break;
            case "/weeklyMenus/edit":
                showEditForm(request, response);
                break;
             case "/weeklyMenus/delete":
            deleteMenu(request, response);
            break;
            default:
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/weeklyMenus/create":
                createMenu(request, response);
                break;
            case "/weeklyMenus/update":
                updateMenu(request, response);
                break;
            case "/weeklyMenus/delete":
                deleteMenu(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                break;
        }
    }

    private void showAllMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int page = 1;
    int pageSize = 5;

    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }

    int totalMenus = weeklyMenusDAO.getTotalMenusCount();
    int totalPages = (int) Math.ceil((double) totalMenus / pageSize);

    List<WeeklyMenus> menus = weeklyMenusDAO.getAllMenuses(page, pageSize);
    request.setAttribute("menus", menus);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("pageSize", pageSize);
    request.getRequestDispatcher("/weeklyMenus.jsp").forward(request, response);
}

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DietaryRequirements> dietaryRequirements = dietaryRequirementsDAO.getAllDietaryRequirements();
        request.setAttribute("dietaryRequirements", dietaryRequirements);
        request.getRequestDispatcher("/createMenu.jsp").forward(request, response);
    }

    private void createMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuName = request.getParameter("menuName");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl"); 
        double price = Double.parseDouble(request.getParameter("price"));
        int dietaryRequirementID = Integer.parseInt(request.getParameter("dietaryRequirements"));

        DietaryRequirements dietaryRequirement = new DietaryRequirements();
        dietaryRequirement.setDietaryRequirementID(dietaryRequirementID);

        WeeklyMenus menu = new WeeklyMenus(0, menuName, description, dietaryRequirement,imageUrl,price);
        weeklyMenusDAO.addMenu(menu);

        response.sendRedirect(request.getContextPath() + "/weeklyMenus");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        WeeklyMenus menu = weeklyMenusDAO.getMenuByID(menuID);
        Recipes recipes = weeklyMenusDAO.getRecipesByMenuID(menuID);
        List<DietaryRequirements> dietaryRequirements = dietaryRequirementsDAO.getAllDietaryRequirements();
         request.setAttribute("menu", menu);
        request.setAttribute("recipes", recipes);
        request.setAttribute("dietaryRequirements", dietaryRequirements);
        request.getRequestDispatcher("/editMenu.jsp").forward(request, response);
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        String menuName = request.getParameter("menuName");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl"); 
        double price = Double.parseDouble(request.getParameter("price"));
        int dietaryRequirementID = Integer.parseInt(request.getParameter("dietaryRequirements"));

        DietaryRequirements dietaryRequirement = new DietaryRequirements();
        dietaryRequirement.setDietaryRequirementID(dietaryRequirementID);

        WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, dietaryRequirement,imageUrl,price);
        weeklyMenusDAO.updateMenu(menu);

        response.sendRedirect(request.getContextPath() + "/weeklyMenus");
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        weeklyMenusDAO.deleteMenu(menuID);

        response.sendRedirect(request.getContextPath() + "/weeklyMenus");
    }
}