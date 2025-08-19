package com.P.common.model.Maps;
import dev.morphia.annotations.Embedded;
import com.P.common.model.enums.Ingredients;

@Embedded
public class Stone extends Objects{
    public Stone(Ingredients type, String color, String name){
        super(false,"cyan","#");
    }
}
