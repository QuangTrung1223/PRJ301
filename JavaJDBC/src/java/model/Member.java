/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Guang Trump
 */
public class Member {
    private int MemberID;
    private int RoleID;
    private String FullName;
    private String Email;
    private String password;
    private boolean Status; // thêm thuộc tính Status

    public Member() {
    }

    // Constructor đầy đủ
    public Member(int MemberID, int RoleID, String FullName, String Email, String password, boolean Status) {
        this.MemberID = MemberID;
        this.RoleID = RoleID;
        this.FullName = FullName;
        this.Email = Email;
        this.password = password;
        this.Status = Status;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int MemberID) {
        this.MemberID = MemberID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "MemberID=" + MemberID +
                ", RoleID=" + RoleID +
                ", FullName='" + FullName + '\'' +
                ", Email='" + Email + '\'' +
                ", password='" + password + '\'' +
                ", Status=" + Status +
                '}';
    }
}
