/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.PersonalMealPlansDAO;
import Models.MealPlanDetails;
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
@WebServlet(name="MealPlanDetailsServlet", urlPatterns={"/mealdetail"})
public class MealPlanDetailsServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mealPlanID = Integer.parseInt(request.getParameter("mealPlanID"));
        PersonalMealPlansDAO mealPlansDAO = new PersonalMealPlansDAO();
        List<MealPlanDetails> mealPlanDetails = mealPlansDAO.getMealPlanDetailsByMealPlanID(mealPlanID);
        request.setAttribute("mealPlanDetails", mealPlanDetails);
        request.getRequestDispatcher("/mealPlanDetails.jsp").forward(request, response);
    }

}
