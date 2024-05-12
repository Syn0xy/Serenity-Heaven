package model.component;

import utils.Vector2;

public class BoxCollider extends Collider {
    
    protected float width;

    protected float height;

    public BoxCollider(ColliderObserver colliderObserver, Vector2 position, float width, float height){
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

    /*
            +---------------+
            |               |
            |       •   +---+---------+
            |           |   |         |
            +-----------+---+  •      |
                        |             |
                        +-------------+
    */
    protected boolean isCollision(BoxCollider boxCollider){
        // float cx = Math.abs(boxCollider.position.x - position.x);
        // float cy = Math.abs(boxCollider.position.y - position.y);
        return
            Math.abs(boxCollider.position.x - position.x) <= (width + boxCollider.width) / 2 &&
            Math.abs(boxCollider.position.y - position.y) <= (height + boxCollider.height) / 2;
    }
    
    @Override
    protected boolean isCollision(SphereCollider sphereCollider) {
        return Collider.isCollision(this, sphereCollider);
    }
    
}
