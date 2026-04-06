import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        // Database credentials
        String dbUrl = "jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:FSKTM";
        String username = "D226969";
        String password = "226969";

        // SQL query to create the users table
        String sql = "CREATE TABLE users (" +
                "name VARCHAR2(100), " +
                "email VARCHAR2(100), " +
                "phone VARCHAR2(50), " +
                "address VARCHAR2(200), " +
                "password VARCHAR2(100))";

        try {
            // Establish connection and execute query
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stat = conn.createStatement();

            stat.execute(sql);
            System.out.println("Table 'users' created successfully.");

            // Close database resources
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error (Table might already exist): " + e.getMessage());
        }
    }
}