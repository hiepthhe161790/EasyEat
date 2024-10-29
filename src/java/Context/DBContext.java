package Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FPT University - PRJ301
 */
public class DBContext {
    protected Connection connection;
    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public String xSql = null;

    public DBContext() {
        //@Students: You are allowed to edit user, pass, url variables to fit
        //your system configuration
        //You can also add more methods for Database Interaction tasks.
        //But we recommend you to do it in another class
        // For example : StudentDBContext extends DBContext ,
        //where StudentDBContext is located in dal package,
        try {
            String user = "sa";
            String pass = "12345678";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=EasyEatPRJ301";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
            con = connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loi vai lol");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.finalize();
        }
    }

    public static void main(String[] args) {
        DBContext db = new DBContext();
    }
}
