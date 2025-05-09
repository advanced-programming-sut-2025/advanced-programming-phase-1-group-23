package model.Objects;

import model.Basics.Game;
import model.Basics.Player;
import model.enums.ToolLevel;
import model.enums.ToolType;

public class Tool {
    private ToolType toolType;
    private ToolLevel toolLevel;
    private int useCost;
    private int irrigationCapacity=0;

    public Tool(ToolType toolType, ToolLevel toolLevel) {
        this.toolType = toolType;
        this.toolLevel = toolLevel;
        this.useCost = calculateUseCost();
    }

    public void useOn(){

    }

    private int calculateUseCost() {
        Game game = App.allGames.getLast();
        Player player = game.getCurrentPlayer();
        int useCost = switch (this.toolType) {
            case Hoe, Pickaxe, Axe, WateringCan -> switch (this.toolLevel) {
                case Gold -> 2;
                case Iridium -> 1;
                case Initial -> 5;
                case Iron -> 3;
                case Cooper -> 4;
                case Learning, Bambou, FiberGlass -> 0;
            };
            case Scythe -> 2;
            case Scissors, MilkingCan -> 4;

            case FishingRod -> switch (this.toolLevel) {
                case Learning -> 8;
                case Bambou -> 8;
                case FiberGlass -> 6;
                case Initial, Iron, Cooper, Gold -> 0;
                case Iridium -> 4;
            };
            case TrashCan -> 0;
        };

        if ((this.toolType == ToolType.Hoe || this.toolType == ToolType.WateringCan) && player.returnFarmingLevel() == 4)
            useCost--;
        if ((this.toolType == ToolType.Pickaxe) && player.returnMiningLevel() == 4) useCost--;
        if (this.toolType == ToolType.Axe && player.returnForagingLevel() == 4) useCost--;
        if (this.toolType == ToolType.FishingRod && player.returnFishingLevel() == 4) useCost--;

        return Math.min(0, useCost);
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public ToolLevel getToolLevel() {
        return toolLevel;
    }

    public void setToolLevel(ToolLevel toolLevel) {
        this.toolLevel = toolLevel;
    }

    public int getUseCost() {
        return useCost=calculateUseCost();
    }

    public void setUseCost(int useCost) {
        this.useCost = useCost;
    }

    public int getIrrigationCapacity() {
        return irrigationCapacity;
    }

    public void setIrrigationCapacity(int irrigationCapacity) {
        this.irrigationCapacity = irrigationCapacity;
    }

    public boolean isHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }
}
