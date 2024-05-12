package model.component;

import utils.Vector2;

public class SphereCollider extends Collider {

    protected float radius;

    public SphereCollider(ColliderObserver colliderObserver, Vector2 position, float radius){
        super(colliderObserver, position);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    protected boolean isCollision(SphereCollider sphereCollider){
        return sphereCollider.position.distance(position) <= radius + sphereCollider.radius;
    }

    @Override
    protected boolean isCollision(BoxCollider boxCollider) {
        return Collider.isCollision(boxCollider, this);
    }

}
