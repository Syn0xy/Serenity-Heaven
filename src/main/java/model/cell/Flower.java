package model.cell;

import manager.StaticSprite;
import model.CellType;

public class Flower extends StaticCell {

    private static final StaticSprite SPRITE = new StaticSprite("flower_1");

    public Flower() {
        super(SPRITE);
    }

    @Override
    public CellType getType() {
        return CellType.FARM;
    }

    @Override
    public CellType[] getPlaceable() {
        return new CellType[]{
            CellType.LAND,
            CellType.FARMLAND
        };
    }

}
