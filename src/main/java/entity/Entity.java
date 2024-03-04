package entity;

import manager.AnimationAction;
import manager.Sprite;
import manager.AnimationSprite;
import util.Vector2;

public abstract class Entity {

    protected Vector2 position;

    protected AnimationSprite animation;
    
    protected Entity(Vector2 position, AnimationAction[] animationActions){
        this.position = position;
        this.animation = new AnimationSprite(animationActions);
    }

    public Vector2 getPosition() {
        return position;
    }
    
    public Sprite getSprite() {
        return animation;
    }

    protected void setAnimationAction(String action) {
        this.animation.setAction(action);
    }

    public void update() {
        animation.update();
    }

    protected void move(Vector2 move){
        position.plus(move);
    }

}
