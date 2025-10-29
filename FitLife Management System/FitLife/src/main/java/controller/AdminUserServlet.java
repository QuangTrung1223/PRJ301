package controller;

import dal.UserDAO;
import model.User;
import utils.PasswordHasher;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AdminUserServlet", urlPatterns = {"/admin/add-user", "/admin/update-user", "/admin/delete-user"})
public class AdminUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("session_login");
        String userRole = (String) session.getAttribute("user_role");
        
        // Admin check
        if (sessionLogin == null || userRole == null || !"admin".equalsIgnoreCase(userRole)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        String servletPath = request.getServletPath();
        UserDAO userDAO = new UserDAO();
        
        try {
            switch (servletPath) {
                case "/admin/add-user":
                    addUser(request, response, userDAO, session);
                    break;
                case "/admin/update-user":
                    updateUser(request, response, userDAO, session);
                    break;
                case "/admin/delete-user":
                    deleteUser(request, response, userDAO, session);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin-dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "An error occurred: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin-dashboard");
        }
    }
    
    private void addUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO, HttpSession session) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        
        // Validate unique username/email
        if (userDAO.getUserByUsername(username) != null) {
            session.setAttribute("error", "Username already exists!");
            response.sendRedirect(request.getContextPath() + "/admin-dashboard");
            return;
        }
        
        if (userDAO.getUserByEmail(email) != null) {
            session.setAttribute("error", "Email already exists!");
            response.sendRedirect(request.getContextPath() + "/admin-dashboard");
            return;
        }
        
        // Create new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(PasswordHasher.hashPassword(password));
        newUser.setRole(role);
        newUser.setStatus("active");
        newUser.setGender(gender);
        newUser.setAge(age);
        newUser.setHeight(height);
        newUser.setWeight(weight);
        newUser.setJoinDate(Date.valueOf(LocalDate.now()));
        
        boolean success = userDAO.addUser(newUser);
        
        if (success) {
            session.setAttribute("success", "User added successfully!");
        } else {
            session.setAttribute("error", "Failed to add user!");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin-dashboard");
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO, HttpSession session) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));

        User existingUser = userDAO.getUserById(id);
        if (existingUser == null) {
            session.setAttribute("error", "User not found!");
            response.sendRedirect(request.getContextPath() + "/admin-dashboard");
            return;
        }

        existingUser.setUsername(username);
        existingUser.setEmail(email);
        if (password != null && !password.isEmpty()) {
            existingUser.setPassword(PasswordHasher.hashPassword(password));
        }
        existingUser.setRole(role);
        existingUser.setStatus(status);
        existingUser.setGender(gender);
        existingUser.setAge(age);
        existingUser.setHeight(height);
        existingUser.setWeight(weight);

        if (userDAO.updateUser(existingUser)) {
            session.setAttribute("success", "User updated successfully!");
        } else {
            session.setAttribute("error", "Failed to update user!");
        }
        response.sendRedirect(request.getContextPath() + "/admin-dashboard");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO, HttpSession session) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (userDAO.deleteUser(id)) {
            session.setAttribute("success", "User deleted successfully!");
        } else {
            session.setAttribute("error", "Failed to delete user!");
        }
        response.sendRedirect(request.getContextPath() + "/admin-dashboard");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
