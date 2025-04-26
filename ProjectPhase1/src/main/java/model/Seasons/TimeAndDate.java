package model.Seasons;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Objects.Energy;
import view.GameMenu;

public class TimeAndDate {
    private int hour;
    private int day;
    private final String[] daysName = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    public TimeAndDate(int hour, int day) {
        this.hour = hour;
        this.day = day;
    }

    public void increaseHour() {
    }

    public void increaseDayAndSeason() {
       Game game=App.allGames.getLast();
        Player player=game.getCurrentPlayer();
        player.setEnergy(new Energy(player.getEnergy().getMaxEnergy(),200));
        if (player.isFainted())player.setEnergy(new Energy(player.getEnergy().getMaxEnergy(), 150));
    }

}
