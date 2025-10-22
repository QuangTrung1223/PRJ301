/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * studentDTO( Data transfer ofject)
 */
// Da co entity student de luu tru data
// data tu database > dung JDBC ket noi DTB -> truy van -> class DAO nam
// trong package dal de truy van
public class student {
    private String id, age;
    private String name;

    public student(String id, String age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "student{" + "id=" + id + ", age=" + age + ", name=" + name + '}';
    }
    
    
    
    
}
