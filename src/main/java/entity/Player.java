package entity;

import input.Input;
import input.KeyCode;
import manager.AnimationAction;
import model.Time;
import util.Vector2;

public class Player extends Entity {

    private static final float SPEED = 5f;

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("idle", "player_idle", 2f, "idle"),
    };
    
    public Player(Vector2 position) {
        super(position, ANIMATION_ACTIONS);
    }

    public void update(){
        super.update();
        if(Input.getKey(KeyCode.Z)){
            position.y += SPEED * Time.deltaTime;
        }
        if(Input.getKey(KeyCode.S)){
            position.y -= SPEED * Time.deltaTime;
        }
        if(Input.getKey(KeyCode.D)){
            position.x += SPEED * Time.deltaTime;
        }
        if(Input.getKey(KeyCode.Q)){
            position.x -= SPEED * Time.deltaTime;
        }
    }

}
