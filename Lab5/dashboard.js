// PING WENCHAO 226969
// dashboard.js - Complete JavaScript event handling for CRUD operations

document.addEventListener('DOMContentLoaded', function() {
    
    // Initial fetch to populate the data table
    fetchUsers();

    const userForm = document.getElementById('userForm');
    const btnAddUser = document.getElementById('btnAddUser');
    const selectAllCheckbox = document.getElementById('selectAll');
    const btnApplyBatch = document.getElementById('btnApplyBatch');

    // Reset modal form when adding a new user
    btnAddUser.addEventListener('click', () => {
        document.getElementById('modalTitle').textContent = 'Add User';
        document.getElementById('userId').value = '';
        document.getElementById('userPassword').required = true;
        userForm.reset();
    });

    // Read: Fetch user data from backend and render the table
    function fetchUsers() {
        fetch('crud.php?action=read')
        .then(response => response.json())
        .then(res => {
            if(res.status === 'success') {
                const tbody = document.getElementById('userTableBody');
                tbody.innerHTML = ''; // Clear existing rows
                
                res.data.forEach(user => {
                    const statusBadge = user.status === 'active' ? 'badge-success' : 'badge-danger';
                    const tr = document.createElement('tr');
                    
                    tr.innerHTML = `
                        <td><input type="checkbox" class="user-checkbox" value="${user.id}"></td>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td><span class="badge badge-info">${user.category}</span></td>
                        <td><span class="badge ${statusBadge}">${user.status}</span></td>
                        <td>
                            <button class="btn btn-sm btn-warning btn-edit" data-user='${JSON.stringify(user)}'>Edit</button>
                            <button class="btn btn-sm btn-danger btn-delete" data-id="${user.id}">Delete</button>
                        </td>
                    `;
                    tbody.appendChild(tr);
                });

                attachRowEventListeners();
            }
        });
    }

    // Attach events to dynamically created Edit and Delete buttons
    function attachRowEventListeners() {
        // Update: Handle Edit button click
        document.querySelectorAll('.btn-edit').forEach(button => {
            button.addEventListener('click', function() {
                const user = JSON.parse(this.getAttribute('data-user'));
                document.getElementById('modalTitle').textContent = 'Edit User';
                document.getElementById('userId').value = user.id;
                document.getElementById('userName').value = user.name;
                document.getElementById('userEmail').value = user.email;
                document.getElementById('userCategory').value = user.category;
                document.getElementById('userStatus').value = user.status;
                document.getElementById('userPassword').required = false; // Optional on edit
                $('#userModal').modal('show');
            });
        });

        // Delete: Handle Delete button click
        document.querySelectorAll('.btn-delete').forEach(button => {
            button.addEventListener('click', function() {
                if (confirm('Are you sure you want to delete this user?')) {
                    const id = this.getAttribute('data-id');
                    const formData = new FormData();
                    formData.append('action', 'delete');
                    formData.append('id', id);

                    fetch('crud.php', { method: 'POST', body: formData })
                    .then(response => response.json())
                    .then(res => {
                        if(res.status === 'success') fetchUsers(); // Auto refresh
                    });
                }
            });
        });
    }

    // Create / Update: Handle modal form submission
    userForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const formData = new FormData(this);
        formData.append('action', 'save');

        fetch('crud.php', { method: 'POST', body: formData })
        .then(response => response.json())
        .then(res => {
            if (res.status === 'success') {
                $('#userModal').modal('hide');
                fetchUsers(); // Automatically refresh dashboard after save
            } else {
                alert(res.message);
            }
        });
    });

    // Group Operation: Handle "Select All" checkbox
    selectAllCheckbox.addEventListener('change', function() {
        document.querySelectorAll('.user-checkbox').forEach(cb => cb.checked = this.checked);
    });

    // Group Operation: Apply batch actions
    btnApplyBatch.addEventListener('click', function() {
        const action = document.getElementById('batchAction').value;
        if (!action) return alert('Please select a batch action.');

        const selectedIds = Array.from(document.querySelectorAll('.user-checkbox:checked')).map(cb => cb.value);
        if (selectedIds.length === 0) return alert('No users selected.');

        if (confirm(`Are you sure you want to ${action} selected users?`)) {
            const formData = new FormData();
            formData.append('action', 'batch');
            formData.append('batchAction', action);
            formData.append('ids', JSON.stringify(selectedIds));

            fetch('crud.php', { method: 'POST', body: formData })
            .then(response => response.json())
            .then(res => {
                if (res.status === 'success') {
                    document.getElementById('selectAll').checked = false;
                    fetchUsers(); // Automatically refresh dashboard
                }
            });
        }
    });

});