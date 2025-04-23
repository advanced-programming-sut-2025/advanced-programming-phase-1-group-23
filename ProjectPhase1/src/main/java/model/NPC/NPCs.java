package model.NPC;

import java.util.List;

public class NPCs {

    private String name;
    private String homeLocation;
    private List<String> dialogues;
    private List<String> favoriteGifts;
    private List<Quest> quests;
    private int friendshipPoints;
    private Location location;

    public NPCs(String name, String homeLocation, List<String> dialogues) {
        this.name = name;
        this.homeLocation = homeLocation;
        this.dialogues = dialogues;
        this.favoriteGifts = favoriteGifts;
        this.quests = quests;
        this.friendshipPoints = friendshipPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public List<String> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<String> dialogues) {
        this.dialogues = dialogues;
    }

    public List<String> getFavoriteGifts() {
        return favoriteGifts;
    }

    public void setFavoriteGifts(List<String> favoriteGifts) {
        this.favoriteGifts = favoriteGifts;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public int getFriendshipPoints() {
        return friendshipPoints;
    }

    public void setFriendshipPoints(int friendshipPoints) {
        this.friendshipPoints = friendshipPoints;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addFriendshipPoints(int points) {
        this.friendshipPoints += points;
    }

    public void addQuest(Quest quest) {
    }
}



