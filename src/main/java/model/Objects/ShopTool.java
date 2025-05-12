package src.main.java.model.Objects;
import model.enums.ToolType;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopTool extends ShopItem {
    private final ToolType type;

    public ShopTool(ToolType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ShopTool() {
    }

    public ToolType getType() {
        return type;
    }
}
