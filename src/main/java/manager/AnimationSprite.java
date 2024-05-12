package manager;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import utils.Updatable;

public class AnimationSprite implements Sprite, Updatable {

    private Image currentImage;

    private int currentWidth;

    private int currentHeight;

    private Map<String, AnimationAction> actions;

    private Image[] currentFrames;

    private String currentAction;

    private String nextAction;

    private int currentFrameIndex;
    
    private double prevTime;
    
    private double crntTime;
    
    private double maxTime;
    
    public AnimationSprite(AnimationAction[] actions){
        this.actions = new HashMap<>();
        this.prevTime = 0;
        this.crntTime = 0;
        
        for(AnimationAction action : actions){
            this.actions.put(action.actionName, action);
        }
        
        setAction(actions[0].actionName);
    }

    @Override
    public Image getImage() {
        return currentImage;
    }

    @Override
    public int getWidth() {
        return currentWidth;
    }

    @Override
    public int getHeight() {
        return currentHeight;
    }

    public void setAction(String action){
        if(currentAction != null && currentAction.equals(action)){
            return;
        }
        
        AnimationAction animationAction = actions.get(action);
        
        if(animationAction != null && animationAction.data != null){
            this.currentAction = action;
            ImageData data = animationAction.data;
            this.maxTime = 1000.0 / animationAction.framerate;
            this.currentFrameIndex = 0;
            this.currentFrames = data.frames;
            this.currentImage = currentFrames[currentFrameIndex];
            this.currentWidth = data.width;
            this.currentHeight = data.height;
            this.nextAction = animationAction.nextAction;
        }
    }
    
    @Override
    public void update() {
        crntTime = System.currentTimeMillis();
        if(crntTime - prevTime > maxTime){
            prevTime = crntTime;
            currentFrameIndex++;
            if(currentFrameIndex % currentFrames.length == 0){
                this.currentFrameIndex = 0;
                setAction(nextAction);
            }
            currentImage = currentFrames[currentFrameIndex];
        }
    }

}
