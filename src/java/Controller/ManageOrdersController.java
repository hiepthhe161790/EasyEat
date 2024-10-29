package Controller;

import DAO.OrdersDAO;
import DAO.UsersDAO;
import Models.Orders;
import Models.Users;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ManageOrdersController", urlPatterns={"/manageOrders"})
public class ManageOrdersController extends HttpServlet {
   
    private OrdersDAO ordersDAO;
    private UsersDAO usersDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        ordersDAO = new OrdersDAO();
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
            case "search":
                searchOrders(request, response);
                break;
            default:
                listOrders(request, response);
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
                insertOrder(request, response);
                break;
            case "update":
                updateOrder(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
             case "search":
                searchOrders(request, response);
                break;
            default:
                listOrders(request, response);
                break;
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Orders> ordersList = ordersDAO.getAllOrders();
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();
        request.setAttribute("order", null); // Thiết lập giá trị null để JSP nhận biết là form mới
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher("orderFormStaff.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        Orders existingOrder = ordersDAO.getOrderById(orderID);
        List<Users> usersList = usersDAO.getAllUsers();
        request.setAttribute("order", existingOrder);
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher("orderFormStaff.jsp").forward(request, response);
    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        Date deliveryDate = Date.valueOf(request.getParameter("deliveryDate"));
        String status = request.getParameter("status");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        Users user = new Users(userID);
        Orders newOrder = new Orders(0, user, orderDate, deliveryDate, status, totalAmount);
        ordersDAO.addOrder(newOrder);
        response.sendRedirect("manageOrders");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        Date deliveryDate = Date.valueOf(request.getParameter("deliveryDate"));
        String status = request.getParameter("status");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        Users user = new Users(userID);
        Orders order = new Orders(orderID, user, orderDate, deliveryDate, status, totalAmount);
        ordersDAO.updateOrder(order);
        response.sendRedirect("manageOrders");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        ordersDAO.deleteOrder(orderID);
        response.sendRedirect("manageOrders");
    }
       private void searchOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("orderDate");
        Date orderDate = (dateString != null && !dateString.isEmpty()) ? Date.valueOf(dateString) : null;
        String userContact = request.getParameter("userContact");
        
        List<Orders> ordersList = ordersDAO.searchOrders(orderDate, userContact);
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }
}
