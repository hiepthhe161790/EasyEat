package Controller;

import DAO.PersonalMealPlansDAO;
import Models.MealPlanDetails;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "MealPlanDetailsController", urlPatterns = {"/mealPlanDetails"})
public class MealPlanDetailsController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mealPlanID = Integer.parseInt(request.getParameter("mealPlanID"));
        PersonalMealPlansDAO mealPlansDAO = new PersonalMealPlansDAO();
        List<MealPlanDetails> mealPlanDetails = mealPlansDAO.getMealPlanDetailsByMealPlanID(mealPlanID);
        request.setAttribute("mealPlanDetails", mealPlanDetails);
        request.getRequestDispatcher("/mealPlanDetails.jsp").forward(request, response);
    }
}
