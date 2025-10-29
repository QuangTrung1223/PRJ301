package controller;

import dal.DietDAO;
import dal.ProgressDAO;
import dal.UserDAO;
import dal.WorkoutDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Diet;
import model.Progress;
import model.User;
import model.Workout;

@WebServlet(name = "AdminDashboardServlet", urlPatterns = {"/admin-dashboard"})
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // Use actual session attributes from LoginServlet
        String sessionLogin = (String) session.getAttribute("session_login");
        String userRole = (String) session.getAttribute("user_role");

        // Redirect to login if not logged in or not admin
        if (sessionLogin == null || userRole == null || !"admin".equalsIgnoreCase(userRole)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Fetch data from database
        UserDAO userDAO = new UserDAO();
        DietDAO dietDAO = new DietDAO();
        WorkoutDAO workoutDAO = new WorkoutDAO();
        ProgressDAO progressDAO = new ProgressDAO();

        System.out.println("=== [AdminDashboardServlet] Fetching data ===");
        List<User> allUsers = userDAO.getAllUsers();
        System.out.println("Users fetched: " + allUsers.size());
        
        List<Diet> allDiets = dietDAO.getAllDiets();
        System.out.println("Diets fetched: " + allDiets.size());
        
        List<Workout> allWorkouts = workoutDAO.getAllWorkouts();
        System.out.println("Workouts fetched: " + allWorkouts.size());
        
        List<Progress> allProgress = progressDAO.getAllProgress();
        System.out.println("Progress fetched: " + allProgress.size());

        // Set data to JSP
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allDiets", allDiets);
        request.setAttribute("allWorkouts", allWorkouts);
        request.setAttribute("allProgress", allProgress);
        // Stats
        request.setAttribute("totalUsers", allUsers.size());
        request.setAttribute("totalWorkouts", allWorkouts.size());
        request.setAttribute("totalDiets", allDiets.size());
        request.setAttribute("totalProgress", allProgress.size());
        
        // Flash messages from session
        String success = (String) session.getAttribute("success");
        String error = (String) session.getAttribute("error");
        if (success != null) {
            request.setAttribute("success", success);
            session.removeAttribute("success");
        }
        if (error != null) {
            request.setAttribute("error", error);
            session.removeAttribute("error");
        }

        // Forward to JSP
        request.getRequestDispatcher("/admin-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}



