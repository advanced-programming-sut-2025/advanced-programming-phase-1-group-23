package model.Basics;

import model.Objects.Tool;

import java.util.ArrayList;

public class User {
    private String username;
    private String nickname;
    private String password;
    private String email;
    private final String gender;
    private int maxScore;
    private int numberOfGamesPlayed;
    private boolean isPlaying;
    private ArrayList<Tool> inventory;

    public User(String username, String nickname, String password, String gender, String email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.maxScore = 0;
        this.numberOfGamesPlayed = 0;
        this.isPlaying = false;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public void increaseNumberOfGamesPlayed() {
        this.numberOfGamesPlayed++;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public ArrayList<Tool> getInventory() {
        return inventory;
    }
}
