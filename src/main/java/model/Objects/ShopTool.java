package src.main.java.model.Objects;
import model.enums.ToolType;

public class ShopTool extends ShopItem {
    private final ToolType type;

    public ShopTool(ToolType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ToolType getType() {
        return type;
    }
}
