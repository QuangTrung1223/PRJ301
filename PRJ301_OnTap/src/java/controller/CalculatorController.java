package controller;

import dto.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CalculatorController", urlPatterns = {"/cal"})
public class CalculatorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String a_raw = request.getParameter("a");
        String b_raw = request.getParameter("b");
        String c_raw = request.getParameter("c");

        try {
            double a = Double.parseDouble(a_raw);
            double b = Double.parseDouble(b_raw);
            double c = Double.parseDouble(c_raw);

            Result res = solveQuadraticEquation(a, b, c);
            String msg = convertMessage((int) res.getFlag());
            DataResponse<Result> resp = new DataResponse<>(res, msg);

            // Must set attribute BEFORE forwarding
            request.setAttribute("data", resp);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Vui lòng nhập số hợp lệ!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Optional: handle POST the same as GET
    }

    // Convert code to message
    public String convertMessage(int msgCode) {
        return switch (msgCode) {
            case 1 -> "Hàm có nghiệm kép";
            case 2 -> "Hàm có 2 nghiệm phân biệt";
            case 3 -> "Hàm có 1 nghiệm";
            case 0 -> "Hàm vô nghiệm";
            default -> "Hàm vô số nghiệm";
        };
    }

    // Solve quadratic equation
    public Result solveQuadraticEquation(double a, double b, double c) {
        Result res = new Result(0, 0, 0);

        if (a == 0) {
            // Linear equation or no solution
            if (b == 0) {
                if (c == 0) {
                    res.setFlag(99); // infinite solutions
                } else {
                    res.setFlag(0); // no solution
                }
            } else {
                double x = -c / b;
                res.setFlag(3); // 1 solution
                res.setX1(x);
            }
            return res; // Important: return early
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            res.setFlag(0);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            res.setFlag(1);
            res.setX1(x);
            res.setX2(x);
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            res.setFlag(2);
            res.setX1(x1);
            res.setX2(x2);
        }

        return res;
    }
}
