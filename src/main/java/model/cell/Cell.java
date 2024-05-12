package model.cell;

import manager.Sprite;
import model.CellType;

public abstract class Cell {
    
    public abstract Sprite getSprite();

    public abstract CellType getType();

    public abstract CellType[] getPlaceable();

    public final boolean isPlaceable(Cell cellAbove){
        CellType[] placeable = getPlaceable();
        CellType cellAboveType = cellAbove.getType();
        for (int i = 0; i < placeable.length; i++) {
            if(placeable[i] == cellAboveType){
                return true;
            }
        }
        return false;
    }

}