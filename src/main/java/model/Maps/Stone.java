package model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class Stone extends Objects{
    public Stone(){
        super(false,"stone","black");
    }
}
