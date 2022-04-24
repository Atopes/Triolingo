package com.example.triolingo;

public class User {

    public String name, email, gender;
    public int score;

    public User(){}

    public User(String name, String email, String gender, int score){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.score = score;
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

    public int getScore() {
        return score;
    }
}
