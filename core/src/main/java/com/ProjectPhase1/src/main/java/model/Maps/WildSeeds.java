package model.Maps;
import dev.morphia.annotations.Embedded;
import model.enums.CropName;

@Embedded
public class WildSeeds extends Objects{

    public WildSeeds(CropName crop) {
        super(true,"purple","seeds");
    }
}
