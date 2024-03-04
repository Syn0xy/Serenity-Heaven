package entity;

import util.Vector2;

public class FollowingCamera {
    
    private Vector2 target;
    
    private Vector2 offset;

    private Vector2 position;
    
    public FollowingCamera(Vector2 target, Vector2 offset){
        this.target = target;
        this.offset = offset;
        this.position = target.copy();
    }

    public FollowingCamera(Vector2 target){
        this(target, new Vector2());
    }

    public Vector2 getPosition() {
        return position;
    }
    
    public void update() {
        position.x += (target.x + offset.x - position.x) / 16;
        position.y += (target.y + offset.y - position.y) / 16;
    }

}
