package model.Basics;

import controller.FarmingController;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Transient;
import model.Maps.Building;
import model.Maps.Farm;
import model.Maps.Maps;
import model.Maps.Position;
import model.Objects.*;
import model.enums.Season;
import model.enums.Weather;
import org.bson.types.ObjectId;
import model.NPC.NPC;
import model.enums.NPCinformation;
import model.enums.Ingredients;
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
    private ArrayList<ArrayList<FriendInteraction>> friendMatrix;
    private ArrayList<NPC> npcs;

    @Transient
    private PlayGame gameThread;
    public boolean hasTurnCycleFinished;
    private Farm farm;


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
        if (date.getMonthValue() == 1) {
            season = Season.SPRING;
            for (Building building : farm.getBuildings()) {
                if (building instanceof Shop) {
                    ((Shop) building).ChangeSeasonSpring();
                }
            }
            return true;
        } else if (date.getMonthValue() == 2) {
            season = Season.SUMMER;
            for (Building building : farm.getBuildings()) {
                if (building instanceof Shop) {
                    ((Shop) building).ChangeSeasonSummer();
                }
            }
            return true;
        } else if (date.getMonthValue() == 3) {
            season = Season.AUTUMN;
            for (Building building : farm.getBuildings()) {
                if (building instanceof Shop) {
                    ((Shop) building).ChangeSeasonFall();
                }
            }
            return true;
        } else if (date.getMonthValue() == 4) {
            season = Season.WINTER;
            for (Building building : farm.getBuildings()) {
                if (building instanceof Shop) {
                    ((Shop) building).ChangeSeasonWinter();
                }
            }
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

    //New Jasmin
    public Farm getFarmByNumber(int number) {
        for (Player p : players) {
            if (p.getFarm().getNum() == number) {
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

    public void newDayBackgroundChecks() {
        for (Player player : players) {
            if (player.isFainted()) {
                player.setFainted(false);
                player.setEnergy(150);
            } else {
                player.setEnergy(200);
            }
            player.setUsedEnergyInTurn(0);
        }

        FarmingController.GoodNightFarm();

        weatherToday = weatherTomorrow;

        determineAndSetWeatherTomorrow();

        for (Player player : getPlayers()) {
            for (Building building : player.getFarm().getBuildings())
                if (building instanceof Barn) {
                    for (Animal animal : ((Barn) building).getAnimals())
                        animal.GoodNight();
                }
            for(ShippingBin shippingBin : player.getFarm().getShippingBins()){}
                //shippingBin.GoodNight();
        }

        for (NPC npc : getNpcs())
            npc.GoodNight();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                friendMatrix.get(i).get(j).GoodNight();

        if (weatherToday == Weather.RAIN || weatherToday == Weather.STORM) {
            //  waterAllCrops();
        }

        // handleCrowAttack();

        //  resetAllAnimalDailyVariables();

//        reInitializeStoreProductsCount();
//        reInitializeNpc();
//        addPlayersMoney(this);

        if (weatherToday == Weather.STORM) {
            strikeLightningOnStormyDay();
        }

        // npcGiveReward(this);

        //  handleArtisanUse();
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
            for (int i = 0; i < 3; i++) {
                int targetX = (int) (Math.random() * 75);
                int targetY = (int) (Math.random() * 50);
                player.getFarm().strikeLightning(targetX, targetY, game.getDate());
            }
        }
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
        this.npcs.add(new NPC("Sebastian", NPCinformation.Sebastian.getInformation(), favorites, new Position(19, 31)));
        favorites.clear();

        favorites.add(Ingredients.STONE);
        favorites.add(Ingredients.IRIDIUM_ORE);
        favorites.add(Ingredients.COFFEE);
        this.npcs.add(new NPC("Abigail", NPCinformation.Abigail.getInformation(), favorites, new Position(5, 17)));
        favorites.clear();

        favorites.add(Ingredients.COFFEE);
        favorites.add(Ingredients.Cheese);
        favorites.add(Ingredients.Wine);
        this.npcs.add(new NPC("Harvey", NPCinformation.Harvey.getInformation(), favorites, new Position(37, 12)));
        favorites.clear();

        favorites.add(Ingredients.SALAD);
        favorites.add(Ingredients.GRAPE);
        favorites.add(Ingredients.Wine);
        this.npcs.add(new NPC("Lea", NPCinformation.Lea.getInformation(), favorites, new Position(22, 9)));
        favorites.clear();

        favorites.add(Ingredients.SPAGHETTI);
        favorites.add(Ingredients.WOOD);
        favorites.add(Ingredients.IRON);
        this.npcs.add(new NPC("Robin", NPCinformation.Sebastian.getInformation(), favorites, new Position(26, 26)));
        favorites.clear();
    }


}
