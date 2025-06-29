package model.NPC;

public class Quest {
    private final Pair requirement;
    private final Pair reward;
    private boolean isCompleted;

    public Quest(Pair requirement, Pair reward) {
        this.requirement = requirement;
        this.reward = reward;
        this.isCompleted = false;
    }

    public Pair getRequirement() {
        return requirement;
    }

    public Pair getReward() {
        return reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
