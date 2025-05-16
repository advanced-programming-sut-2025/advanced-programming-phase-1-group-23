package src.main.java.model.Objects;

import src.main.java.model.NPC.Pair;

public class Trade {
    private final String firstPlayer;
    private final String secondPlayer;
    private final String type;
    private final Pair request;
    private final Pair offer;
    private final int price;

    public Trade(String firstPlayer, String secondPlayer, String type, Pair request, Pair offer, int price) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.type = type;
        this.request = request;
        this.offer = offer;
        this.price = price;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public String getType() {
        return type;
    }

    public Pair getOffer() {
        return offer;
    }

    public Pair getRequest() {
        return request;
    }

    public int getPrice() {
        return price;
    }
}
