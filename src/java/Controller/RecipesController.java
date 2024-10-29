/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.RecipesDAO;
import Models.Recipes;
import Models.WeeklyMenus;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
@WebServlet(name="RecipesController", urlPatterns={"/recipe"})
public class RecipesController extends HttpServlet {
      private RecipesDAO recipesDAO = new RecipesDAO();
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
            out.println("<title>Servlet RecipesController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecipesController at " + request.getContextPath () + "</h1>");
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
        String menuID = request.getParameter("menuID");
        Recipes recipe = null;
        if (menuID != null) {
            recipe = recipesDAO.getRecipeByMenuID(Integer.parseInt(menuID));
        }

        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("/recipeForm.jsp").forward(request, response);
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
        String recipeID = request.getParameter("recipeID");
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        String recipeName = request.getParameter("recipeName");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");

        WeeklyMenus weeklyMenus = new WeeklyMenus();
        weeklyMenus.setMenuID(menuID);
        
        Recipes recipe = new Recipes();
        recipe.setRecipeName(recipeName);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);
        recipe.setWeeklyMenus(weeklyMenus);

        if (recipeID == null || recipeID.isEmpty()) {
            // Create new recipe
            recipesDAO.addRecipe(recipe);
        } else {
            // Update existing recipe
            recipe.setRecipeID(Integer.parseInt(recipeID));
            recipesDAO.updateRecipe(recipe);
        }

        response.sendRedirect("recipe?menuID=" + recipe.weeklyMenus.getMenuID());
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
