/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.PersonalMealPlansDAO;
import Models.MealPlanDetails;
import Models.PersonalMealPlans;
import Models.Users;
import Models.WeeklyMenus;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
@WebServlet(name="PersonalController", urlPatterns={"/personalMealPlans"})
public class PersonalController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userID = user.getUserID();
        PersonalMealPlansDAO dao = new PersonalMealPlansDAO();

        if ("delete".equals(action)) {
            int mealPlanID = Integer.parseInt(request.getParameter("mealPlanID"));
            dao.deleteMealPlan(mealPlanID);
            response.sendRedirect("personalMealPlans");
        } else if ("edit".equals(action)) {
            int mealPlanID = Integer.parseInt(request.getParameter("mealPlanID"));
            PersonalMealPlans mealPlan = dao.getMealPlanById(mealPlanID);
            request.setAttribute("mealPlan", mealPlan);
            request.getRequestDispatcher("editMealPlan.jsp").forward(request, response);
        } else {
            List<PersonalMealPlans> mealPlans = dao.getMealPlansByUserID(userID);
            request.setAttribute("mealPlans", mealPlans);
            request.getRequestDispatcher("viewMealPlans.jsp").forward(request, response);
        }
    }

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("user");

            if (user == null) {
                response.sendRedirect("login");
                return;
            }

            int userID = user.getUserID();
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));

            PersonalMealPlans mealPlan = new PersonalMealPlans(0, user, startDate, endDate);

            List<MealPlanDetails> mealPlanDetailsList = new ArrayList<>();
            String[] daysOfWeek = request.getParameterValues("dayOfWeek");
            String[] mealNames = request.getParameterValues("mealName");
            String[] weeklyMenuIDs = request.getParameterValues("weeklyMenuID");

            for (int i = 0; i < daysOfWeek.length; i++) {
                String dayOfWeek = daysOfWeek[i];
                String mealName = mealNames[i];
                int weeklyMenuID = Integer.parseInt(weeklyMenuIDs[i]);

                WeeklyMenus weeklyMenu = new WeeklyMenus(weeklyMenuID);
                MealPlanDetails details = new MealPlanDetails(0, mealPlan, dayOfWeek, mealName, weeklyMenu);
                mealPlanDetailsList.add(details);
            }

            PersonalMealPlansDAO dao = new PersonalMealPlansDAO();
            dao.addMealPlan(mealPlan, mealPlanDetailsList);

            response.sendRedirect(request.getContextPath() + "/personalMealPlans");
        }
    }

}
