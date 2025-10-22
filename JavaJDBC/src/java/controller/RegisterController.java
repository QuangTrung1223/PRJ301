///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller;
//
//import dal.MemberDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import model.Member;
//
//@WebServlet(name="RegisterController", urlPatterns={"/register"})
//public class RegisterController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        
//    } 
//
//  
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//      String email = request.getParameter("email");
//      String pass = request.getParameter("pass");
//      String fname = request.getParameter("fname");
//      
//      MemberDAO dao = new MemberDAO();
//     if(dao.register(email, pass, fname, true)){
//         response.sendRedirect("login.jsp");
//     } else{
//         request.setAttribute("msg", "REGISTER SUCCESSFULLY");
//         request.getRequestDispatcher("register.jsp").forward(request, response);
//     }
//     
//    }
//
//   
//
//}
