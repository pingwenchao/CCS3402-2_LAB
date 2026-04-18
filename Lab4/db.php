<?php
// PING WENCHAO 226969
// db.php - Database connection setup

// Default XAMPP database credentials
$host = "localhost";
$db_user = "root";     
$db_pass = "";         
$db_name = "students"; 

// Requirement: Proper error handling (Enable strict error reporting for MySQLi)
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

try {
    // Requirement 4b: Establish connection using mysqli extension
    $conn = new mysqli($host, $db_user, $db_pass, $db_name);
    
    // Set character set to utf8mb4 for security and compatibility
    $conn->set_charset("utf8mb4");
    
    // Note: Do not echo success messages here, as it will break AJAX JSON responses later.
    // If no exception is thrown, the connection is successful.

} catch (mysqli_sql_exception $e) {
    // Safe error handling: return error message and stop execution
    die("Database connection failed: " . $e->getMessage());
}
?>