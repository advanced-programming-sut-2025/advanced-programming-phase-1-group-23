package com.P.Server.model;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.User;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Lobby {
    private String name;
    private boolean isPrivate;
    private boolean isVisible;
    private String password;
    private int peopleCounter;
    private ArrayList<User> players;
    private User admin;
    private int ID;
    private final long startTime;
    private Game game ;

    private transient Timer emptyTimer;
    private transient boolean closingScheduled = false;

    public Lobby(String name, boolean isPrivate, String password, User admin, int ID, boolean isVisible) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.password = password;
        this.admin = admin;
        this.ID = ID;
        this.isVisible = isVisible;
        this.peopleCounter = 0;
        this.startTime = System.currentTimeMillis();
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPeopleCounter() {
        return peopleCounter;
    }

    public void setPeopleCounter(int peopleCounter) {
        this.peopleCounter = peopleCounter;
    }

    public Timer getEmptyTimer() {
        return emptyTimer;
    }

    public void setEmptyTimer(Timer emptyTimer) {
        this.emptyTimer = emptyTimer;
    }

    public boolean isClosingScheduled() {
        return closingScheduled;
    }

    public void setClosingScheduled(boolean closingScheduled) {
        this.closingScheduled = closingScheduled;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isExpired() {
        return (peopleCounter == 0) && ((System.currentTimeMillis() - startTime) > 1 * 60 * 1000);
    }
}
