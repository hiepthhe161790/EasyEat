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
@WebServlet(name="MenuDetailController", urlPatterns={"/detail"})
public class MenuDetailController extends HttpServlet {

    private WeeklyMenusDAO weeklyMenusDAO;
    private DietaryRequirementsDAO dietaryRequirementsDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        weeklyMenusDAO = new WeeklyMenusDAO();
        dietaryRequirementsDAO = new DietaryRequirementsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showMenuDetail(request, response);
    }

    private void showMenuDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        WeeklyMenus menu = weeklyMenusDAO.getMenuByID(menuID);
        Recipes recipes = weeklyMenusDAO.getRecipesByMenuID(menuID);
        List<WeeklyMenus> menuRelate = weeklyMenusDAO.getRelatedMenusByMenuID(menuID);
        List<DietaryRequirements> dietaryRequirements = dietaryRequirementsDAO.getAllDietaryRequirements();
     
        request.setAttribute("menu", menu);
        request.setAttribute("recipes", recipes);
            request.setAttribute("menuRelate", menuRelate);
        request.setAttribute("dietaryRequirements", dietaryRequirements);
        request.getRequestDispatcher("/single-product.jsp").forward(request, response);
    }

}
