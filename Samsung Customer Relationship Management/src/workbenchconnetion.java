package samsungcrm;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class workbenchconnection {
 
    // MySQL URL and credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database_proj";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "CRM123";
 
    public static Connection getConnection() {
        Connection con = null;
        try {
            // MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // Establish connection
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
