package entity.mob;

import entity.Entity;
import manager.AnimationAction;
import util.Vector2;

public class Slime extends Entity {

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("idle", "slime_idle", 6f, "dead"),
        new AnimationAction("dead", "slime_dead", 7.5f, "jump"),
        new AnimationAction("jump", "slime_jump", 8f, "idle"),
    };

    public Slime(Vector2 position) {
        super(position, ANIMATION_ACTIONS);
    }

}
