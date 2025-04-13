package model.Basics;

import model.Maps.Map;
import model.Objects.NPCs;
import model.Seasons.AppSeason;
import model.Seasons.TimeAndDate;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Map> farmsList = new ArrayList<>();
    private final ArrayList<NPCs> npcsList  = new ArrayList<>();
    private AppSeason season;
    private TimeAndDate timeAndDate;
}
