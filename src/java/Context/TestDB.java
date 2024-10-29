package Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        // Test the DBContext
        DBContext db = new DBContext();
        if (db.connection != null) {
            System.out.println("Connection to database established successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

    }
}
