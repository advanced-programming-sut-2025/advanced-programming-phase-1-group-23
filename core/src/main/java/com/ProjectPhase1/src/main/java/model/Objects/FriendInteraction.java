package model.Objects;

public class FriendInteraction {
    private int XP;
    private boolean haveTalked;
    private boolean haveTraded;
    private boolean haveGifted;
    private boolean haveHugged;

    public FriendInteraction() {
        this.XP = 0;
        this.haveTalked = false;
        this.haveTraded = false;
        this.haveGifted = false;
        this.haveHugged = false;
    }

    public int getXP() {
        return XP;
    }

    public int getFriendShipLevel() {
        if (XP < 100)
            return 0;
        if (XP < 300)
            return 1;
        if (XP < 600)
            return 2;
        if (XP < 1000)
            return 3;
        return 4;
    }

    public void changeXP(int amount) {
        if (XP < 600 && XP + amount > 600) {
            XP = 599;
            return;
        }
        this.XP += amount;
        if (XP < 0)
            this.XP = 0;
    }

    public boolean isHaveTalked() {
        return haveTalked;
    }

    public void setHaveTalked(boolean haveTalked) {
        this.haveTalked = haveTalked;
    }

    public boolean isHaveTraded() {
        return haveTraded;
    }

    public void setHaveTraded(boolean haveTraded) {
        this.haveTraded = haveTraded;
    }

    public boolean isHaveGifted() {
        return haveGifted;
    }

    public void setHaveGifted(boolean haveGifted) {
        this.haveGifted = haveGifted;
    }

    public boolean isHaveHugged() {
        return haveHugged;
    }

    public void setHaveHugged(boolean haveHugged) {
        this.haveHugged = haveHugged;
    }

    public void GoodNight() {
        if (!haveTalked && !haveTraded && !haveGifted && !haveHugged)
            changeXP(-10);
        this.haveTalked = false;
        this.haveTraded = false;
        this.haveHugged = false;
    }
}
