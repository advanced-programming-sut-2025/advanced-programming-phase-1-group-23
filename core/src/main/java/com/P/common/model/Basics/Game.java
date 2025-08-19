package com.P.common.model.Basics;

import com.P.Client.controller.FarmingController;
import com.P.Client.controller.game.GameController;
import com.P.Client.model.Pair;
import com.P.Client.view.GameView.GameView;
import com.P.common.model.Maps.Building;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Maps;
import com.P.common.model.Maps.Position;
import com.P.common.model.Objects.*;
import com.P.common.model.game.VillageModel;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Transient;
import com.P.common.model.enums.Season;
import com.P.common.model.enums.Weather;
import org.bson.types.ObjectId;
import com.P.common.model.NPC.NPC;
import com.P.common.model.enums.NPCinformation;
import com.P.common.model.enums.Ingredients;
import com.P.Client.view.PlayGame;
import com.P.common.model.game.Clock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<ArrayList<FriendInteraction>> friendMatrix;
    private ArrayList<NPC> npcs;
    private Clock clock;
    private VillageModel villageModel = null;
    private double weather;
    private int[] scoreBoard = new int[4];
    private int sortFactor;

    @Transient
    private transient PlayGame gameThread;
    public boolean hasTurnCycleFinished;
    private Farm farm;


    public void advanceTime() {
        date = date.plusHours(1);
        hasTurnCycleFinished = false;
        if (date.getHour() == 23) {
            date = date.plusHours(10);

            weatherToday = weatherTomorrow;
            determineWeatherTomorrow();
            GameController.advanceToNextDay();
            newDayBackgroundChecks();

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
        if (date.getMonthValue() == 1) {
            season = Season.SPRING;
            return true;
        } else if (date.getMonthValue() == 2) {
            season = Season.SUMMER;
            return true;
        } else if (date.getMonthValue() == 3) {
            season = Season.AUTUMN;
            return true;
        } else if (date.getMonthValue() == 4) {
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
        this.friendMatrix = new ArrayList<>();
        for (int i = 0; i < players.size(); i++)
            this.friendMatrix.add(new ArrayList<>());
        for (int i = 0; i < players.size(); i++)
            for (int j = 0; j < players.size(); j++)
                this.friendMatrix.get(i).add(new FriendInteraction());
        this.npcs = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            scoreBoard[i] = i;
        sortFactor = 0;

        initializeNPCs();
        //TODO : add npcs & shops
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

    public Player getPlayerByName(String username) {
        for(Player player : players)
            if(player.getUser().getUsername().equals(username))
                return player;
        return null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<ArrayList<FriendInteraction>> getFriendMatrix() {
        return friendMatrix;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
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

    public Farm getFarmByNumber(int number) {
        for (Player p : players) {
            if (p.getFarm() != null && p.getFarm().getNum() == number) {
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

    List<Animal> productAnimals = new ArrayList<>();

    public void newDayBackgroundChecks() {
        for (Player player : players) {
            if (player.isFainted()) {
                player.setFainted(false);
                player.setEnergy(150);
            } else {
                player.setEnergy(200);
                Pair<Float,Float> pair = new Pair<>(3f,3f);
                player.setPlayerPosition(pair);
            }
            player.setUsedEnergyInTurn(0);
        }

        FarmingController.GoodNightFarm();

       setWeatherToday(weatherTomorrow);

        determineAndSetWeatherTomorrow();
        productAnimals.clear();

        for (Player player : getPlayers()) {
            for (Building building : player.getFarm().getBuildings())
                if (building instanceof Barn) {
                    for (Animal animal : ((Barn) building).getAnimals()) {
                        animal.GoodNight();
                        if (animal.getProduct() != null) {
                            productAnimals.add(animal);
                        }
                    }
                }
        }

        for (NPC npc : getNpcs())
            npc.GoodNight();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                friendMatrix.get(i).get(j).GoodNight();


        if (weatherToday == Weather.STORM) {
            strikeLightningOnStormyDay();
        }

        App.getLoggedInUser().getCurrentGame().getClock().setWeatherSprite();
        App.getLoggedInUser().getCurrentGame().getClock().setSeasonSprite();
        setWeather();

        GameView.sebastianStart = false;
        GameView.sebastianEnd = false;

        GameView.abigailStart = false;
        GameView.abigailEnd = false;

        GameView.harveyStart = false;
        GameView.harveyEnd = false;

        GameView.leaStart = false;
        GameView.leaEnd = false;

        GameView.robinStart = false;
        GameView.robinEnd = false;
    }

    private void determineAndSetWeatherTomorrow() {
        int randomNumber;
        do {
            randomNumber = (int) (Math.random() * 4);
        } while (!Weather.values()[randomNumber]
                .isWeatherPossible(App.getLoggedInUser().getCurrentGame().getSeason()));
        weatherTomorrow = Weather.values()[randomNumber];
    }

    private void strikeLightningOnStormyDay() {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();

        for (Player player : players) {
            strikeLightningForPlayer(player, game, 3);
        }
    }

    private void strikeLightningForPlayer(Player player, Game game, int strikesCount) {
        for (int i = 0; i < strikesCount; i++) {
            int[] coords = getRandomLightningCoordinates(75, 50);
            player.getFarm().strikeLightning(coords[0], coords[1], game.getDate());
        }
    }

    private int[] getRandomLightningCoordinates(int maxX, int maxY) {
        int x = (int) (Math.random() * maxX);
        int y = (int) (Math.random() * maxY);
        return new int[]{x, y};
    }


    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    private void initializeNPCs() {
        ArrayList<Ingredients> favorites = new ArrayList<>();
        favorites.add(Ingredients.WOOL);
        favorites.add(Ingredients.PUMPKIN_PIE);
        favorites.add(Ingredients.PIZZA);
        Position position = new Position();
        position.setX(8);
        position.setY(9);
        this.npcs.add(new NPC("Sebastian", NPCinformation.Sebastian.getInformation(), favorites, new Position(19, 31),position));
        favorites.clear();

        favorites.add(Ingredients.STONE);
        favorites.add(Ingredients.IRIDIUM_ORE);
        favorites.add(Ingredients.COFFEE);
        this.npcs.add(new NPC("Abigail", NPCinformation.Abigail.getInformation(), favorites, new Position(5, 17),new Position(24, 25)));
        favorites.clear();

        favorites.add(Ingredients.COFFEE);
        favorites.add(Ingredients.Cheese);
        favorites.add(Ingredients.Wine);
        this.npcs.add(new NPC("Harvey", NPCinformation.Harvey.getInformation(), favorites, new Position(37, 12),new Position(34, 35)));
        favorites.clear();

        favorites.add(Ingredients.SALAD);
        favorites.add(Ingredients.GRAPE);
        favorites.add(Ingredients.Wine);
        this.npcs.add(new NPC("Lea", NPCinformation.Lea.getInformation(), favorites, new Position(22, 9),new Position(44, 35)));
        favorites.clear();

        favorites.add(Ingredients.SPAGHETTI);
        favorites.add(Ingredients.WOOD);
        favorites.add(Ingredients.IRON);
        this.npcs.add(new NPC("Robin", NPCinformation.Sebastian.getInformation(), favorites, new Position(26, 26),new Position(15, 16)));
        favorites.clear();
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public VillageModel getVillageModel() {
        return villageModel;
    }

    public void setVillageModel(VillageModel villageModel) {
        this.villageModel = villageModel;
    }

    public void setWeather(){
        switch (App.getLoggedInUser().getCurrentGame().getWeatherToday()) {
            case SUNNY -> this.weather = 1.5;
            case RAIN -> this.weather = 1.2;
            case STORM -> this.weather = 0.5;
            default -> this.weather = 1;
        }
    }

    public double getWeather(){
        return weather;
    }

    public void setWeather(double weather) {
        this.weather = weather;
    }

    public List<Animal> getProductAnimals() {
        return productAnimals;
    }


    public void setSortFactor(int i) {
        if(i >= 0 && i < 3)
            sortFactor = i;
    }

    public void updateScoreBoard() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if((sortFactor == 0 && players.get(scoreBoard[j]).getMoney() < players.get(scoreBoard[j + 1]).getMoney()) ||
                    (sortFactor == 1 && players.get(scoreBoard[j]).getTotalSkills() < players.get(scoreBoard[j + 1]).getTotalSkills()) ||
                    (sortFactor == 2 && players.get(scoreBoard[j]).getCompletedQuests() < players.get(scoreBoard[j + 1]).getCompletedQuests())) {
                    int k = scoreBoard[j];
                    scoreBoard[j] = scoreBoard[j + 1];
                    scoreBoard[j + 1] = k;
                }
    }

    public String getScoreboard() {
        StringBuilder table = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            Player player = players.get(scoreBoard[i]);
            table.append(i + 1).append("- ").append(player.getUser().getNickname()).append(" ");
            if(i == 0)
                table.append("money: ");
            else
                table.append("       ");
            table.append(player.getMoney()).append(" ");
            if(i == 0)
                table.append("skills: ");
            else
                table.append("        ");
            table.append(player.returnFarmingLevel()).append(" ");
            table.append(player.returnMiningLevel()).append(" ");
            table.append(player.returnForagingLevel()).append(" ");
            table.append(player.returnFishingLevel()).append(" ");
            if(i == 0)
                table.append("quests: ");
            else
                table.append("        ");
            table.append(player.getCompletedQuests()).append("\n");
        }
        return table.toString();
    }
}
