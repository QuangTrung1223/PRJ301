package controller;

import dao.UserDAO;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create": showNewForm(request, response); break;
            case "insert": insertUser(request, response); break;
            case "delete": deleteUser(request, response); break;
            case "edit": showEditForm(request, response); break;
            case "update": updateUser(request, response); break;
            case "search": searchUser(request, response); break;
            default: listUser(request, response); break;
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> list = userDAO.selectAllUsers();
        request.setAttribute("listUser", list);
        RequestDispatcher rd = request.getRequestDispatcher("/user/userList.jsp");
        rd.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/user/createUser.jsp");
        rd.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User u = new User(0,
                request.getParameter("username"),
                request.getParameter("email"),
                request.getParameter("country"),
                request.getParameter("role"),
                "1".equals(request.getParameter("status")),
                request.getParameter("password"));
        userDAO.insertUser(u);
        response.sendRedirect("users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("users");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User u = userDAO.selectUser(id);
        request.setAttribute("user", u);
        RequestDispatcher rd = request.getRequestDispatcher("/user/editUser.jsp");
        rd.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User u = new User(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("username"),
                request.getParameter("email"),
                request.getParameter("country"),
                request.getParameter("role"),
                "1".equals(request.getParameter("status")),
                request.getParameter("password"));
        userDAO.updateUser(u);
        response.sendRedirect("users");
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<User> list = userDAO.searchUsers(keyword);
        request.setAttribute("listUser", list);
        RequestDispatcher rd = request.getRequestDispatcher("/user/userList.jsp");
        rd.forward(request, response);
    }
}
