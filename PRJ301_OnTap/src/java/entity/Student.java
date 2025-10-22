/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * studentDTO( Data transfer ofject)
 */
// Da co entity Student de luu tru data
// data tu database > dung JDBC ket noi DTB -> truy van -> class DAO nam
// trong package dal de truy van
public class Student {
    private String StudentID; //1 nam , 0 nu
    private String FirstName, LastName, Dob, gender;

    public Student(String StudentID, String FirstName, String LastName, String Dob, String gender) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Dob = Dob;
        this.gender = gender;
    }

    public Student(int id, String fname, String lname, String gender, String date) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

   
    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String doB) {
        this.Dob = doB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "student{" + "StudentID=" + StudentID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Dob=" + Dob + ", gender=" + gender + '}';
    }

    

   
    
    
}
