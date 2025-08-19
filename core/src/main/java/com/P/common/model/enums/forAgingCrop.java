package com.P.common.model.enums;

public enum forAgingCrop {
    FOR_AGING_CROP(new CropName[]{CropName.CommonMushroom,CropName.Daffodil,CropName.Dandelion,CropName.Leek,CropName.Morel,CropName.Salmonberry,CropName.SpringOnion,CropName.WildHorseradish,
    CropName.FiddleheadFern,CropName.Grape,CropName.RedMushroom,CropName.SpiceBerry,CropName.SweetPea,CropName.Blackberry,
    CropName.Chanterelle,CropName.Hazelnut,CropName.PurpleMushroom,CropName.WildPlum,
    CropName.Crocus,CropName.CrystalFruit,CropName.Holly,CropName.SnowYam,CropName.WinterRoot})
    ;

    private final CropName[] crops;

    forAgingCrop(CropName[] crops) {
        this.crops = crops;
    }

    public CropName[] getCrops() {
        return crops;
    }
}
