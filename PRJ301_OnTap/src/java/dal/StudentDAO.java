package dal;

import entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DBContext {

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        Connection c = getConnection();
        if (c == null) {
            System.err.println("Database connection is null.");
            return list;
        }

        try (PreparedStatement stm = c.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("StudentID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("DateOfBirth"),
                        rs.getString("Gender")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Student by ID
    public Student getStudentByID(int id) {
        String sql = "SELECT * FROM Student WHERE StudentID = ?";
        Connection c = getConnection();
        if (c == null) return null;

        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("StudentID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("DateOfBirth"),
                            rs.getString("Gender")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Create new Student
    public void createStudent(Student s) {
        String sql = "INSERT INTO Student (FirstName, LastName, DateOfBirth, Gender) VALUES (?, ?, ?, ?)";
        Connection c = getConnection();
        if (c == null) {
            System.err.println("Database connection is null.");
            return;
        }

        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(s.getStudentID()));
            stm.setString(2, s.getFirstName());
            stm.setString(3, s.getLastName());
            stm.setString(4, s.getDob());
            stm.setString(5, s.getGender());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update existing Student
    public void updateStudent(Student s) {
        String sql = "UPDATE Student SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ? WHERE StudentID = ?";
        Connection c = getConnection();
        if (c == null) {
            System.err.println("Database connection is null.");
            return;
        }

        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setString(1, s.getFirstName());
            stm.setString(2, s.getLastName());
            stm.setString(3, s.getDob());
            stm.setString(4, s.getGender());
            stm.setInt(5, Integer.parseInt(s.getStudentID()));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Student by ID
    public boolean deleteStudentByID(int id) {
        if (getStudentByID(id) == null) {
            System.err.println("Student not found!");
            return false;
        }

        String sql = "DELETE FROM Student WHERE StudentID = ?";
        Connection c = getConnection();
        if (c == null) return false;

        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Get all students
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            System.out.println(s);
        }

        // Example: Create new Student
        // dao.createStudent(new Student(null, "John", "Doe", "2000-01-01", "Male"));

        // Example: Update Student
        // dao.updateStudent(new Student("1", "Jane", "Doe", "2000-01-02", "Female"));

        // Example: Delete Student
        // dao.deleteStudentByID(1);

        dao.closeConnection();
    }
}
