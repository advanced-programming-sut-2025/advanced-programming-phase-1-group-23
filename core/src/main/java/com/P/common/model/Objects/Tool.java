package com.P.common.model.Objects;

import com.P.common.model.enums.ToolLevel;
import com.P.common.model.enums.ToolType;

import dev.morphia.annotations.Embedded;

@Embedded
public class Tool {
    private ToolType toolType;
    private ToolLevel toolLevel;
    private int irrigationCapacity = 0;

    public Tool(ToolType toolType, ToolLevel toolLevel) {
        this.toolType = toolType;
        this.toolLevel = toolLevel;
    }

    public Tool() {
    }

    public void useOn() {

    }

    private int calculateUseCost() {
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

            case planting -> 0;
            case fertility -> 0;
            case FishingRod -> switch (this.toolLevel) {
                case Learning -> 8;
                case Bambou -> 8;
                case FiberGlass -> 6;
                case Initial, Iron, Cooper, Gold -> 0;
                case Iridium -> 4;
            };
            case TrashCan -> 0;
        };

        return  useCost;
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
        return calculateUseCost();
    }

    public int getIrrigationCapacity() {
        return irrigationCapacity;
    }

    public void setIrrigationCapacity(int irrigationCapacity) {
        this.irrigationCapacity = irrigationCapacity;
    }
}
