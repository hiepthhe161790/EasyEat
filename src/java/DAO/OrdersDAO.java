package DAO;

import Context.DBContext;
import Models.DietaryRequirements;
import Models.OrderDetails;
import Models.Orders;
import Models.Users;
import Models.WeeklyMenus;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrdersDAO extends DBContext {

    public List<Orders> getAllOrders() {
    List<Orders> orders = new ArrayList<>();
    String query = "SELECT o.OrderID, o.UserID, o.OrderDate, o.DeliveryDate, o.Status, o.TotalAmount, " +
                   "u.Name, u.Email, u.Phone, u.Address, u.Role, u.Password " +
                   "FROM Orders o " +
                   "JOIN Users u ON o.UserID = u.UserID";
    try {
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderID = rs.getInt("OrderID");
            int userID = rs.getInt("UserID");
            Date orderDate = rs.getDate("OrderDate");
            Date deliveryDate = rs.getDate("DeliveryDate");
            String status = rs.getString("Status");
            double totalAmount = rs.getDouble("TotalAmount");

            // User information
            String name = rs.getString("Name");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            String role = rs.getString("Role");
            String password = rs.getString("Password");

            Users user = new Users(userID, name, email, phone, address, role, password);
            Orders order = new Orders(orderID, user, orderDate, deliveryDate, status, totalAmount);
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return orders;
}


    public List<Orders> getOrdersByUser(int userID) {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                Date orderDate = rs.getDate("OrderDate");
                Date deliveryDate = rs.getDate("DeliveryDate");
                String status = rs.getString("Status");
                double totalAmount = rs.getDouble("TotalAmount");

                Users user = new Users(userID);
                Orders order = new Orders(orderID, user, orderDate, deliveryDate, status, totalAmount);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return orders;
    }
 public Orders getOrderById(int orderID) {
        Orders order = null;
        String query = "SELECT o.OrderID, o.OrderDate, o.DeliveryDate, o.Status, o.TotalAmount, " +
                       "u.UserID, u.Name, u.Email, u.Phone, u.Address, u.Role, u.Password " +
                       "FROM Orders o JOIN Users u ON o.UserID = u.UserID " +
                       "WHERE o.OrderID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String role = rs.getString("Role");
                String password = rs.getString("Password");

                Date orderDate = rs.getDate("OrderDate");
                Date deliveryDate = rs.getDate("DeliveryDate");
                String status = rs.getString("Status");
                double totalAmount = rs.getDouble("TotalAmount");

                Users user = new Users(userID, name, email, phone, address, role, password);
                order = new Orders(orderID, user, orderDate, deliveryDate, status, totalAmount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    public void addOrder(Orders order) {
        String query = "INSERT INTO Orders (UserID, OrderDate, DeliveryDate, Status, TotalAmount) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order.getUsers().getUserID());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getDeliveryDate());
            ps.setString(4, order.getStatus());
            ps.setDouble(5, order.getTotalAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement other CRUD operations (update, delete) here...
    public Map<String, List<Orders>> getOrdersGroupedByAddresss() {
        Map<String, List<Orders>> groupedOrders = new HashMap<>();
        String query = "SELECT Orders.*, Users.Address FROM Orders JOIN Users ON Orders.UserID = Users.UserID";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int userID = rs.getInt("UserID");
                Date orderDate = rs.getDate("OrderDate");
                Date deliveryDate = rs.getDate("DeliveryDate");
                String status = rs.getString("Status");
                double totalAmount = rs.getDouble("TotalAmount");
                String address = rs.getString("Address");

                Users user = new Users(userID);
                Orders order = new Orders(orderID, user, orderDate, deliveryDate, status, totalAmount);

                if (!groupedOrders.containsKey(address)) {
                    groupedOrders.put(address, new ArrayList<>());
                }
                groupedOrders.get(address).add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupedOrders;
    }
  public void updateOrder(Orders order) {
        String query = "UPDATE Orders SET UserID = ?, OrderDate = ?, DeliveryDate = ?, Status = ?, TotalAmount = ? WHERE OrderID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order.getUsers().getUserID());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getDeliveryDate());
            ps.setString(4, order.getStatus());
            ps.setDouble(5, order.getTotalAmount());
            ps.setInt(6, order.getOrderID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void deleteOrder(int orderID) {
        String query = "DELETE FROM Orders WHERE OrderID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Orders> getOrdersGroupedByAddress() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT o.OrderID, o.UserID, u.Address, o.OrderDate, o.DeliveryDate, o.Status, o.TotalAmount " +
                       "FROM Orders o " +
                       "JOIN Users u ON o.UserID = u.UserID " +
                       "ORDER BY u.Address";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Orders order = new Orders();
                Users user = new Users(); // Ensure the Users object is properly initialized

                order.setOrderID(rs.getInt("OrderID"));
                user.setUserID(rs.getInt("UserID"));
                user.setAddress(rs.getString("Address"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setDeliveryDate(rs.getDate("DeliveryDate"));
                order.setStatus(rs.getString("Status"));
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                order.setUsers(user); // Set the user in the order
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
  public int saveOrder(Orders order, List<WeeklyMenus> cart) {
        String orderQuery = "INSERT INTO Orders (UserID, OrderDate, DeliveryDate, Status, TotalAmount) VALUES (?, ?, ?, ?, ?)";
        String orderDetailsQuery = "INSERT INTO OrderDetails (OrderID, MenuID, Quantity) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUsers().getUserID());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getDeliveryDate());
            ps.setString(4, order.getStatus());
            ps.setDouble(5, order.getTotalAmount());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);

                ps = connection.prepareStatement(orderDetailsQuery);
                for (WeeklyMenus menu : cart) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, menu.getMenuID());
                    ps.setInt(3, menu.getQuantity());
                    ps.addBatch();
                }

                ps.executeBatch();
                connection.commit();
                return orderId;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }
  public List<WeeklyMenus> getTopSellingMenus(int count) {
    List<WeeklyMenus> topMenus = new ArrayList<>();
    String query = "SELECT TOP (?) wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price, COUNT(od.MenuID) AS MenuCount " +
                   "FROM Orders o " +
                   "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                   "JOIN WeeklyMenus wm ON od.MenuID = wm.MenuID " +
                   "GROUP BY wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price " +
                   "ORDER BY MenuCount DESC";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, count);
        rs = ps.executeQuery();
        while (rs.next()) {
            int menuID = rs.getInt("MenuID");
            String menuName = rs.getString("MenuName");
            String description = rs.getString("Description");
            String imageUrl = rs.getString("imageUrl");
            double price = rs.getDouble("price");

            WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, null, imageUrl, price);
            topMenus.add(menu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return topMenus;
}
 public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        String query = "SELECT od.OrderDetailID, od.Quantity, " +
                       "wm.MenuID, wm.MenuName, wm.Description, wm.imageUrl, wm.price " +
                       "FROM OrderDetails od " +
                       "JOIN WeeklyMenus wm ON od.MenuID = wm.MenuID " +
                       "WHERE od.OrderID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderDetailID = rs.getInt("OrderDetailID");
                int quantity = rs.getInt("Quantity");
                int menuID = rs.getInt("MenuID");
                String menuName = rs.getString("MenuName");
                String description = rs.getString("Description");
                String imageUrl = rs.getString("imageUrl");
                double price = rs.getDouble("price");

                // Tạo đối tượng WeeklyMenus
                WeeklyMenus menu = new WeeklyMenus(menuID, menuName, description, null, imageUrl, price);

                // Tạo đối tượng OrderDetails
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderDetailID(orderDetailID);
                orderDetail.setQuantity(quantity);
                orderDetail.setWeeklyMenus(menu);

                // Thêm vào danh sách chi tiết đơn hàng
                orderDetailsList.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên ResultSet, PreparedStatement ở đây
        }
        return orderDetailsList;
    }
public List<Orders> searchOrders(Date orderDate, String userContact) {
    List<Orders> orders = new ArrayList<>();
    String query = "SELECT o.OrderID, o.UserID, o.OrderDate, o.DeliveryDate, o.Status, o.TotalAmount, " +
                   "u.Name, u.Email, u.Phone, u.Address, u.Role, u.Password " +
                   "FROM Orders o " +
                   "JOIN Users u ON o.UserID = u.UserID " +
                   "WHERE (o.OrderDate = ? OR ? IS NULL) " +
                   "AND (u.Phone = ? OR u.Email = ? OR ? IS NULL)";
    try {
        ps = connection.prepareStatement(query);
        ps.setDate(1, orderDate);
        ps.setDate(2, orderDate);
        ps.setString(3, userContact);
        ps.setString(4, userContact);
        ps.setString(5, userContact);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int orderID = rs.getInt("OrderID");
            int userID = rs.getInt("UserID");
            Date orderDateResult = rs.getDate("OrderDate");
            Date deliveryDate = rs.getDate("DeliveryDate");
            String status = rs.getString("Status");
            double totalAmount = rs.getDouble("TotalAmount");

            // User information
            String name = rs.getString("Name");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            String role = rs.getString("Role");
             String password = rs.getString("Password");

            Users user = new Users(userID, name, email, phone, address, role,password);
            Orders order = new Orders(orderID, user, orderDateResult, deliveryDate, status, totalAmount);
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}


 public static void main(String[] args) {
    // Khởi tạo đối tượng OrdersDAO
    OrdersDAO ordersDAO = new OrdersDAO();

    // Tạo đối tượng Users giả lập với userID là 1 (cần đảm bảo rằng userID này tồn tại trong bảng Users)
//    Users user = new Users(1, "John Doe", "john.doe@example.com", "123456789", "123 Main St", "user", "password");
//
//    // Tạo đối tượng Orders với dữ liệu mẫu
//    Orders newOrder = new Orders(0, user, Date.valueOf("2024-07-15"), Date.valueOf("2024-07-20"), "Processing", 1000.50);
//
//    // Thêm đơn hàng vào cơ sở dữ liệu
//    ordersDAO.addOrder(newOrder);

    // Lấy danh sách tất cả các đơn hàng để kiểm tra
    List<Orders> orders = ordersDAO.getAllOrders();

    // In ra danh sách các đơn hàng
    for (Orders order : orders) {
        System.out.println("Order ID: " + order.getOrderID() +
                           ", User ID: " + order.getUsers().getUserID() +
                           ", Order Date: " + order.getOrderDate() +
                           ", Delivery Date: " + order.getDeliveryDate() +
                 ", User Date: " + order.users.getName() +
                           ", Status: " + order.getStatus() +
                           ", Total Amount: " + order.getTotalAmount());
    }
}


}
