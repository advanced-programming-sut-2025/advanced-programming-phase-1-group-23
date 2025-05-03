package model.Objects;

import model.enums.ToolLevel;
import model.enums.ToolType;

public class Tool {
    private ToolType toolType;
    private ToolLevel toolLevel;
    private int useCost;

    public Tool(ToolType toolType, ToolLevel toolLevel, int useCost) {
        this.toolType = toolType;
        this.toolLevel = toolLevel;
        this.useCost = useCost;
    }

    public void useOn(){

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
        return useCost;
    }

    public void setUseCost(int useCost) {
        this.useCost = useCost;
    }
}
