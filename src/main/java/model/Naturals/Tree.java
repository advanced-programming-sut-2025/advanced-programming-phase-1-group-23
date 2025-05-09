package model.Naturals;

import model.enums.CropName;
import model.enums.Season;
import model.enums.SeedType;
import model.enums.TreeName;

import java.util.ArrayList;

public class Tree implements Objectss{

    private final TreeName treeName;
    private boolean cut;
    private boolean wateredToday=false;
    private int daysWithoutIrrigation=0;

    public Tree(TreeName treeName, boolean cut) {
        this.treeName = treeName;
        this.cut = cut;
    }

    public TreeName getTreeName() {
        return treeName;
    }

    public void toggleCut(){
        this.cut=!(this.cut);
    }

    public int getDaysWithoutIrrigation() {
        return daysWithoutIrrigation;
    }

    public void increaseDaysWithoutIrrigation() {
        this.daysWithoutIrrigation++;
    }

    public boolean isWateredToday() {
        return wateredToday;
    }

    public void setWateredToday(boolean wateredToday) {
        this.wateredToday = wateredToday;
    }
}
