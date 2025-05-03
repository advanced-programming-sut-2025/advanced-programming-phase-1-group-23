package model.NPC;

import model.Seasons.TimeAndDate;
import model.Seasons.Weather;
import model.enums.Season;

public class DialogueCondition {
    private TimeAndDate time;
    private Season season;
    private Weather weather;
    private NPCFriendshipLevel friendshipLevel;

    public DialogueCondition(TimeAndDate time, Season season, Weather weather, NPCFriendshipLevel friendshipLevel) {
        this.time = time;
        this.season = season;
        this.weather = weather;
        this.friendshipLevel = friendshipLevel;
    }

    public TimeAndDate getTime() {
        return time;
    }

    public void setTime(TimeAndDate time) {
        this.time = time;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public NPCFriendshipLevel getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(NPCFriendshipLevel friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }
}
