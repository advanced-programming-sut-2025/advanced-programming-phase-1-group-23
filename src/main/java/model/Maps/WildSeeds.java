package model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class WildSeeds extends Objects{

    public WildSeeds(){
        super(true,"seeds","yellow");
    }
}
