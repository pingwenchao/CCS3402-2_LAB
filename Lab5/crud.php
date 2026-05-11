<?php
// PING WENCHAO 226969
// crud.php - API endpoint for all CRUD and Group operations

session_start();
require_once 'db.php';
header('Content-Type: application/json');

// Ensure only authorized admins can perform operations
if (!isset($_SESSION['user_category']) || $_SESSION['user_category'] !== 'admin') {
    echo json_encode(["status" => "error", "message" => "Unauthorized access."]);
    exit();
}

$action = isset($_POST['action']) ? $_POST['action'] : (isset($_GET['action']) ? $_GET['action'] : '');

switch ($action) {
    
    // Read: Fetch all users
    case 'read':
        $result = $conn->query("SELECT id, name, email, category, status FROM users ORDER BY id DESC");
        $users = [];
        while ($row = $result->fetch_assoc()) {
            $users[] = $row;
        }
        echo json_encode(["status" => "success", "data" => $users]);
        break;

    // Create / Update: Handle user form submission
    case 'save':
        $id = $_POST['id'];
        $name = $_POST['name'];
        $email = $_POST['email'];
        $category = $_POST['category'];
        $status = $_POST['status'];
        $password = trim($_POST['password']);

        if (empty($id)) {
            // Create new user
            $hashed_password = password_hash($password, PASSWORD_DEFAULT);
            $stmt = $conn->prepare("INSERT INTO users (name, email, password, category, status) VALUES (?, ?, ?, ?, ?)");
            $stmt->bind_param("sssss", $name, $email, $hashed_password, $category, $status);
        } else {
            // Update existing user
            if (!empty($password)) {
                $hashed_password = password_hash($password, PASSWORD_DEFAULT);
                $stmt = $conn->prepare("UPDATE users SET name=?, email=?, password=?, category=?, status=? WHERE id=?");
                $stmt->bind_param("sssssi", $name, $email, $hashed_password, $category, $status, $id);
            } else {
                // Keep old password if input is blank
                $stmt = $conn->prepare("UPDATE users SET name=?, email=?, category=?, status=? WHERE id=?");
                $stmt->bind_param("ssssi", $name, $email, $category, $status, $id);
            }
        }

        if ($stmt->execute()) {
            echo json_encode(["status" => "success", "message" => "Record saved successfully."]);
        } else {
            echo json_encode(["status" => "error", "message" => "Database error."]);
        }
        $stmt->close();
        break;

    // Delete: Remove single user
    case 'delete':
        $id = $_POST['id'];
        $stmt = $conn->prepare("DELETE FROM users WHERE id=?");
        $stmt->bind_param("i", $id);
        if ($stmt->execute()) {
            echo json_encode(["status" => "success"]);
        }
        $stmt->close();
        break;

    // Group Operation: Handle batch actions
    case 'batch':
        $batchAction = $_POST['batchAction'];
        $ids = json_decode($_POST['ids']); // Receive array of IDs

        if (empty($ids)) {
            echo json_encode(["status" => "error", "message" => "No users selected."]);
            exit();
        }

        $placeholders = implode(',', array_fill(0, count($ids), '?'));
        $types = str_repeat('i', count($ids));

        if ($batchAction === 'delete') {
            $stmt = $conn->prepare("DELETE FROM users WHERE id IN ($placeholders)");
            $stmt->bind_param($types, ...$ids);
        } else {
            $newStatus = ($batchAction === 'activate') ? 'active' : 'inactive';
            $stmt = $conn->prepare("UPDATE users SET status = ? WHERE id IN ($placeholders)");
            $bind_types = "s" . $types;
            $stmt->bind_param($bind_types, $newStatus, ...$ids);
        }

        if ($stmt->execute()) {
            echo json_encode(["status" => "success"]);
        }
        $stmt->close();
        break;

    default:
        echo json_encode(["status" => "error", "message" => "Invalid action."]);
}

$conn->close();
?>