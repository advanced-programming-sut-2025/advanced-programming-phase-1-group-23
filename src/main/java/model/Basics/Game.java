package model.Basics;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Transient;
import model.Maps.Farm;
import model.Maps.Maps;
import model.enums.Season;
import model.enums.Weather;
import org.bson.types.ObjectId;
import view.PlayGame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity("games")
public class Game {
    @Id
    private String id;
    private ArrayList<Player> players;
    private Maps map;
    private boolean isGameOngoing;
    private Player currentPlayer;
    private LocalDateTime date;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private Season season;
    private ObjectId objectId;

    @Transient
    private PlayGame gameThread;
    public boolean hasTurnCycleFinished;

    public void advanceTime() {
        date = date.plusHours(1);
        hasTurnCycleFinished = false;
        if (date.getHour() == 23) {
            date = date.plusHours(10);

            weatherToday = weatherTomorrow;
            determineWeatherTomorrow();

        }
        if (date.getDayOfMonth() == 29) {
            date = date.minusDays(28);
            date = date.plusMonths(1);
        }
    }

    private void determineWeatherTomorrow() {
        int randomNumber;
        do {
            randomNumber = (int) (Math.random() * 4);
        } while (!Weather.values()[randomNumber]
                .isWeatherPossible(App.getLoggedInUser().getCurrentGame().getSeason()));
        weatherTomorrow = Weather.values()[randomNumber];
    }

    public boolean checkSeasonChange() {
        if (date.getMonthValue() >= 1 && date.getMonthValue() <= 3) {
            season = Season.SPRING;
            return true;
        } else if (date.getMonthValue() >= 4 && date.getMonthValue() <= 6) {
            season = Season.SUMMER;
            return true;
        } else if (date.getMonthValue() >= 7 && date.getMonthValue() <= 9) {
            season = Season.AUTUMN;
            return true;
        } else if (date.getMonthValue() >= 10 && date.getMonthValue() <= 12) {
            season = Season.WINTER;
            return true;
        }
        return false;
    }

    public Game() {
        // TODO:Set turns
    }

    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.hasTurnCycleFinished = false;
        this.players = players;
        this.map = Maps.makeMap();
        this.isGameOngoing = true;
        this.currentPlayer = currentPlayer;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = LocalDateTime.parse("2025-01-01 09:00:00", dateTimeFormatter);
        this.weatherToday = Weather.SUNNY;
        this.weatherTomorrow = Weather.SUNNY;
        this.season = Season.SPRING;
        this.isGameOngoing = false;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Maps getMap() {
        return map;
    }

    public boolean isGameOngoing() {
        return isGameOngoing;
    }

    public void setGameOngoing(boolean gameOngoing) {
        isGameOngoing = gameOngoing;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Weather getWeatherToday() {
        return weatherToday;
    }

    public void setWeatherToday(Weather weatherToday) {
        this.weatherToday = weatherToday;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public PlayGame getGameThread() {
        return gameThread;
    }

    public void setGameThread(PlayGame gameThread) {
        this.gameThread = gameThread;
    }

    public Weather getWeatherTomorrow() {
        return weatherTomorrow;
    }

    public void setWeatherTomorrow(Weather weatherTomorrow) {
        this.weatherTomorrow = weatherTomorrow;
    }

    public String getId() {
        return id;
    }

    public Player findPlayerByUser(User user) {
        for (Player player : players) {
            if (player.getUser().getUsername().equals(user.getUsername())) {
                return player;
            }
        }
        return null;
    }

    public boolean cycleToNextPlayer() {
        int index = players.indexOf(currentPlayer);
        if (index == players.size() - 1) {
            currentPlayer = players.getFirst();
            return true;
        } else {
            currentPlayer = players.get(index + 1);
            return false;
        }
    }

    //New Jasmin
    public Farm getFarmByNumber(int number){
        for(Player p:players){
            if(p.getFarm().getNum() == number){
                return p.getFarm();
            }
        }
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setMap(Maps map) {
        this.map = map;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public boolean isHasTurnCycleFinished() {
        return hasTurnCycleFinished;
    }

    public void setHasTurnCycleFinished(boolean hasTurnCycleFinished) {
        this.hasTurnCycleFinished = hasTurnCycleFinished;
    }
}
