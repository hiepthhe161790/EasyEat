package Controller;

import DAO.UsersDAO;
import Models.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="ProfileController", urlPatterns={"/profile"})
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            user.setName(request.getParameter("name"));
            user.setPhone(request.getParameter("phone"));
            user.setAddress(request.getParameter("address"));

            UsersDAO usersDAO = new UsersDAO();
            if (usersDAO.updateUserProfile(user)) {
                session.setAttribute("user", user);
                request.setAttribute("message", "Profile updated successfully.");
            } else {
                request.setAttribute("error", "Failed to update profile.");
            }
        } else if ("changePassword".equals(action)) {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");

            if (user.getPassword().equals(currentPassword)) {
                UsersDAO usersDAO = new UsersDAO();
                if (usersDAO.changeUserPassword(user.getUserID(), newPassword)) {
                    user.setPassword(newPassword);
                    session.setAttribute("user", user);
                    request.setAttribute("message", "Password changed successfully.");
                } else {
                    request.setAttribute("error", "Failed to change password.");
                }
            } else {
                request.setAttribute("error", "Current password is incorrect.");
            }
        }

        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
