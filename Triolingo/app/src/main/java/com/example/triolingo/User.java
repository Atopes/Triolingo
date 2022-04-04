package com.example.triolingo;

public class User {

    public String name, email, gender;

    public User(){}

    public User(String name, String email,String gender){
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
