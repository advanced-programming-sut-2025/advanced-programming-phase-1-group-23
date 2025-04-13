package model;

import java.util.ArrayList;

public class Shop {
    private String shopName;
    private NPCs npcs;
    private ArrayList<String> products;

    public Shop(NPCs npcs, ArrayList<String> products, String shopName) {
        this.npcs = npcs;
        this.products = products;
        this.shopName = shopName;
    }

    public String shopMenu(Shop shop) {

    }
}
