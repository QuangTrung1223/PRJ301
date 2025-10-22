package dal;

import entity.student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DBContext {
    // Homework: implement removeStudentBy(id):boolean, getStudentById(id):student, addStudent(id, age, name):void

    public List<student> getAllStudents() {
        String sql = "SELECT id, age, name FROM Student";
        List<student> list = new ArrayList<>();

        Connection conn = getConnection(); // <-- use the getter from DBContext
        if (conn == null) {
            System.err.println("Database connection is null.");
            return list;
        }

        try (PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                // keep using getString since your student constructor currently expects Strings
                student s = new student(
                        rs.getString("id"),
                        rs.getString("age"),
                        rs.getString("name")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        StudentDAO std = new StudentDAO();
        List<student> list = std.getAllStudents();
        for (student s : list) {
            System.out.println(s);
        }
        std.closeConnection(); // close DB connection after use
    }
}
