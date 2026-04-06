// PING WENCHAO 226969

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Utility class to remove all records from the 'users' table.
 * Useful for resetting the database during testing.
 */
public class ClearData {
    public static void main(String[] args) {
        // Oracle Database credentials
        String dbUrl = "jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:FSKTM";
        String username = "D226969";
        String password = "226969";

        // SQL query to truncate (empty) the users table
        String sql = "TRUNCATE TABLE users";

        try {
            // Establish connection and execute the truncate query
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stat = conn.createStatement();

            stat.execute(sql);
            System.out.println("All data cleared from 'users' table.");

            // Clean up resources
            stat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}