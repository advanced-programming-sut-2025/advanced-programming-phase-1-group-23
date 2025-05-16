package model.enums;

import model.Naturals.Tree;

public enum forAgingTree {
    forAgings(new TreeName[]{TreeName.MapleTree,TreeName.PineTree,TreeName.MahoganyTree,TreeName.MushroomTree,TreeName.OakTree});
    ;


    private final TreeName[] trees;

    forAgingTree(TreeName[] trees) {
        this.trees = trees;
    }

    public TreeName[] getTrees() {
        return trees;
    }
}
