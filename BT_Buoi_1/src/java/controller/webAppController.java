
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Guang Trump
 */
@WebServlet(name="webAppController", urlPatterns={"/webAppController"})
public class webAppController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet webAppController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet webAppController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
        String x1_raw = request.getParameter("x1");
        String x2_raw = request.getParameter("x2");
        String delta_raw = request.getParameter("delta");
        
        int x1 = Integer.parseInt(x1_raw);
        int x2 = Integer.parseInt(x2_raw);
        int delta = Integer.parseInt(delta_raw);
        int result = 0;
        
        
        
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
/*
    Webapp:  MÁY TÍNH PHƯƠNG TRÌNH BẬC 2
        Thiết kế UI/UX của trang web app phương trình bậc 2 như sau:
    Có logo "GÌ CŨNG ĐƯỢC" ở giữa tại header
    Content: Cho chữ đẹp xíu, cho chữ đều xíu, cho hình phong phú, màu sắc đẹp
    Thiết kế 3 ô nhập dữ liệu cho ba param: a,b,c
    Submit kết quả in ra gồm có:
    -- Kết quả gồm 2 nghiệm phân biệt: x1 = , x2 = ,
    -- Kết quả gồm 1 nghiệm kép: x1 = x2 = ....;
    -- Kết quả vô nghiệm
    
    
    validate: Nó nhập ko phải số ->> Error
    */
}
