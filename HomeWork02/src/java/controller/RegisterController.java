/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import constant.MessageConstant;
import constant.NameAttributeConstant;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name="RegisterController", urlPatterns={"/process"})
public class RegisterController extends HttpServlet {
 
    
    
    private final String PROFILE_PAGE = "profile.jsp";
    private final String REGISTER_PAGE = "register.jsp";
    
    
    private final String USERNAME = "username";
    private final String FIRSTNAME = "fname";
    private final String PASSWORD= "pwd";
    private final String GMAIL = "gmail";
    boolean flag = false;
   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter(USERNAME);
        String fname = request.getParameter(USERNAME);
        String pwd = request.getParameter(PASSWORD);
        String gmail = request.getParameter(GMAIL);
        
        if(username.isBlank() || username.isEmpty()
        || fname.isBlank() || fname.isEmpty()
        || pwd.isBlank() || pwd.isEmpty()
        || gmail.isBlank() || gmail.isEmpty()) {
            request.setAttribute(NameAttributeConstant.ERROR_ATTRIBUTE, MessageConstant.ERROR_MESSAGE);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
            
        } else {
            User u = new User(fname, gmail, pwd, username);
            request.setAttribute(NameAttributeConstant.USER_ATTRIBUTE, u);
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
            
        } 
            
            
        
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
