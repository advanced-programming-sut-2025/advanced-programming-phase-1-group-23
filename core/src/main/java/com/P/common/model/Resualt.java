package com.P.common.model;

import dev.morphia.annotations.Embedded;

@Embedded
public class Resualt {
    private boolean accept;
    private String answer;

    public Resualt() {

    }

    public Resualt(boolean accept, String answer) {
        this.accept = accept;
        this.answer = answer;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
