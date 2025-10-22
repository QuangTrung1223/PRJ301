package controller;

import dal.WorkoutDAO;
import model.Workout;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkoutServlet", urlPatterns = {"/workout"})
public class WorkoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        WorkoutDAO dao = new WorkoutDAO();
        
        if ("add".equals(action)) {
            String workoutName = request.getParameter("workoutName");
            int duration = Integer.parseInt(request.getParameter("duration"));
            int caloriesBurned = Integer.parseInt(request.getParameter("caloriesBurned"));
            String workoutType = request.getParameter("workoutType");
            String description = request.getParameter("description");
            String bmiCategory = request.getParameter("bmiCategory");
            
            boolean success = dao.addWorkout(userId, workoutName, duration, caloriesBurned, workoutType, description, bmiCategory);
            if (success) {
                request.setAttribute("message", "Workout added successfully");
            } else {
                request.setAttribute("error", "Failed to add workout");
            }
        }
        
        List<Workout> workouts = dao.getWorkoutsByUserId(userId);
        request.setAttribute("userWorkouts", workouts);
        request.getRequestDispatcher("userdashboard.jsp#workouts").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}