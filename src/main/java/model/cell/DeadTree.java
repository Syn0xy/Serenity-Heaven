package model.cell;

import manager.Sprite;
import manager.StaticSprite;
import model.CellType;

public class DeadTree extends StaticCell {

    private static final StaticSprite[] SPRITES = new StaticSprite[]{
        new StaticSprite("dead_tree"),
        new StaticSprite("moss_tree"),
        new StaticSprite("tree_stump"),
    };
    
    private static final Sprite random(){
        return SPRITES[(int)(Math.random() * SPRITES.length)];
    }

    public DeadTree() {
        super(random());
    }

    @Override
    public CellType getType() {
        return CellType.ALL;
    }

    @Override
    public CellType[] getPlaceable() {
        return new CellType[]{
            CellType.LAND,
            CellType.FARMLAND
        };
    }

}
