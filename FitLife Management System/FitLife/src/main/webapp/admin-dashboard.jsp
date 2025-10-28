<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - User Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="admin-body">
    <div class="admin-dashboard-container">
        <!-- Top Header Bar -->
        <header class="admin-top-bar">
            <div class="admin-welcome-section">
                <h1 class="admin-title">
                    <i class="fas fa-user-shield"></i> Admin Dashboard
                </h1>
                <p class="admin-subtitle">Manage users, workouts, diets, and progress</p>
            </div>
            <div class="admin-actions-section">
                <button class="admin-action-btn refresh">
                    <i class="fas fa-sync-alt"></i> Refresh Data
                </button>
                <button class="admin-action-btn export">
                    <i class="fas fa-file-export"></i> Export Report
                </button>
                <span class="admin-time">
                    <i class="fas fa-clock"></i> <span id="current-time"></span>
                </span>
            </div>
        </header>

        <!-- Main Grid Layout -->
        <div class="admin-main-grid">
            <!-- Sidebar -->
            <aside class="admin-sidebar">
                <div class="sidebar-section">
                    <h3 class="sidebar-title">Quick Stats</h3>
                    <div class="quick-stats">
                        <div class="quick-stat-item">
                            <span class="stat-icon users"><i class="fas fa-users"></i></span>
                            <div class="stat-info">
                                <span class="stat-number">${totalUsers}</span>
                                <span class="stat-label">Total Users</span>
                            </div>
                        </div>
                        <div class="quick-stat-item">
                            <span class="stat-icon workouts"><i class="fas fa-dumbbell"></i></span>
                            <div class="stat-info">
                                <span class="stat-number">${totalWorkouts}</span>
                                <span class="stat-label">Total Workouts</span>
                            </div>
                        </div>
                        <div class="quick-stat-item">
                            <span class="stat-icon diets"><i class="fas fa-utensils"></i></span>
                            <div class="stat-info">
                                <span class="stat-number">${totalDiets}</span>
                                <span class="stat-label">Total Diets</span>
                            </div>
                        </div>
                        <div class="quick-stat-item">
                            <span class="stat-icon progress"><i class="fas fa-chart-line"></i></span>
                            <div class="stat-info">
                                <span class="stat-number">${totalProgress}</span>
                                <span class="stat-label">Progress Records</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="sidebar-section">
                    <h3 class="sidebar-title">Navigation</h3>
                    <div class="admin-nav">
                        <button class="nav-item active" data-tab="users"><i class="fas fa-users"></i> User Management</button>
                        <button class="nav-item" data-tab="workouts"><i class="fas fa-dumbbell"></i> Workouts</button>
                        <button class="nav-item" data-tab="diets"><i class="fas fa-utensils"></i> Diets</button>
                        <button class="nav-item" data-tab="progress"><i class="fas fa-chart-line"></i> Progress</button>
                    </div>
                </div>
            </aside>

            <!-- Content Area -->
            <main class="admin-content-area">
                <div class="content-header">
                    <h2 id="content-title"><i class="fas fa-users"></i> User Management</h2>
                    <div class="content-actions">
                        <div class="search-container">
                            <i class="fas fa-search"></i>
                            <input type="text" placeholder="Search users..." id="user-search">
                        </div>
                        <!-- Thêm nút Add User -->
                        <button class="admin-action-btn add" onclick="openAddUserModal()">
                            <i class="fas fa-plus"></i> Add User
                        </button>
                        
                    </div>
                </div>

                <!-- User Data Table -->
                <div class="data-table-container" id="users-tab">
                    <div class="table-header">
                        <div class="table-title">
                            <i class="fas fa-table"></i> All Users
                        </div>
                        <div class="table-filters">
                            <select class="filter-select" id="role-filter">
                                <option value="all">All Roles</option>
                                <option value="user">User</option>
                                <option value="admin">Admin</option>
                            </select>
                        </div>
                    </div>
                    <table class="modern-table">
                        <thead>
                            <tr class="table-header-row">
                                <th class="col">User</th>
                                <th class="col">ID</th>
                                <th class="col">Email</th>
                                <th class="col">Role</th>
                                <th class="col">Join Date</th>
                                <th class="col">Status</th>
                                <!-- Thêm cột mới cho Gender, Age, Height, Weight -->
                                <th class="col">Gender</th>
                                <th class="col">Age</th>
                                <th class="col">Height</th>
                                <th class="col">Weight</th>
                                <th class="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="table-body">
                            <c:choose>
                                <c:when test="${not empty allUsers}">
                                    <c:forEach var="user" items="${allUsers}">
                                        <tr class="table-row">
                                            <td class="col user-cell">
                                                <div class="user-avatar-small">
                                                    <i class="fas fa-user"></i>
                                                </div>
                                                <div class="user-details">
                                                    <span class="username">${user.username}</span>
                                                </div>
                                            </td>
                                            <td class="col id-text">${user.id}</td>
                                            <td class="col email-text">${user.email}</td>
                                            <td class="col">
                                                <span class="role-badge ${user.role.toLowerCase()}">${user.role}</span>
                                            </td>
                                            <td class="col date-text">
                                                <fmt:formatDate value="${user.joinDate}" pattern="dd-MM-yyyy"/>
                                            </td>
                                            <td class="col">
                                                <span class="role-badge ${user.status.toLowerCase()}">${user.status}</span>
                                            </td>
                                            <!-- Thêm td mới cho Gender, Age, Height, Weight -->
                                            <td class="col">${user.gender}</td>
                                            <td class="col">${user.age}</td>
                                            <td class="col">${user.height}</td>
                                            <td class="col">${user.weight}</td>
                                            <td class="col action-buttons">
                                                <button class="action-btn view" onclick="viewUser('${user.id}')">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <button class="action-btn edit" onclick="editUser('${user.id}')">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                                <button class="action-btn delete" onclick="deleteUser('${user.id}')">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="11" class="empty-state"> <!-- Điều chỉnh colspan từ 7 thành 11 -->
                                            <i class="fas fa-users-slash"></i>
                                            <h3>No Users Found</h3>
                                            <p>It looks like there are no users to display. Try adding a new user or adjusting your filters.</p>
                                            
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- Workouts Tab (Placeholder) -->
                <div class="data-table-container" id="workouts-tab" style="display: none;">
                    <div class="table-header">
                        <div class="table-title">
                            <i class="fas fa-dumbbell"></i> All Workouts
                        </div>
                    </div>
                    <table class="modern-table">
                        <thead>
                            <tr class="table-header-row">
                                <th class="col">Workout ID</th>
                                <th class="col">Name</th>
                                <th class="col">Description</th>
                                <th class="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="table-body">
                            <c:choose>
                                <c:when test="${not empty allWorkouts}">
                                    <c:forEach var="workout" items="${allWorkouts}">
                                        <tr class="table-row">
                                            <td class="col id-text">${workout.id}</td>
                                            <td class="col">${workout.name}</td>
                                            <td class="col">${workout.description}</td>
                                            <td class="col action-buttons">
                                                <button class="action-btn view" onclick="viewWorkout('${workout.id}')">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <button class="action-btn edit" onclick="editWorkout('${workout.id}')">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                                <button class="action-btn delete" onclick="deleteWorkout('${workout.id}')">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="empty-state">
                                            <i class="fas fa-dumbbell"></i>
                                            <h3>No Workouts Found</h3>
                                            <p>It looks like there are no workouts to display.</p>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- Diets Tab (Placeholder) -->
                <div class="data-table-container" id="diets-tab" style="display: none;">
                    <div class="table-header">
                        <div class="table-title">
                            <i class="fas fa-utensils"></i> All Diets
                        </div>
                    </div>
                    <table class="modern-table">
                        <thead>
                            <tr class="table-header-row">
                                <th class="col">Diet ID</th>
                                <th class="col">Name</th>
                                <th class="col">Description</th>
                                <th class="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="table-body">
                            <c:choose>
                                <c:when test="${not empty allDiets}">
                                    <c:forEach var="diet" items="${allDiets}">
                                        <tr class="table-row">
                                            <td class="col id-text">${diet.id}</td>
                                            <td class="col">${diet.name}</td>
                                            <td class="col">${diet.description}</td>
                                            <td class="col action-buttons">
                                                <button class="action-btn view" onclick="viewDiet('${diet.id}')">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <button class="action-btn edit" onclick="editDiet('${diet.id}')">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                                <button class="action-btn delete" onclick="deleteDiet('${diet.id}')">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="empty-state">
                                            <i class="fas fa-utensils"></i>
                                            <h3>No Diets Found</h3>
                                            <p>It looks like there are no diets to display.</p>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- Progress Tab (Placeholder) -->
                <div class="data-table-container" id="progress-tab" style="display: none;">
                    <div class="table-header">
                        <div class="table-title">
                            <i class="fas fa-chart-line"></i> All Progress
                        </div>
                    </div>
                    <table class="modern-table">
                        <thead>
                            <tr class="table-header-row">
                                <th class="col">Progress ID</th>
                                <th class="col">User ID</th>
                                <th class="col">Date</th>
                                <th class="col">Weight</th>
                                <th class="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="table-body">
                            <c:choose>
                                <c:when test="${not empty allProgress}">
                                    <c:forEach var="progress" items="${allProgress}">
                                        <tr class="table-row">
                                            <td class="col id-text">${progress.id}</td>
                                            <td class="col">${progress.userId}</td>
                                            <td class="col date-text">
                                                <fmt:formatDate value="${progress.date}" pattern="dd-MM-yyyy"/>
                                            </td>
                                            <td class="col">${progress.weight}</td>
                                            <td class="col action-buttons">
                                                <button class="action-btn view" onclick="viewProgress('${progress.id}')">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <button class="action-btn edit" onclick="editProgress('${progress.id}')">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                                <button class="action-btn delete" onclick="deleteProgress('${progress.id}')">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5" class="empty-state">
                                            <i class="fas fa-chart-line"></i>
                                            <h3>No Progress Records Found</h3>
                                            <p>It looks like there are no progress records to display.</p>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </main>
        </div>
    </div>

    <!-- Modal for Adding User -->
    <div class="modal" id="add-user-modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Add New User</h2>
                <span class="close" onclick="closeModal('add-user-modal')">&times;</span>
            </div>
            <div class="modal-body">
                <form id="add-user-form" action="${pageContext.request.contextPath}/admin/add-user" method="post">
                    <div class="input-field full-width">
                        <label><i class="fas fa-user"></i> Username</label>
                        <input type="text" name="username" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-envelope"></i> Email</label>
                        <input type="email" name="email" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-lock"></i> Password</label>
                        <input type="password" name="password" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-user-tag"></i> Role</label>
                        <select name="role" required>
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>
                    <div class="modal-actions">
                        <button type="button" class="btn-secondary" onclick="closeModal('add-user-modal')">Cancel</button>
                        <button type="submit" class="btn-primary">Add User</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Thêm Modal for Viewing User -->
    <div class="modal" id="view-user-modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>View User Details</h2>
                <span class="close" onclick="closeModal('view-user-modal')">&times;</span>
            </div>
            <div class="modal-body">
                <div class="user-detail">
                    <label><i class="fas fa-user"></i> Username:</label>
                    <span id="view-username"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-envelope"></i> Email:</label>
                    <span id="view-email"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-user-tag"></i> Role:</label>
                    <span id="view-role"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-calendar-alt"></i> Join Date:</label>
                    <span id="view-joinDate"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-check-circle"></i> Status:</label>
                    <span id="view-status"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-venus-mars"></i> Gender:</label>
                    <span id="view-gender"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-birthday-cake"></i> Age:</label>
                    <span id="view-age"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-ruler-vertical"></i> Height:</label>
                    <span id="view-height"></span>
                </div>
                <div class="user-detail">
                    <label><i class="fas fa-weight"></i> Weight:</label>
                    <span id="view-weight"></span>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn-secondary" onclick="closeModal('view-user-modal')">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Thêm Modal for Editing User -->
    <div class="modal" id="edit-user-modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Edit User</h2>
                <span class="close" onclick="closeModal('edit-user-modal')">&times;</span>
            </div>
            <div class="modal-body">
                <form id="edit-user-form" action="${pageContext.request.contextPath}/admin/update-user" method="post">
                    <input type="hidden" name="id" id="edit-id">
                    <div class="input-field full-width">
                        <label><i class="fas fa-user"></i> Username</label>
                        <input type="text" name="username" id="edit-username" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-envelope"></i> Email</label>
                        <input type="email" name="email" id="edit-email" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-lock"></i> Password (leave blank to keep current)</label>
                        <input type="password" name="password" id="edit-password">
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-user-tag"></i> Role</label>
                        <select name="role" id="edit-role" required>
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-check-circle"></i> Status</label>
                        <select name="status" id="edit-status" required>
                            <option value="active">Active</option>
                            <option value="inactive">Inactive</option>
                            <!-- Thêm tùy chọn status khác nếu cần -->
                        </select>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-venus-mars"></i> Gender</label>
                        <select name="gender" id="edit-gender" required>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-birthday-cake"></i> Age</label>
                        <input type="number" name="age" id="edit-age" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-ruler-vertical"></i> Height</label>
                        <input type="number" step="0.01" name="height" id="edit-height" required>
                    </div>
                    <div class="input-field full-width">
                        <label><i class="fas fa-weight"></i> Weight</label>
                        <input type="number" step="0.01" name="weight" id="edit-weight" required>
                    </div>
                    <div class="modal-actions">
                        <button type="button" class="btn-secondary" onclick="closeModal('edit-user-modal')">Cancel</button>
                        <button type="submit" class="btn-primary">Update User</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Thêm biến JS để lưu dữ liệu users từ server
        var users = [
            <c:forEach var="user" items="${allUsers}" varStatus="status">
                {
                    id: '${user.id}',
                    username: '${user.username}',
                    email: '${user.email}',
                    role: '${user.role}',
                    joinDate: '<fmt:formatDate value="${user.joinDate}" pattern="dd-MM-yyyy"/>',
                    status: '${user.status}',
                    gender: '${user.gender}',
                    age: '${user.age}',
                    height: '${user.height}',
                    weight: '${user.weight}'
                }${not status.last ? ',' : ''}
            </c:forEach>
        ];

        // Update current time
        function updateTime() {
            const now = new Date();
            const options = { hour: '2-digit', minute: '2-digit', hour12: true };
            document.getElementById('current-time').textContent = now.toLocaleTimeString('en-US', options);
        }
        setInterval(updateTime, 1000);
        updateTime();

        // Modal functions
        function openAddUserModal() {
            document.getElementById('add-user-modal').style.display = 'block';
        }

        function closeModal(modalId) {
            document.getElementById(modalId).style.display = 'none';
        }

        // Placeholder functions for actions
        function viewUser(userId) {
            const user = users.find(u => u.id === userId);
            if (user) {
                document.getElementById('view-username').textContent = user.username;
                document.getElementById('view-email').textContent = user.email;
                document.getElementById('view-role').textContent = user.role;
                document.getElementById('view-joinDate').textContent = user.joinDate;
                document.getElementById('view-status').textContent = user.status;
                document.getElementById('view-gender').textContent = user.gender;
                document.getElementById('view-age').textContent = user.age;
                document.getElementById('view-height').textContent = user.height;
                document.getElementById('view-weight').textContent = user.weight;
                document.getElementById('view-user-modal').style.display = 'block';
            } else {
                alert('User not found');
            }
            // Implement view user logic
        }

        function editUser(userId) {
            const user = users.find(u => u.id === userId);
            if (user) {
                document.getElementById('edit-id').value = user.id;
                document.getElementById('edit-username').value = user.username;
                document.getElementById('edit-email').value = user.email;
                document.getElementById('edit-role').value = user.role.toLowerCase();
                document.getElementById('edit-status').value = user.status.toLowerCase();
                document.getElementById('edit-gender').value = user.gender;
                document.getElementById('edit-age').value = user.age;
                document.getElementById('edit-height').value = user.height;
                document.getElementById('edit-weight').value = user.weight;
                document.getElementById('edit-password').value = ''; // Để trống để không thay đổi
                document.getElementById('edit-user-modal').style.display = 'block';
            } else {
                alert('User not found');
            }
            // Implement edit user logic
        }

        function deleteUser(userId) {
            if (confirm('Are you sure you want to delete user ' + userId + '?')) {
                // Implement delete user logic
                window.location.href = '${pageContext.request.contextPath}/admin/delete-user?id=' + userId;
                alert('User ' + userId + ' deleted');
            }
        }

        function viewWorkout(workoutId) {
            alert('View workout: ' + workoutId);
        }

        function editWorkout(workoutId) {
            alert('Edit workout: ' + workoutId);
        }

        function deleteWorkout(workoutId) {
            if (confirm('Are you sure you want to delete workout ' + workoutId + '?')) {
                alert('Workout ' + workoutId + ' deleted');
            }
        }

        function viewDiet(dietId) {
            alert('View diet: ' + dietId);
        }

        function editDiet(dietId) {
            alert('Edit diet: ' + dietId);
        }

        function deleteDiet(dietId) {
            if (confirm('Are you sure you want to delete diet ' + dietId + '?')) {
                alert('Diet ' + dietId + ' deleted');
            }
        }

        function viewProgress(progressId) {
            alert('View progress: ' + progressId);
        }

        function editProgress(progressId) {
            alert('Edit progress: ' + progressId);
        }

        function deleteProgress(progressId) {
            if (confirm('Are you sure you want to delete progress ' + progressId + '?')) {
                alert('Progress ' + progressId + ' deleted');
            }
        }

        // Search and filter functionality for users
        document.getElementById('user-search').addEventListener('input', function(e) {
            const searchText = e.target.value.toLowerCase();
            const rows = document.querySelectorAll('#users-tab .table-row');
            rows.forEach(row => {
                const username = row.querySelector('.username').textContent.toLowerCase();
                const email = row.querySelector('.email-text').textContent.toLowerCase();
                row.style.display = (username.includes(searchText) || email.includes(searchText)) ? '' : 'none';
            });
        });

        document.getElementById('role-filter').addEventListener('change', function(e) {
            const role = e.target.value.toLowerCase();
            const rows = document.querySelectorAll('#users-tab .table-row');
            rows.forEach(row => {
                const userRole = row.querySelector('.role-badge').textContent.toLowerCase();
                row.style.display = (role === 'all' || userRole === role) ? '' : 'none';
            });
        });

        // Tab switching functionality
        document.querySelectorAll('.nav-item').forEach(button => {
            button.addEventListener('click', function() {
                // Remove active class from all buttons
                document.querySelectorAll('.nav-item').forEach(btn => btn.classList.remove('active'));
                // Add active class to clicked button
                this.classList.add('active');

                // Hide all tabs
                document.querySelectorAll('.data-table-container').forEach(tab => {
                    tab.style.display = 'none';
                });

                // Show selected tab
                const tabId = this.getAttribute('data-tab') + '-tab';
                document.getElementById(tabId).style.display = 'block';

                // Update content title
                const titles = {
                    'users': '<i class="fas fa-users"></i> User Management',
                    'workouts': '<i class="fas fa-dumbbell"></i> Workout Management',
                    'diets': '<i class="fas fa-utensils"></i> Diet Management',
                    'progress': '<i class="fas fa-chart-line"></i> Progress Management'
                };
                document.getElementById('content-title').innerHTML = titles[this.getAttribute('data-tab')];
            });
        });
    </script>
</body>
</html>