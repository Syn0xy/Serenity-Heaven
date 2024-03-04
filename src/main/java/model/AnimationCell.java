package model;

import manager.AnimationAction;
import manager.Sprite;
import manager.AnimationSprite;

public abstract class AnimationCell extends Cell {

    private AnimationSprite animation;
    
    public AnimationCell(AnimationAction[] animationActions){
        this.animation = new AnimationSprite(animationActions);
    }

    @Override
    public Sprite getSprite() {
        return animation;
    }

    protected void setAnimationAction(String action) {
        this.animation.setAction(action);
    }

    public void update(){
        animation.update();
    }

}
