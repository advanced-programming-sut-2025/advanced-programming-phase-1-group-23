package controller;

import model.NPC.DialogueCondition;
import model.NPC.NPCs;
import model.NPC.Quest;
import view.NPCView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPCController extends ControllersController{
        private List<NPCs> npcs;

        public NPCController() {
            this.npcs = new ArrayList<>();
            initializeNPCs();
        }


        //NPC 5


        public List<NPCs> getAllNPCs() {
            return npcs;
        }

}
