package view;

import model.Basics.Game;
import model.Repo.GameRepo;

public class PlayGame extends Thread {
    private Game game;
    public boolean keepRunning = false;

    public PlayGame(Game game) {
        this.game = game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void run() {
        while (keepRunning) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (game.hasTurnCycleFinished) {
                game.advanceTime();
            }
            boolean check = game.checkSeasonChange();
            if(check){
                //GameRepo.saveGame(game);
            }
        }
    }
}
