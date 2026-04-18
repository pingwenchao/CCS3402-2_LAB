// PING WENCHAO 226969
// script.js - Lab 4 AJAX and Real-time Validation Logic

document.addEventListener('DOMContentLoaded', function() {

    // --- DOM Elements ---
    const regForm = document.getElementById('registerForm');
    const loginForm = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const emailFeedback = document.getElementById('emailFeedback');
    const nameInput = document.getElementById('name');
    const passwordInput = document.getElementById('password');
    const confirmInput = document.getElementById('confirmPassword');
    const regBtn = document.getElementById('regBtn');
    const passwordReqs = document.getElementById('passwordReqs');

    // State variables to track if form is allowed to submit
    let isEmailAvailable = false;
    let isPasswordValid = false;

    // ==========================================
    // 1. AJAX: Email Existence Check (Requirement 3)
    // ==========================================
    if (emailInput) {
        emailInput.addEventListener('blur', function() {
            const email = this.value.trim();
            if (email === "") return;

            // Prepare data to send
            const formData = new FormData();
            formData.append('email', email);

            // Send AJAX request to check_user.php
            fetch('check_user.php', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "User already exists") {
                    emailFeedback.textContent = "✖ " + data;
                    emailFeedback.style.color = "#ff3b30"; // Red
                    isEmailAvailable = false;
                } else {
                    emailFeedback.textContent = "✔ Email is available";
                    emailFeedback.style.color = "#34c759"; // Green
                    isEmailAvailable = true;
                }
                checkFormValidity();
            })
            .catch(error => console.error('Error:', error));
        });
    }

    // ==========================================
    // 2. Real-Time Password Strength (Part II)
    // ==========================================
    if (passwordInput) {
        passwordInput.addEventListener('input', function() {
            const pw = this.value;
            const nameVal = nameInput ? nameInput.value.trim().toLowerCase() : "";
            
            // Show the requirements panel when user starts typing
            passwordReqs.style.display = 'block';

            // Rule 1: Length between 12 and 64
            const isValidLength = pw.length >= 12 && pw.length <= 64;
            updateReqUI('req-length', isValidLength);

            // Rule 2: At least one digit
            const hasDigit = /\d/.test(pw);
            updateReqUI('req-digit', hasDigit);

            // Rule 3: At least one lowercase letter
            const hasLower = /[a-z]/.test(pw);
            updateReqUI('req-lower', hasLower);

            // Rule 4: At least one uppercase letter
            const hasUpper = /[A-Z]/.test(pw);
            updateReqUI('req-upper', hasUpper);

            // Rule 5: At least one special character (%^!@#${}[])
            const hasSpecial = /[%^!@#${}\[\]]/.test(pw);
            updateReqUI('req-special', hasSpecial);

            // Rule 6: MUST NOT contain ; , :
            const hasForbidden = /[;,\:]/.test(pw);
            updateReqUI('req-forbidden', !hasForbidden); // Inverse logic

            // Rule 7: Cannot start or end with a space
            const hasEdgeSpace = /^\s|\s$/.test(pw);
            updateReqUI('req-space', !hasEdgeSpace); // Inverse logic

            // Rule 8: Cannot contain first or last name
            let containsName = false;
            if (nameVal.length > 0) {
                const nameParts = nameVal.split(/\s+/);
                for (let part of nameParts) {
                    if (part.length > 1 && pw.toLowerCase().includes(part)) {
                        containsName = true;
                        break;
                    }
                }
            }
            updateReqUI('req-name', !containsName); // Inverse logic

            // Check if ALL rules passed
            isPasswordValid = isValidLength && hasDigit && hasLower && hasUpper && 
                              hasSpecial && !hasForbidden && !hasEdgeSpace && !containsName;
            
            checkFormValidity();
        });
    }

    // Helper function to toggle green/red classes
    function updateReqUI(elementId, isValid) {
        const el = document.getElementById(elementId);
        if (!el) return;

        if (isValid) {
            el.classList.remove('invalid');
            el.classList.add('valid');
            el.textContent = el.textContent.replace('✖', '✔');
        } else {
            el.classList.remove('valid');
            el.classList.add('invalid');
            el.textContent = el.textContent.replace('✔', '✖');
        }
    }

    // Enable Register button only if everything is perfect
    function checkFormValidity() {
        if (!regBtn || !confirmInput) return;
        const passwordsMatch = passwordInput.value === confirmInput.value && passwordInput.value !== "";
        
        if (isEmailAvailable && isPasswordValid && passwordsMatch) {
            regBtn.disabled = false;
        } else {
            regBtn.disabled = true;
        }
    }

    if (confirmInput) {
        confirmInput.addEventListener('input', checkFormValidity);
    }

    // ==========================================
    // 3. AJAX: Handle Registration Submission
    // ==========================================
    if (regForm) {
        regForm.addEventListener('submit', function(e) {
            e.preventDefault(); // Stop normal page reload

            const formData = new FormData(this);
            const feedbackEl = document.getElementById('registerFeedback');

            fetch('register_user.php', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                feedbackEl.textContent = data;
                if (data.includes("Successful")) {
                    feedbackEl.style.color = "#34c759";
                    // Redirect to login page after 1.5 seconds
                    setTimeout(() => { window.location.href = 'login.html'; }, 1500);
                } else {
                    feedbackEl.style.color = "#ff3b30";
                }
            })
            .catch(error => console.error('Error:', error));
        });
    }

    // ==========================================
    // 4. AJAX: Handle Login Submission
    // ==========================================
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const formData = new FormData(this);

            fetch('login.php', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                // Requirement 7b: Display "Successful login" or "Login failed"
                if (data.includes("Successful")) {
                    alert(data); // Using alert or you can use a feedback div
                    // window.location.href = 'dashboard.php'; // Future expansion
                } else {
                    alert(data);
                }
            })
            .catch(error => console.error('Error:', error));
        });
    }

});