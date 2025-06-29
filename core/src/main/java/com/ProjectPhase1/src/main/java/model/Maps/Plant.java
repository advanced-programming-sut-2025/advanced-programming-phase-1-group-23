package model.Maps;

import model.enums.TreeName;

import java.time.LocalDateTime;
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
