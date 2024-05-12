package model.entity;

import manager.AnimationAction;
import manager.AnimationSprite;
import manager.Sprite;
import model.component.Collider;
import model.component.ColliderObserver;
import model.component.Health;
import model.component.SphereCollider;
import model.scene.GameScene;
import utils.Updatable;
import utils.Vector2;

public abstract class Entity extends Health implements Updatable, ColliderObserver {

    protected Vector2 position;

    private AnimationSprite animation;

    protected Collider collider;
    
    protected Entity(Vector2 position, AnimationAction[] animationActions) {
        this.position = position;
        this.animation = new AnimationSprite(animationActions);
        this.collider = new SphereCollider(this, position, 0.5f);
    }

    public abstract EntityType getEntityType();

    public Vector2 getPosition() {
        return position;
    }
    
    public Sprite getAnimation() {
        return animation;
    }

    public Collider getCollider() {
        return collider;
    }

    protected void setAnimationAction(String action) {
        this.animation.setAction(action);
    }

    @Override
    public void destroy() {
        GameScene.getInstance().killEntity(this);
    }
    
    @Override
    public void update() {
        collider.update();
        animation.update();
    }
    
    public boolean isVisible(Entity entity) {
        return getEntityType() != entity.getEntityType();
    }

}
