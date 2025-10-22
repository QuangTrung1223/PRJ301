/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Guang Trump
 */
@WebServlet(urlPatterns={"/CongController"})
public class CongController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } 
    private final String USERNAME = "admin";
    private final String PASSWORD = "123";
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         String message = "";
         
         if(username.isEmpty() || username.isBlank() || password.isEmpty() || password.isBlank()){
             message = "Please enter all field of form";
         } else if(username.equalsIgnoreCase(USERNAME) && password.equalsIgnoreCase(PASSWORD)){
             PrintWriter out = response.getWriter();
             out.println("Login Successfully");
             return;
         } else {
             message = "Login Failed";
         }
         ArrayList<String> userList = new ArrayList();
         userList.add("SonNV01");
         userList.add("FatherNV02");
         userList.add("MotherNV03");
         request.setAttribute("list_user", userList);
         request.setAttribute("MessageError", message);
         request.getRequestDispatcher("index.jsp").forward(request, response);
         
         if(message == null){
             System.out.println("msg");
         }
         
         for( String tay : userList){
             
         }
        
       
       
    }

}
