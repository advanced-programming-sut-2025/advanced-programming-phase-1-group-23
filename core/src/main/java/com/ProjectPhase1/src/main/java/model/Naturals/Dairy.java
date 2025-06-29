package model.Naturals;

import model.enums.DairyTypes;
import dev.morphia.annotations.Embedded;

@Embedded
public class Dairy {
    private DairyTypes dairyTypes;

    public Dairy() {
    }
}
