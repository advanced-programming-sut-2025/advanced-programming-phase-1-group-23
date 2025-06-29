package model.Objects;

import model.Naturals.Objectss;

public class ShippingBin implements Objectss {
    private int totalMoney;

    public ShippingBin() {
        this.totalMoney = 0;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void addTotalMoney(int amount) {
        this.totalMoney += amount;
    }
}
