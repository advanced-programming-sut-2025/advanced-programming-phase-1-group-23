package model.Maps;
import dev.morphia.annotations.Embedded;
import model.enums.Ingredients;

@Embedded
public class Stone extends Objects{
    public Stone(Ingredients type, String color, String name){
        super(false,"cyan","#");
    }
}
