package model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class Plant extends Objects{

    public Plant(){
        super(false,"plant","green");
    }
}
