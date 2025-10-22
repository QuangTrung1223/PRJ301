///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
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
///**
// *
// * @author Guang Trump
// */
//@WebServlet(name = "LoginController", urlPatterns = {"/login"})
//public class LoginController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String email = request.getParameter("email");
//        String password = request.getParameter("pass");
//
//        MemberDAO dao = new MemberDAO();
//        Member member = dao.login(email, password);
//
//        if (member != null && member.getStatus()) {
//            request.setAttribute("member", member);
//            request.getRequestDispatcher("home.jsp").forward(request, response);
//        } else {
//            response.sendRedirect("banned.jsp");
//        }
//
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
