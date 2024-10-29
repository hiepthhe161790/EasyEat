package controller;

import DAO.UsersDAO;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ManageUsersController", urlPatterns = {"/manageUsers"})
public class ManageUsersController extends HttpServlet {

    private UsersDAO usersDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usersDAO = new UsersDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
                  case "delete":
                deleteUser(request, response);
                break;
            case "toggleActive":
                toggleActiveStatus(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "toggleActive":
                toggleActiveStatus(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("userForm.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String password = request.getParameter("password");

        Users newUser = new Users();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setRole(role);
        newUser.setPassword(password);
        newUser.setActive(true); // Set default active status

        usersDAO.addUser(newUser);
        response.sendRedirect("manageUsers");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        Users existingUser = usersDAO.getUserById(userID);
        request.setAttribute("users", existingUser);
        request.getRequestDispatcher("userForm.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        Users user = new Users();
        user.setUserID(userID);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        user.setPassword(password);
        user.setActive(active); // Add this line

        usersDAO.updateUser(user);
        response.sendRedirect("manageUsers");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        usersDAO.deleteUser(userID);
        response.sendRedirect("manageUsers");
    }

    private void toggleActiveStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        boolean currentStatus = Boolean.parseBoolean(request.getParameter("currentStatus"));
        usersDAO.toggleUserActiveStatus(userID, !currentStatus);
        response.sendRedirect("manageUsers");
    }
}
