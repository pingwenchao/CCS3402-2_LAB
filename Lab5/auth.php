<?php
// PING WENCHAO 226969
// auth.php - Handle Authentication, Session Management, and Cookies

// Start user session
session_start();

// Include database connection
require_once 'db.php'; 

// Set response type to JSON
header('Content-Type: application/json');

// Verify that all required fields are received via POST
if (isset($_POST['email']) && isset($_POST['password']) && isset($_POST['category'])) {
    
    $email = trim($_POST['email']);
    $password = $_POST['password'];
    $category = $_POST['category'];
    $remember = isset($_POST['remember']) ? true : false;

    // Prevent SQL injection using prepared statements
    $stmt = $conn->prepare("SELECT id, name, password, status FROM users WHERE email = ? AND category = ?");
    $stmt->bind_param("ss", $email, $category);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows === 1) {
        $user = $result->fetch_assoc();

        // Check if the user account is active before allowing login
        if ($user['status'] === 'inactive') {
            echo json_encode(["status" => "error", "message" => "Login failed: Your account is disabled."]);
            exit();
        }

        // Verify hashed password
        if (password_verify($password, $user['password'])) {
            
            // Login successful: Initialize session variables
            $_SESSION['user_id'] = $user['id'];
            $_SESSION['user_name'] = $user['name'];
            $_SESSION['user_category'] = $category;

            // Handle persistent login cookie
            if ($remember) {
                // Set cookie for 7 days
                setcookie("user_email", $email, time() + (86400 * 7), "/"); 
            } else {
                // Clear existing cookie if checkbox is unchecked
                setcookie("user_email", "", time() - 3600, "/"); 
            }

            echo json_encode(["status" => "success", "message" => "Login successful!", "redirect" => "dashboard.php"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Incorrect password."]);
        }
    } else {
        echo json_encode(["status" => "error", "message" => "User not found or category mismatch."]);
    }
    
    // Close statement
    $stmt->close();
} else {
    echo json_encode(["status" => "error", "message" => "Missing required fields."]);
}

// Close database connection
$conn->close();
?>