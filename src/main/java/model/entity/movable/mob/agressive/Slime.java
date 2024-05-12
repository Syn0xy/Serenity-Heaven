package model.entity.movable.mob.agressive;

import manager.AnimationAction;
import model.component.EllipseCollider;
import utils.Vector2;

public class Slime extends AgressiveEntity {

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("idle", "slime_idle", 6f, "dead"),
        new AnimationAction("dead", "slime_dead", 7.5f, "jump"),
        new AnimationAction("jump", "slime_jump", 8f, "idle"),
    };

    private static final float SPEED = 1;

    private static final int VISIBLE_RANGE = 8;

    private static final int MAX_HEALTH = 6;

    private static final float ATTACK_RANGE = 0.2f;

    private static final int ATTACK_DAMAGE = 1;

    public Slime(Vector2 position) {
        super(position, ANIMATION_ACTIONS);
        collider = new EllipseCollider(this, position, 2f, 1f);
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

    @Override
    public float getAttackRange() {
        return ATTACK_RANGE;
    }

    @Override
    public int getDamage() {
        return ATTACK_DAMAGE;
    }

}
