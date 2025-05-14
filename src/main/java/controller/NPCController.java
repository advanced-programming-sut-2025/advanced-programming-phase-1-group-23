package controller;

import java.util.ArrayList;
import java.util.List;

public class NPCController extends ControllersController{
        private List<model.NPC.NPC1> npcs;

        public NPCController() {
            this.npcs = new ArrayList<>();
            initializeNPCs();
        }


        private void initializeNPCs() {
        }


        public List<model.NPC.NPC1> getAllNPCs() {
            return npcs;
        }

}
