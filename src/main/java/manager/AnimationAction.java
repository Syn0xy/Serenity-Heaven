package manager;

public class AnimationAction {

    protected String actionName;
    
    protected ImageData data;
    
    protected float framerate;
    
    protected String nextAction;
    
    public AnimationAction(String actionName, String framesName, float framerate, String nextAction){
        this.actionName = actionName;
        this.data = ImageManager.get(framesName);
        this.framerate = framerate;
        this.nextAction = nextAction;
    }

    public String getActionName() {
        return actionName;
    }

}
