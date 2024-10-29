//package DAO;
//
//import Context.DBContext;
//import Models.MealPlanDetails;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MealPlanDetailsDAO extends DBContext {
//
//    public List<MealPlanDetails> getAllMealPlanDetails() {
//        List<MealPlanDetails> details = new ArrayList<>();
//        String query = "SELECT * FROM MealPlanDetails";
//        try {
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//             
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//        return details;
//    }
//
//    public void addMealPlanDetail(MealPlanDetails detail) {
//        String query = "INSERT INTO MealPlanDetails (MealPlanID, DayOfWeek, MealName, MenuID) VALUES (?, ?, ?, ?)";
//        try {
//         
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//    }
//
//    // Implement other CRUD operations (update, delete) here...
//
//}
