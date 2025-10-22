package model;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String role;

    public User() {}

    public User(int userID, String userName, String password, String role) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

   
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
