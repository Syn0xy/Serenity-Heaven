package model.cell;

import model.CellType;
import model.StaticCell;

public class Empty extends StaticCell {

    public Empty() {
        super(null);
    }

    @Override
    public CellType getType() {
        return null;
    }

    @Override
    public CellType[] getPlaceable() {
        return null;
    }

}
