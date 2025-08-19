package com.P.common.model.enums;

public enum SecurityQuestion {
    FIRST_TEACHER("What was the name of your first teacher?"),
    FAVORITE_QUSTION("What is the name of your favorite book?");

    private String question;
    SecurityQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String string(){
        return question;
    }
}
