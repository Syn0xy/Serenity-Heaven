package model.entity.movable.player;

import utils.Time;
import utils.Vector2;

public class FollowingCamera {

    private static final float SPEED = 32f;
    
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
        float s = (float)(1.0 - Time.deltaTime) * SPEED;
        position.x += (target.x + offset.x - position.x) / s;
        position.y += (target.y + offset.y - position.y) / s;
    }

}
