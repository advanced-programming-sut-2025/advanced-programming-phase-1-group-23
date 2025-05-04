package model.Naturals;

import model.enums.CropName;
import model.enums.Season;
import model.enums.SeedType;
import model.enums.TreeName;

import java.util.ArrayList;

public class Tree {

    private final TreeName treeName;
    private boolean cut;

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

}
