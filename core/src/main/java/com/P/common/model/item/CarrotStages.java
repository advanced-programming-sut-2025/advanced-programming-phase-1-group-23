package com.P.common.model.item;

public enum CarrotStages {
    CARROT_STAGE_1("game/Ingredient/carrot_stage_1.png"),
    CARROT_STAGE_2("game/Ingredient/carrot_stage_2.png"),
    CARROT_STAGE_3("game/Ingredient/carrot_stage_3.png"),
    CARROT_STAGE_4("game/Ingredient/carrot_stage_4.png"),
    ;
    private final String iconPath;
    private CarrotStages(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return iconPath;
    }
}
