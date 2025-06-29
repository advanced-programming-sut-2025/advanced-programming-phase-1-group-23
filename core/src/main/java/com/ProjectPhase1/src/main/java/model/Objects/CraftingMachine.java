package model.Objects;

import model.Naturals.Objectss;
import model.enums.Ingredients;

public class CraftingMachine implements Objectss {
    private final Ingredients type;
    private int processingHourTime = 0;
    private boolean isWorking = false;
    private Ingredients product = null;

    public CraftingMachine(Ingredients type) {
        this.type = type;
    }

    public Ingredients getType() {
        return type;
    }

    public int getProcessingHourTime() {
        return processingHourTime;
    }

    public void setProcessingHourTime(int processingHourTime) {
        this.processingHourTime = processingHourTime;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public Ingredients getProduct() {
        return product;
    }

    public void setProduct(Ingredients product) {
        this.product = product;
    }
}
