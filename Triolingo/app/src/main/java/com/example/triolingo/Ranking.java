package com.example.triolingo;

public class Ranking {

    public String name;
    public int score;

    public Ranking(){}

    public Ranking(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
}
