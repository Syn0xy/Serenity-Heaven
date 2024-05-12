package model.cell;

import manager.AnimationAction;
import manager.Sprite;
import utils.Updatable;
import manager.AnimationSprite;

public abstract class AnimationCell extends Cell implements Updatable {

    private AnimationSprite animation;
    
    public AnimationCell(AnimationAction[] animationActions) {
        this.animation = new AnimationSprite(animationActions);
    }

    @Override
    public Sprite getSprite() {
        return animation;
    }

    protected void setAnimationAction(String action) {
        this.animation.setAction(action);
    }
    
    @Override
    public void update() {
        animation.update();
    }

}
