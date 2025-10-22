package controller;

import constant.MessageConstant;
import dal.MemberDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Member;

@WebServlet(name = "AuthController", urlPatterns = {"/auth"})
public class AuthController extends HttpServlet {
    private final String LOGIN_URL = "login.jsp";
    private final String REGISTER_URL = "register.jsp";
    private final String HOME_URL = "home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến login.jsp nếu không có type
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (type.equalsIgnoreCase("LOGIN")) {
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            postLogin(request, response, email, password);
        } else if (type.equalsIgnoreCase("REGISTER")) {
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String fname = request.getParameter("fname");
            postRegister(request, response, fname, email, pass);
        } 
    }

    //Xử lý đăng ký
    protected void postRegister(HttpServletRequest request, HttpServletResponse response,
            String fname, String email, String pass)
            throws ServletException, IOException {

        MemberDAO dao = new MemberDAO();
        boolean result = dao.register(fname, email, pass, true);

        if (result) {
            // Đăng ký thành công
            response.sendRedirect(LOGIN_URL);
        } else {
            // Thất bại
            request.setAttribute("msg",MessageConstant.UNSUCCESSFULLY);
            request.getRequestDispatcher(REGISTER_URL).forward(request, response);
        }
    }

    //  Xử lý đăng nhập
    protected void postLogin(HttpServletRequest request, HttpServletResponse response,
            String email, String password)
            throws ServletException, IOException {

        MemberDAO dao = new MemberDAO();
        Member member = dao.login(email, password);

        if (member != null && member.getStatus()) {
            // Đăng nhập thành công
            request.setAttribute("member", member);
            request.getRequestDispatcher(HOME_URL).forward(request, response);
        } else {
            // Sai tài khoản hoặc bị khóa
            request.setAttribute("msg", "⚠️ Invalid email or password!");
            request.getRequestDispatcher(LOGIN_URL).forward(request, response);
        }
    }
}


//// BTVN04
//// Tạo database co 2 Table: Food và Drink (Thuộc tinh ty build)
//// Tạo Project ShopManager cho phép qun 1i CRUD Food va Drink
//Create - Read - Update - Delete

