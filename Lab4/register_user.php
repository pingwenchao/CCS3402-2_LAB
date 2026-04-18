<?php
// PING WENCHAO 226969
// register_user.php - Handle new user registration

// Include the database connection file
require_once 'db.php';

// Verify that all required fields are received
if (isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])) {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    // Additional Feature (Optional): Password Hashing for security
    // Never store plain-text passwords in the database
    $hashed_password = password_hash($password, PASSWORD_DEFAULT);

    // Requirement 5: Insert user information into the "users" table
    $stmt = $conn->prepare("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
    $stmt->bind_param("sss", $name, $email, $hashed_password);

    // Execute the query and return appropriate response
    if ($stmt->execute()) {
        echo "Registration Successful";
    } else {
        echo "Error: Could not register user.";
    }

    // Close statement
    $stmt->close();
}

// Close database connection
$conn->close();
?>