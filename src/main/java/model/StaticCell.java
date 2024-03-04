package model;

import manager.Sprite;

public abstract class StaticCell extends Cell {

    private Sprite sprite;

    public StaticCell(Sprite sprite){
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
    
}
