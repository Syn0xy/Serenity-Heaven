package model.entity.movable.mob.pacify;

import manager.AnimationAction;
import utils.Vector2;

public class Chicken extends PacifyEntity {

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("down_idle", "chicken_down_idle", 4f, "down_idle"),
        new AnimationAction("down_walk", "chicken_down_walk", 4f, "down_idle"),
        new AnimationAction("left_idle", "chicken_left_idle", 4f, "left_idle"),
        new AnimationAction("left_walk", "chicken_left_walk", 4f, "left_idle"),
        new AnimationAction("right_idle", "chicken_right_idle", 4f, "right_idle"),
        new AnimationAction("right_walk", "chicken_right_walk", 4f, "right_idle"),
        new AnimationAction("up_idle", "chicken_up_idle", 4f, "up_idle"),
        new AnimationAction("up_walk", "chicken_up_walk", 4f, "up_idle"),
        new AnimationAction("dead", "chicken_down_dead", 6f, "dead"),
    };

    private static final float SPEED = 4f;

    private static final int VISIBLE_RANGE = 5;

    private static final int MAX_HEALTH = 4;
    
    public Chicken(Vector2 position) {
        super(position, ANIMATION_ACTIONS);
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

    @Override
    protected int getVisibleRange() {
        return VISIBLE_RANGE;
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override protected void left() { setAnimationAction("left_walk"); }
    @Override protected void right() { setAnimationAction("right_walk"); }
    @Override protected void up() { setAnimationAction("up_walk"); }
    @Override protected void down() { setAnimationAction("down_walk"); }

    @Override
    public void destroy() {
        setAnimationAction("dead");
        super.destroy();
    }

}
