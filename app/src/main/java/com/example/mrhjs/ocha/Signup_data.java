package com.example.mrhjs.ocha;

public class Signup_data {
    String id;
    String password;
    String password_question;
    String answer;
    String email;
    int gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_question() {
        return password_question;
    }

    public void setPassword_question(String password_question) {
        this.password_question = password_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Signup_data(){}
}

