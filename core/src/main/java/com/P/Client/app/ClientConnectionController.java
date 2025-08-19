package com.P.Client.app;

import com.P.Client.controller.GameController;
import com.P.Client.controller.TurnController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.model.Pair;
import com.P.Client.model.Payam;
import com.P.Client.view.GameView.GameMenu;
import com.P.Client.view.PreGameView.NewGameView;
import com.P.Main;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Maps.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientConnectionController {
    public static Message handleCommand(Message command) {
        return null;
    }

    public static void handleUpdate(Message message) {
        if(message.getFromBody("Lets Play!") != null) {
            TurnController.handleNewGame();
            return;
        }

        if(message.getFromBody("advanceTime") != null) {
            App.getLoggedInUser().getCurrentGame().advanceTime();
        }

        if(message.getFromBody("updatePosition") != null) {
            System.out.println("update player position");
            int turn = message.getIntFromBody("updatePosition");
            System.out.println(turn);
            if(turn != ClientApp.getTurnNumber()) {
                int x = message.getIntFromBody("X");
                int y = message.getIntFromBody("Y");
                System.out.println("x , y: " + x + " " + y);
                App.getLoggedInUser().getCurrentGame().getPlayers()
                    .get(turn).setPlayerPosition(new Pair<>((float) x, (float) y));

                int reaction = message.getIntFromBody("reaction");
                App.getLoggedInUser().getCurrentGame().getPlayers()
                    .get(turn).setReaction(reaction, false);

                boolean inVillage = message.getFromBody("inVillage").equals("true");
                System.out.println("reaction : " + reaction + ", village: " + inVillage);
                App.getLoggedInUser().getCurrentGame().getPlayers()
                    .get(turn).setInVillage(inVillage);
            }
        }

        if(message.getFromBody("isExist") != null) {
            GameMenu.isExit = (message.getFromBody("isExist").equals("true"));
        }

        if (message.getFromBody("updateSkill") != null) {
            String skill = message.getFromBody("updateSkill");
            int turn = message.getIntFromBody("turn");
            int value = message.getIntFromBody("value");
            App.getLoggedInUser().getCurrentGame().getPlayers().get(turn).updateSkill(skill, value);
        }

        if (message.getFromBody("publicMessage") != null) {
            String matn = message.getFromBody("publicMessage");
            String tag = message.getFromBody("tag");
            boolean isTag = tag.equals(App.getLoggedInUser().getUsername());
            App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPublicChat()
                .add(new Payam(matn, isTag));
            if(isTag) {
                //TODO : Khorshid
            }
        }

        if (message.getFromBody("privateMessage") != null) {
            String matn = message.getFromBody("privateMessage");
            String sender = message.getFromBody("sender");
            String receiver = message.getFromBody("receiver");
            if (App.getLoggedInUser().getUsername().equals(receiver)) {
               int ind = getIndex(sender);
               if(ind != -1) {
                   App.getLoggedInUser().getCurrentGame().getCurrentPlayer()
                       .getPrivateChats().get(ind).add(matn);
               }
            }
        }


//        int turn = ClientApp.getTurnNumber();
//        for (int i = 0; i < 4; i++)
//            if (i != turn) {
//                if(message.getFromBody("isInVillage" + i) != null) {
//                    Float x = message.getFromBody("playerF" + i);
//                    Float y = message.getFromBody("playerS" + i);
//                    App.getLoggedInUser().getCurrentGame().getPlayers().get(i).setPlayerPosition(new Pair<>(x, y));
//                    App.getLoggedInUser().getCurrentGame().getPlayers().get(i).setInVillage(true);
//                } else {
//                    App.getLoggedInUser().getCurrentGame().getPlayers().get(i).setInVillage(false);
//                }
//
//                if(message.getFromBody("reaction" + i) != null) {
//                    App.getLoggedInUser().getCurrentGame().getPlayers().get(i).setReaction(message.getFromBody("reaction" + i));
//                }
//            }

//        if (message.getFromBody("updateMapSelection") != null) {
//            TurnController.updateMapSelection(message.getFromBody("updateMapSelection"));
//        }
    }

    private static int getIndex(String name) {
        Game game = App.getLoggedInUser().getCurrentGame();
        for(int i = 0; i < 4; i++)
            if(game.getPlayers().get(i).getUser().getUsername().equals(name))
                return i;
        return -1;
    }
}
