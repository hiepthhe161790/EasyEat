package DAO;

import Context.DBContext;
import Models.DietaryRequirements;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DietaryRequirementsDAO extends DBContext {

    public List<DietaryRequirements> getAllDietaryRequirements() {
        List<DietaryRequirements> dietaryRequirementsList = new ArrayList<>();
        String query = "SELECT * FROM DietaryRequirements";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DietaryRequirements dietaryRequirements = new DietaryRequirements();
                dietaryRequirements.setDietaryRequirementID(rs.getInt("DietaryRequirementID"));
                dietaryRequirements.setName(rs.getString("Name"));
                dietaryRequirements.setDescription(rs.getString("Description"));
                dietaryRequirementsList.add(dietaryRequirements);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dietaryRequirementsList;
    }

    public DietaryRequirements getDietaryRequirementsById(int dietaryRequirementID) {
        DietaryRequirements dietaryRequirements = null;
        String query = "SELECT * FROM DietaryRequirements WHERE DietaryRequirementID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, dietaryRequirementID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dietaryRequirements = new DietaryRequirements();
                dietaryRequirements.setDietaryRequirementID(rs.getInt("DietaryRequirementID"));
                dietaryRequirements.setName(rs.getString("Name"));
                dietaryRequirements.setDescription(rs.getString("Description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dietaryRequirements;
    }

    public void addDietaryRequirements(DietaryRequirements dietaryRequirements) {
        String query = "INSERT INTO DietaryRequirements (Name, Description) VALUES (?, ?)";
        try {
           PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, dietaryRequirements.getName());
            ps.setString(2, dietaryRequirements.getDescription());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                dietaryRequirements.setDietaryRequirementID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDietaryRequirements(DietaryRequirements dietaryRequirements) {
        String query = "UPDATE DietaryRequirements SET Name = ?, Description = ? WHERE DietaryRequirementID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, dietaryRequirements.getName());
            ps.setString(2, dietaryRequirements.getDescription());
            ps.setInt(3, dietaryRequirements.getDietaryRequirementID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void deleteDietaryRequirements(int dietaryRequirementID) {
        String query = "DELETE FROM DietaryRequirements WHERE DietaryRequirementID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, dietaryRequirementID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DietaryRequirementsDAO dietaryRequirementsDAO = new DietaryRequirementsDAO();
        List<DietaryRequirements> dietaryRequirementses = dietaryRequirementsDAO.getAllDietaryRequirements();
        for (DietaryRequirements dietaryRequirementse : dietaryRequirementses) {
            System.out.println(dietaryRequirementse);
        }
    }
  
}
