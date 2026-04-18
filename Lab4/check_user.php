<?php
// PING WENCHAO 226969
// check_user.php - Handle AJAX request to check user existence

// Include the database connection file
require_once 'db.php'; 

// Check if the email parameter is received via POST request
if (isset($_POST['email'])) {
    $email = $_POST['email'];

    // Security Feature: Use Prepared Statements to prevent SQL injection
    $stmt = $conn->prepare("SELECT id FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();

    // Requirement 4a: Respond with "User already exists" if a match is found
    if ($result->num_rows > 0) {
        echo "User already exists";
    } else {
        echo "Available";
    }

    // Close statement to free resources
    $stmt->close();
}

// Close database connection
$conn->close();
?>