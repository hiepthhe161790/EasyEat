package DAO;

import Context.DBContext;
import Models.MealPlanDetails;
import Models.PersonalMealPlans;
import Models.Users;
import Models.WeeklyMenus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalMealPlansDAO extends DBContext {

      public List<PersonalMealPlans> getAllMealPlans() {
        List<PersonalMealPlans> mealPlans = new ArrayList<>();
        String query = "SELECT * FROM PersonalMealPlans";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int mealPlanID = rs.getInt("MealPlanID");
                int userID = rs.getInt("UserID");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");

                Users user = new Users(userID); // Assuming Users class has a constructor that accepts userID
                PersonalMealPlans mealPlan = new PersonalMealPlans(mealPlanID, user, startDate, endDate);
                mealPlans.add(mealPlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return mealPlans;
    }

    public PersonalMealPlans getMealPlanById(int mealPlanID) {
        PersonalMealPlans mealPlan = null;
        String query = "SELECT * FROM PersonalMealPlans WHERE MealPlanID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mealPlanID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");

                Users user = new Users(userID);
                mealPlan = new PersonalMealPlans(mealPlanID, user, startDate, endDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return mealPlan;
    }

    public void addMealPlan(PersonalMealPlans mealPlan) {
        String query = "INSERT INTO PersonalMealPlans (UserID, StartDate, EndDate) VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mealPlan.getUsers().getUserID());
            ps.setDate(2, mealPlan.getStartDate());
            ps.setDate(3, mealPlan.getEndDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updateMealPlan(PersonalMealPlans mealPlan) {
        String query = "UPDATE PersonalMealPlans SET StartDate = ?, EndDate = ? WHERE MealPlanID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, mealPlan.getStartDate());
            ps.setDate(2, mealPlan.getEndDate());
            ps.setInt(3, mealPlan.getMealPlanID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void deleteMealPlan(int mealPlanID) {
    String deleteDetailsQuery = "DELETE FROM MealPlanDetails WHERE MealPlanID = ?";
    String deleteMealPlanQuery = "DELETE FROM PersonalMealPlans WHERE MealPlanID = ?";
    try {
        // Xóa các MealPlanDetails liên quan trước
        ps = connection.prepareStatement(deleteDetailsQuery);
        ps.setInt(1, mealPlanID);
        ps.executeUpdate();
        ps.close();

        // Xóa PersonalMealPlans
        ps = connection.prepareStatement(deleteMealPlanQuery);
        ps.setInt(1, mealPlanID);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }
}


    public void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addMealPlan(PersonalMealPlans mealPlan, List<MealPlanDetails> mealPlanDetailsList) {
        String mealPlanQuery = "INSERT INTO PersonalMealPlans (UserID, StartDate, EndDate) VALUES (?, ?, ?)";
        String mealPlanDetailsQuery = "INSERT INTO MealPlanDetails (MealPlanID, DayOfWeek, MealName, MenuID) VALUES (?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);

            // Insert into PersonalMealPlans
            ps = connection.prepareStatement(mealPlanQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, mealPlan.getUsers().getUserID());
            ps.setDate(2, mealPlan.getStartDate());
            ps.setDate(3, mealPlan.getEndDate());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int mealPlanID = rs.getInt(1);
                mealPlan.setMealPlanID(mealPlanID);

                // Insert into MealPlanDetails
                ps = connection.prepareStatement(mealPlanDetailsQuery);
                for (MealPlanDetails details : mealPlanDetailsList) {
                    ps.setInt(1, mealPlanID);
                    ps.setString(2, details.getDayOfWeek());
                    ps.setString(3, details.getMealName());
                    ps.setInt(4, details.getWeeklyMenus().getMenuID());
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeResources();
        }
    }
    public List<PersonalMealPlans> getMealPlansByUserID(int userID) {
    List<PersonalMealPlans> mealPlans = new ArrayList<>();
    String query = "SELECT * FROM PersonalMealPlans WHERE UserID = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        rs = ps.executeQuery();
        while (rs.next()) {
            int mealPlanID = rs.getInt("MealPlanID");
            Date startDate = rs.getDate("StartDate");
            Date endDate = rs.getDate("EndDate");

            Users user = new Users(userID);
            PersonalMealPlans mealPlan = new PersonalMealPlans(mealPlanID, user, startDate, endDate);
            mealPlans.add(mealPlan);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }
    return mealPlans;
}
 public List<MealPlanDetails> getMealPlanDetailsByMealPlanID(int mealPlanID) {
    List<MealPlanDetails> detailsList = new ArrayList<>();
    String query = "SELECT mpd.DetailID, mpd.DayOfWeek, mpd.MealName, mpd.MenuID, wm.MenuName " +
                   "FROM MealPlanDetails mpd " +
                   "JOIN WeeklyMenus wm ON mpd.MenuID = wm.MenuID " +
                   "WHERE mpd.MealPlanID = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, mealPlanID);
        rs = ps.executeQuery();
        while (rs.next()) {
            int detailID = rs.getInt("DetailID");
            String dayOfWeek = rs.getString("DayOfWeek");
            String mealName = rs.getString("MealName");
            int menuID = rs.getInt("MenuID");
            String menuName = rs.getString("MenuName");

            WeeklyMenus weeklyMenu = new WeeklyMenus(menuID, menuName); // Assuming WeeklyMenus class has a constructor that accepts menuID and menuName
            MealPlanDetails details = new MealPlanDetails(detailID, null, dayOfWeek, mealName, weeklyMenu);
            detailsList.add(details);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }
    return detailsList;
}

    public static void main(String[] args) {
        PersonalMealPlansDAO personalMealPlansDAO = new PersonalMealPlansDAO();
        List<MealPlanDetails> list = personalMealPlansDAO.getMealPlanDetailsByMealPlanID(1);
        for (MealPlanDetails personalMealPlans : list) {
            System.out.println(personalMealPlans.weeklyMenus.getMenuName());
            
        }
    }
   
}
