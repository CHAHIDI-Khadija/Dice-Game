package com.app;

public class User {

    private String nom;

    private String login;

    private String password;

    private int bestScore;

    private int Score;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore() {
        if(this.Score>this.bestScore){
            this.bestScore = this.Score;
        }
    }
    public User(){

    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
