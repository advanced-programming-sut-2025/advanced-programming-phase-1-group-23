package model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class Water extends Objects{

    public Water(){
        super(false,"water","blue");
    }
}
