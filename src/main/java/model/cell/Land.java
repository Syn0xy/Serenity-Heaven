package model.cell;

import manager.StaticSprite;
import model.CellType;
import model.StaticCell;

public class Land extends StaticCell {

    private static final StaticSprite SPRITE = new StaticSprite("land_spring");

    public Land() {
        super(SPRITE);
    }

    @Override
    public CellType getType() {
        return CellType.LAND;
    }

    @Override
    public CellType[] getPlaceable() {
        return new CellType[]{
            CellType.WATER
        };
    }

}
