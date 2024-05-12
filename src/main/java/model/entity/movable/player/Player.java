package model.entity.movable.player;

import input.Input;
import input.KeyCode;
import manager.AnimationAction;
import model.entity.EntityType;
import model.entity.movable.MovableEntity;
import utils.Vector2;

public class Player extends MovableEntity {

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("idle", "player_idle", 2f, "idle"),
    };

    private static final float SPEED = 7.5f;

    private static final int MAX_HEALTH = 15;
    
    public Player(Vector2 position) {
        super(position, ANIMATION_ACTIONS);
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.HUMAIN;
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    protected void move() {}

    @Override
    public void update() {
        super.update();
        Vector2 force = new Vector2();
        if(Input.getKey(KeyCode.Z)) { force.y += 1; }
        if(Input.getKey(KeyCode.S)) { force.y -= 1; }
        if(Input.getKey(KeyCode.D)) { force.x += 1; }
        if(Input.getKey(KeyCode.Q)) { force.x -= 1; }
        force(force);
    }

}
