package com.P.Client.controller.game;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;

import static com.P.Client.controller.WebController.SendPrivate;
import static com.P.Client.controller.WebController.SendPublic;

public class ChatController1 {
    public void sendPublicMessage(String text) {
        if(!text.contains("@")) {
            SendPublic(text, "");
            return;
        }
        String[] words = text.split("@");
        String matn = App.getLoggedInUser().getUsername() + ": " + words[0];
        SendPublic(matn, words[1]);
    }

    public void sendPrivateMessage(String text, String receiver) {
        int ind = getIndex(receiver);
        if(ind == -1)
            return;

        App.getLoggedInUser().getCurrentGame().getCurrentPlayer()
            .getPrivateChats().get(ind).add("You: " + text);
        String matn = App.getLoggedInUser().getUsername() + ": " + text;
        SendPrivate(matn, App.getLoggedInUser().getUsername(), receiver);
    }

    private static int getIndex(String name) {
        Game game = App.getLoggedInUser().getCurrentGame();
        for(int i = 0; i < 4; i++)
            if(game.getPlayers().get(i).getUser().getUsername().equals(name))
                return i;
        return -1;
    }
}
