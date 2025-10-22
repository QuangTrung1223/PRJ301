/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Guang Trump
 */
public class User {
    String fullname, username, password, gmail;

    public User(String fullname, String username, String password, String gmail) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return "User{" + "fullname=" + fullname + ", username=" + username + ", password=" + password + ", gmail=" + gmail + '}';
    }
    
    
}
