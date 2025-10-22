package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RandomController", urlPatterns = {"/random"})
public class RandomController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number_raw = request.getParameter("number");
        String message = "";
        boolean flag = false;
        try {
            int number = Integer.parseInt(number_raw);
            if (number >= 1 && number <= 5) {
                int randomNumber = (int) (Math.random() * 5) + 1;
                if (randomNumber == number) {
                    message = "You are lucky member";
                    flag = true;
                } else {
                    message = "You are unlucky member";
                }
            } else {
                message = "Please enter number from 1 -> 5";
            }
        } catch (Exception e) {
            message = "Invalid input. Please enter a valid number.";
        }

        request.setAttribute("message", message);

        if (flag) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Random number guessing servlet";
    }
}
