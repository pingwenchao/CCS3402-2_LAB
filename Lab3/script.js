// PING WENCHAO 226969

/**
 * Initialize storage simulation.
 * Uses localStorage to act as the "user.txt" file.
 */
function initializeStorage() {
    if (!localStorage.getItem("user_data_file")) {
        localStorage.setItem("user_data_file", ""); // Initialize empty "file"
    }
}

/**
 * Handles the registration form submission logic.
 */
function handleRegistration(event) {
    event.preventDefault();

    // Retrieve input values
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirmPassword").value;

    // Requirement 2: Client-side Validation (Passwords match)
    if (password !== confirm) {
        alert("Passwords do not match! Please try again.");
        return;
    }

    // Requirement 3: Check User Existence (Reading simulated txt)
    const fileContent = localStorage.getItem("user_data_file");
    const userRows = fileContent.split("\n");
    
    for (let row of userRows) {
        if (row.trim()) {
            const columns = row.split(" | ");
            if (columns[1] === email) { // Index 1 is the Email
                alert("User already exists!");
                return;
            }
        }
    }

    // Requirement 4: Registration Success (Writing to simulated txt)
    const newEntry = name + " | " + email + " | " + password;
    const updatedContent = fileContent ? fileContent + "\n" + newEntry : newEntry;
    localStorage.setItem("user_data_file", updatedContent);

    alert("Registration Successful!");
    window.location.href = "login.html"; // Redirect to login page
}

/**
 * Handles the login form verification logic.
 */
function handleLogin(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Requirement 6: Check credentials against data in "user.txt"
    const fileContent = localStorage.getItem("user_data_file");
    const userRows = fileContent.split("\n");
    let loginFound = false;

    for (let row of userRows) {
        if (row.trim()) {
            // Destructure the array into variables
            const [uName, uEmail, uPass] = row.split(" | ");
            
            // Verify both email and password
            if (uEmail === email && uPass === password) {
                loginFound = true;
                break;
            }
        }
    }

    // Requirement 6a & 6b: Display success or failure message
    if (loginFound) {
        alert("Successful login!");
    } else {
        alert("Login failed! Incorrect email or password.");
    }
}

// ---------------- Execution ----------------

initializeStorage();

// Attach events based on which page is currently loaded in the browser
const regForm = document.getElementById("registerForm");
if (regForm) {
    regForm.addEventListener("submit", handleRegistration);
}

const logForm = document.getElementById("loginForm");
if (logForm) {
    logForm.addEventListener("submit", handleLogin);
}