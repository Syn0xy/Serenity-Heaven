package model.cell;

import manager.Sprite;
import manager.StaticSprite;
import model.CellType;

public class Grass extends StaticCell {

    private static final StaticSprite[] SPRITES = new StaticSprite[]{
        new StaticSprite("grass_1"),
        new StaticSprite("grass_2"),
    };
    
    private static final Sprite random(){
        return SPRITES[(int)(Math.random() * SPRITES.length)];
    }

    public Grass() {
        super(random());
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
