package controller;

import dal.DietDAO;
import model.Diet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DietServlet", urlPatterns = {"/diet"})
public class DietServlet extends HttpServlet {

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
        DietDAO dao = new DietDAO();
        
        if ("add".equals(action)) {
            String mealName = request.getParameter("mealName");
            int calories = Integer.parseInt(request.getParameter("calories"));
            String mealType = request.getParameter("mealType");
            String description = request.getParameter("description");
            String bmiCategory = request.getParameter("bmiCategory");
            
            boolean success = dao.addDiet(userId, mealName, calories, mealType, description, bmiCategory);
            if (success) {
                request.setAttribute("message", "Diet added successfully");
            } else {
                request.setAttribute("error", "Failed to add diet");
            }
        }
        
        List<Diet> diets = dao.getDietsByUserId(userId);
        request.setAttribute("userDiets", diets);
        request.getRequestDispatcher("userdashboard.jsp#diet").forward(request, response);
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