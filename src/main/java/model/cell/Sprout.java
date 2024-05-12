package model.cell;

import java.time.Duration;
import java.time.Instant;

import manager.AnimationAction;
import model.CellType;

public abstract class Sprout extends AnimationCell {

    private String[] statesAnimationName;
    
    private Instant start;

    private int statesCount;

    private int timeToGrow;

    private boolean finish;
    
    protected Sprout(AnimationAction[] animationActions) {
        super(animationActions);
        this.statesAnimationName = new String[animationActions.length];
        this.start = Instant.now();
        this.statesCount = animationActions.length;
        this.timeToGrow = getTimeToGrow();
        this.finish = false;
        for (int i = 0; i < animationActions.length; i++) {
            statesAnimationName[i] = animationActions[i].getActionName();
        }
    }
    
    protected abstract int getTimeToGrow();
    
    @Override
    public void update() {
        if(!finish){
            Duration timeElapsed = Duration.between(start, Instant.now());
            int index = (int)((timeElapsed.toSeconds() * (statesCount - 1)) / timeToGrow);
            if(index < statesCount){
                setAnimationAction(statesAnimationName[index]);
                if(index == statesCount - 1){
                    finish = true;
                }
            }
        }
    }

    @Override
    public CellType getType() {
        return CellType.FARM;
    }

    @Override
    public CellType[] getPlaceable() {
        return new CellType[]{
            CellType.LAND,
            CellType.FARMLAND
        };
    }

}
