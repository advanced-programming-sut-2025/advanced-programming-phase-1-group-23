package model.Basics;

import model.Maps.Maps;
import model.NPC.NPCs;
import model.Seasons.AppSeason;
import model.Seasons.TimeAndDate;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Maps> farmsList = new ArrayList<>();
    private Player currentPlayer;
    private final ArrayList<NPCs> npcsList  = new ArrayList<>();
    private AppSeason season;
    private TimeAndDate timeAndDate;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
