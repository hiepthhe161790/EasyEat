package DAO;

import Context.DBContext;
import Models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends DBContext {
 public Users getUserByEmail(String email) {
        Users user = null;
        String query = "SELECT * FROM Users WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setRole(rs.getString("Role"));
                user.setPassword(rs.getString("Password"));
                user.setActive(rs.getBoolean("Active")); // Add this line
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setRole(rs.getString("Role"));
                user.setPassword(rs.getString("Password"));
                user.setActive(rs.getBoolean("Active")); // Add this line
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return users;
    }

    public Users getUserById(int userID) {
        Users user = null;
        String query = "SELECT * FROM Users WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setRole(rs.getString("Role"));
                user.setPassword(rs.getString("Password"));
                user.setActive(rs.getBoolean("Active")); // Add this line
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return user;
    }

    public void addUser(Users user) {
        String query = "INSERT INTO Users (Name, Email, Phone, Address, Role, Password, Active) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.isActive()); // Add this line
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 public void addUsers(Users user) {
        try {
            String query = "INSERT INTO Users (name, email, phone, address, role, password,active) VALUES (?, ?, ?, ?, ?, ?,1)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(Users user) {
        String query = "UPDATE Users SET Name = ?, Email = ?, Phone = ?, Address = ?, Role = ?, Password = ?, Active = ? WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.isActive()); // Add this line
            ps.setInt(8, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void deleteUser(int userID) {
        String query = "DELETE FROM Users WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public boolean updateUserProfile(Users user) {
        String query = "UPDATE Users SET Name = ?, Phone = ?, Address = ? WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getAddress());
            ps.setInt(4, user.getUserID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeUserPassword(int userID, String newPassword) {
        String query = "UPDATE Users SET Password = ? WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void toggleUserActiveStatus(int userID, boolean isActive) {
        String query = "UPDATE Users SET Active = ? WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setBoolean(1, isActive);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       public boolean emailExists(String email) {
        boolean exists = false;
        try {
            String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
       public static void main(String[] args) {
        UsersDAO aO = new UsersDAO();
        aO.deleteUser(19);
    }
}
