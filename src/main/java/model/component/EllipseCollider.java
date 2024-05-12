package model.component;

import utils.Vector2;

public class EllipseCollider extends Collider {
    
    protected float width;

    protected float height;

    public EllipseCollider(ColliderObserver colliderObserver, Vector2 position, float width, float height){
        super(colliderObserver, position);
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    protected boolean isCollision(BoxCollider boxCollider) {
        return false;
    }

    @Override
    protected boolean isCollision(SphereCollider sphereCollider) {
        return false;
    }

}
