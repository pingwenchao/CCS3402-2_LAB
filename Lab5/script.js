// PING WENCHAO 226969
// script.js - Client-side Event Handling and AJAX Submission

document.addEventListener('DOMContentLoaded', function() {
    
    const loginForm = document.getElementById('loginForm');
    const loginAlert = document.getElementById('loginAlert');

    // Handle login form submission
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault(); 

            const formData = new FormData(this);

            // Send AJAX request to authentication script
            fetch('auth.php', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json()) 
            .then(data => {
                
                // Provide visual feedback based on authentication result
                if (data.status === 'success') {
                    // Update UI for successful login
                    loginAlert.classList.remove('alert-danger', 'd-none');
                    loginAlert.classList.add('alert-success');
                    loginAlert.textContent = data.message + " Redirecting...";
                    
                    // Redirect to the dashboard
                    setTimeout(() => {
                        window.location.href = data.redirect;
                    }, 1000);
                } else {
                    // Update UI for failed login
                    loginAlert.classList.remove('d-none', 'alert-success');
                    loginAlert.classList.add('alert-danger');
                    loginAlert.textContent = "✖ " + data.message;
                }
            })
            .catch(error => console.error('Error during authentication:', error));
        });
    }
});