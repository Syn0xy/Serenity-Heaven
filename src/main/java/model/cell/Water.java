package model.cell;

import manager.StaticSprite;
import model.CellType;
import model.StaticCell;

public class Water extends StaticCell {

    private static final StaticSprite SPRITE = new StaticSprite("water_spring");

    public Water() {
        super(SPRITE);
    }

    @Override
    public CellType getType() {
        return CellType.WATER;
    }

    @Override
    public CellType[] getPlaceable() {
        return new CellType[]{
            CellType.ALL
        };
    }

}
