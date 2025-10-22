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
        User currentUser = (User) session.getAttribute("account");

        // Nếu chưa login hoặc không phải admin thì chuyển hướng về login
        if (currentUser == null || !"admin".equalsIgnoreCase(currentUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu từ database
        UserDAO userDAO = new UserDAO();
        DietDAO dietDAO = new DietDAO();
        WorkoutDAO workoutDAO = new WorkoutDAO();
        ProgressDAO progressDAO = new ProgressDAO();

        List<User> allUsers = userDAO.getAllUsers();
        List<Diet> allDiets = dietDAO.getAllDiets();
        List<Workout> allWorkouts = workoutDAO.getAllWorkouts();
        List<Progress> allProgress = progressDAO.getAllProgress();

        // Ghi log để kiểm tra
        System.out.println("=== [AdminDashboardServlet] ===");
        System.out.println("Số lượng User: " + allUsers.size());
        for (User u : allUsers) {
            System.out.println("User: " + u.getUsername() + " | Role: " + u.getRole() + " | Email: " + u.getEmail());
        }

        // Truyền dữ liệu sang JSP
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allDiets", allDiets);
        request.setAttribute("allWorkouts", allWorkouts);
        request.setAttribute("allProgress", allProgress);
        // Thêm các thuộc tính thống kê
        request.setAttribute("totalUsers", allUsers.size());
        request.setAttribute("totalWorkouts", allWorkouts.size());
        request.setAttribute("totalDiets", allDiets.size());
        request.setAttribute("totalProgress", allProgress.size());

        // Chuyển tiếp đến JSP
        request.getRequestDispatcher("/admin-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}