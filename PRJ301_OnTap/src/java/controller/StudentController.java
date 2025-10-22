/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.StudentDAO;

import entity.Student;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="StudentController", urlPatterns={"/manager"})
public class StudentController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //Lấy data bằng hàm doGet và truyển qua home.jsp
        StudentDAO s = new StudentDAO();
        var data = s.getAllStudents();
        request.setAttribute("data", data);     
        request.getRequestDispatcher("home.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    StudentDAO stDao = new StudentDAO();
    String msg = "";
    String id_raw = request.getParameter("id");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String gender = request.getParameter("gender");
    String date = request.getParameter("dob");
    
        try {
            int id = Integer.parseInt(id_raw);
            if(stDao.getStudentByID(id) != null){
                msg = "Student Existed";
                request.setAttribute(msg, "msg");
                request.getRequestDispatcher("createstd.jsp").forward(request, response);
            } else {
                stDao.createStudent(new Student(id, fname, lname, gender, date));
                msg = "Added";
                request.setAttribute(msg, "msg");
                response.sendRedirect("manager");
            }
            
        } catch (Exception e) {
        }
        
    }

   
}
