package com.michael.database;

public class User {
    private String name;
    private int id;
    private String email;
    private Integer age;

    public User(int id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public Integer getAge() {
        return age;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }


    @Override
    public String toString() {
        String user = new String(id + " " + name + " " + age + " " + email);
        return user;
    }
}
