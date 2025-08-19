package com.P.Client.model;

public class Payam {
    private final String matn;
    private final boolean tag;

    public Payam(String matn, boolean tag) {
        this.matn = matn;
        this.tag = tag;
    }

    public String getMatn() {
        return matn;
    }

    public boolean isTag() {
        return tag;
    }
}
