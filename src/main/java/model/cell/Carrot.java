package model.cell;

import manager.AnimationAction;

public class Carrot extends Sprout {

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("state_1", "carrot_sprout_state_1", 1f, "state_1"),
        new AnimationAction("state_2", "carrot_sprout_state_2", 1f, "state_2"),
        new AnimationAction("state_3", "carrot_sprout_state_3", 1f, "state_3"),
    };
    
    private static final int TIME_TO_GROW = 10;
    
    public Carrot() {
        super(ANIMATION_ACTIONS);
    }

    @Override
    protected int getTimeToGrow() {
        return TIME_TO_GROW;
    }

}
