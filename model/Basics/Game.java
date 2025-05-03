package model.Basics;

import model.enums.Season;
import model.enums.Weather;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Game {
    private ArrayList<Player> players;
    private Map map;
    private boolean isGameOngoing;
    private Player currentPlayer;
    private LocalDateTime date;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private Season season;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
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

    public Weather getWeatherTomorrow() {
        return weatherTomorrow;
    }

    public void setWeatherTomorrow(Weather weatherTomorrow) {
        this.weatherTomorrow = weatherTomorrow;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public boolean isHasTurnCycleFinished() {
        return hasTurnCycleFinished;
    }

    public void setHasTurnCycleFinished(boolean hasTurnCycleFinished) {
        this.hasTurnCycleFinished = hasTurnCycleFinished;
    }
}
