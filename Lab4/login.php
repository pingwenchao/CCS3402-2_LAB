<?php
// PING WENCHAO 226969
// login.php - Handle login verification

// Include the database connection file
require_once 'db.php';

// Verify that email and password are received
if (isset($_POST['email']) && isset($_POST['password'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    // Security Feature: Use Prepared Statements to fetch the hashed password
    $stmt = $conn->prepare("SELECT password FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();

    // Check if the email exists in the database
    if ($result->num_rows === 1) {
        $row = $result->fetch_assoc();
        
        // Additional Feature: Verify the plain-text input against the hashed password
        if (password_verify($password, $row['password'])) {
            // Requirement 7b: Respond with "Successful login"
            echo "Successful login"; 
        } else {
            // Requirement 7b: Respond with "Login failed"
            echo "Login failed";     
        }
    } else {
        // Email not found in the database
        echo "Login failed"; 
    }

    // Close statement
    $stmt->close();
}

// Close database connection
$conn->close();
?>