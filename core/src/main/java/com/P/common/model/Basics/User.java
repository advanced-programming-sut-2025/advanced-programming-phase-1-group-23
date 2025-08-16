package com.P.common.model.Basics;

import com.P.Server.model.Lobby;
import com.P.common.model.enums.Avatar;
import com.P.common.model.game.Clock;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Transient;
import com.P.common.model.Objects.Tool;
import com.P.Server.model.Repo.GameRepo;
import com.P.common.model.enums.SecurityQuestion;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Entity("users")
public class User {

    @Id
    private String username;
    private String nickname;
    private String password;
    private String hashedPassword;
    private SecurityQuestion question;
    private String answer;
    private String email;
    private String gender;
    private String id;
    private ObjectId gameId;
    private Avatar avatar;
    private String lobby;
    private ObjectId currentGameId;
    private ArrayList<ObjectId> savedGames = new ArrayList<>();


    //Game
    @Transient
    private Game currentGame;
    private ArrayList<ObjectId> games = new ArrayList<>();
    private int numberOfGamesPlayed;
    private boolean isPlaying;

    //Other
    private int maxScore;
    private ArrayList<Tool> inventory;

    public User(String gender) {

        this.gender = gender;
    }

    public User() {

    }

    public User(String gender
        , String email, String nickname, String password, String username, Avatar avatar) {
        this.gender = gender;
        this.email = email;
        this.nickname = nickname;
        this.hashedPassword = password;
        this.username = username;
        this.maxScore = 0;
        this.numberOfGamesPlayed = 0;
        this.avatar = avatar;
        this.currentGame = null;
        this.lobby = null;
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
        currentGame.setClock(new Clock());
        currentGame.getClock().setSeasonSprite();
        currentGame.getClock().setWeatherSprite();
    }

    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Game getCurrentGame() {
        if (currentGame == null && gameId != null) {
            currentGame = GameRepo.findGameById(gameId.toString(), true);
        }
        return currentGame;
    }

    public ArrayList<ObjectId> getGames() {
        return games;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public int getAvatarId() {
        return switch (avatar) {
            case ELLIOTT -> 1;
            case HALEY -> 2;
            case LEAH -> 3;
            case ROBIN -> 4;
            case SEBASTIAN -> 5;
            case SHANE -> 6;
            default -> 0;
        };
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

    public void addSavedGame(ObjectId gameId) {
        if (!savedGames.contains(gameId)) {
            savedGames.add(gameId);
        }
    }

    public List<ObjectId> getSavedGames() {
        return savedGames;
    }

    public void setCurrentGameId(ObjectId gameId) {
        this.currentGameId = gameId;
    }

    public ObjectId getCurrentGameId() {
        return currentGameId;
    }

    public Game loadGame(ObjectId gameId) {
        return GameRepo.findGameById(gameId.toString(), true);
    }

    public void setSavedGames(ArrayList<ObjectId> savedGames) {
        this.savedGames = savedGames;
    }

}
