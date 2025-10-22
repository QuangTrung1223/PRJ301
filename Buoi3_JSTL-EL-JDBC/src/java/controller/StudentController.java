package controller;

import dal.StudentDAO;
import entity.student;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "StudentController", urlPatterns = {"/student"})
public class StudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO std = new StudentDAO();
        var list = std.getAllStudents();
        
        //Khởi tạo 1 session
        HttpSession session = request.getSession();
        
        //Đưa DATA vào SESSION
        session.setAttribute("listSession", list);
        
        //request.setAttribute("list", list);
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        // Luồng logic mới:
        //Áp dụng SESSION để lưu trữ DÂT, đưa DATA lên CLOUD
        //Khi nào dùng thì lấy xuống dùng
        std.closeConnection(); 
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // For now, just reuse GET logic or add insert/update later
        doGet(request, response);
    }


    
}
