//package ru.kata.spring.boot_security.demo.models;
//
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "username")
//    @NotEmpty(message = "Not valid name!")
//    @Size(min = 1, max = 50, message = "Enter correct value bet.1 and 15 chars!")
//    private String name;
//
//    @Column(name = "userage")
//    @Min(value = 0, message = "Not correct age!")
//    private int age;
//
//    @Column(name = "useremail")
//    @Email(message = "Not correct email!")
//    @NotEmpty(message = "Not empty email!")
//    private String email;
//
//    @Column(name = "password")
//    private String password;
//
//    public User() {
//    }
//
//    public User(String name, int age, String email) {
//        this.name = name;
//        this.age = age;
//        this.email = email;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
//}