package com.P.common.model.NPC;

public class NPCFriendship {
    private int xp;
    private boolean haveTalked;
    private boolean haveGifted;

    public NPCFriendship() {
        this.haveGifted = false;
        this.haveTalked = false;
        this.xp = 0;
    }

    public int getXp() {
        return xp;
    }

    public int getFriendshipLevel() {
        return xp / 200;
    }

    public void changeXp(int xp) {
        this.xp += xp;
        if (this.xp >= 800)
            this.xp = 799;
    }

    public boolean isHaveTalked() {
        return haveTalked;
    }

    public void setHaveTalked(boolean haveTalked) {
        this.haveTalked = haveTalked;
    }

    public boolean isHaveGifted() {
        return haveGifted;
    }

    public void setHaveGifted(boolean haveGifted) {
        this.haveGifted = haveGifted;
    }
}
