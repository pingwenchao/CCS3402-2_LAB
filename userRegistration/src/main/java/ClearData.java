import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClearData {
    public static void main(String[] args) {
        // Database credentials
        String dbUrl = "jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:FSKTM";
        String username = "D226969";
        String password = "226969";

        // SQL query to delete all records from the users table
        String sql = "TRUNCATE TABLE users";

        try {
            // Establish connection and execute query
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stat = conn.createStatement();

            stat.execute(sql);
            System.out.println("All data cleared from 'users' table.");

            // Close database resources
            stat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}