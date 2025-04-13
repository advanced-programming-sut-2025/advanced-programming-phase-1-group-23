package model.Objects;

import java.util.ArrayList;

public class NPCs {
    private String name;
    private String job;
    private ArrayList<String> interests;
    private Shop shop;

    public NPCs(String name, String job, Shop shop, ArrayList<String> interests) {
        this.name = name;
        this.job = job;
        this.shop = shop;
        this.interests = interests;
    }
}
