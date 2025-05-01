package model.Basics;

import model.Objects.Tool;
import model.enums.SecurityQuestion;

import java.util.ArrayList;

public class User {
    private String username;
    private String nickname;
    private String password;
    private String hashedPassword;
    private SecurityQuestion question;
    private String answer;
    private String email;
    private final String gender;
    private Game currentGame;
    private int maxScore;
    private int numberOfGamesPlayed;
    private boolean isPlaying;
    private String id;
    private ArrayList<Tool> inventory;

    public User(String gender
            , String email, String nickname, String password, String username) {
        this.gender = gender;
        this.email = email;
        this.nickname = nickname;
        this.hashedPassword = password;
        this.username = username;
        this.maxScore = 0;
        this.numberOfGamesPlayed = 0;
        this.currentGame = null;
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

    public String getId() {
        return id;
    }

    public void setInventory(ArrayList<Tool> inventory) {
        this.inventory = inventory;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public SecurityQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SecurityQuestion question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }

    public void setId(String id) {
        this.id = id;
    }
}
