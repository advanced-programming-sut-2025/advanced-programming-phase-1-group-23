package com.P.common.model.Maps;

import com.P.common.model.enums.TreeName;

import dev.morphia.annotations.Embedded;

@Embedded
public class Plant extends Objects {
    private TreeName name;

    public Plant () {

    }
    public Plant(TreeName name) {
        super(false, "green", "tree");
        this.name = name;
    }
}
