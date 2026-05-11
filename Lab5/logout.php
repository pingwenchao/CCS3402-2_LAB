<?php
// PING WENCHAO 226969
// logout.php - Destroy session and cookies safely

session_start();

// Destroy all session data
$_SESSION = array();
session_destroy();

// Clear the persistent cookie if it exists
if (isset($_COOKIE['user_email'])) {
    setcookie('user_email', '', time() - 3600, '/');
}

// Redirect back to login page
header("Location: login.html");
exit();
?>