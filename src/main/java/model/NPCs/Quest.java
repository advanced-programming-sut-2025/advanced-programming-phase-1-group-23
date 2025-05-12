package model.NPC;
import dev.morphia.annotations.Embedded;

@Embedded
public class Quest {
    private String description;
    private String requiredItem;
    private String reward;
    private boolean isCompleted;

    public Quest(String description, String requiredItem, String reward, boolean isCompleted) {
        this.description = description;
        this.requiredItem = requiredItem;
        this.reward = reward;
        this.isCompleted = isCompleted;
    }

    public Queest(){
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
