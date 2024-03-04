package entity.mob;

import entity.Entity;
import manager.AnimationAction;
import util.Vector2;

public class Chicken extends Entity {

    private static final AnimationAction[] ANIMATION_ACTIONS = new AnimationAction[]{
        new AnimationAction("down_idle", "chicken_down_idle", 4f, "down_idle"),
        new AnimationAction("down_walk", "chicken_down_walk", 4f, "down_idle"),
        new AnimationAction("left_idle", "chicken_left_idle", 4f, "left_idle"),
        new AnimationAction("left_walk", "chicken_left_walk", 4f, "left_idle"),
        new AnimationAction("right_idle", "chicken_right_idle", 4f, "right_idle"),
        new AnimationAction("right_walk", "chicken_right_walk", 4f, "right_idle"),
        new AnimationAction("up_idle", "chicken_up_idle", 4f, "up_idle"),
        new AnimationAction("up_walk", "chicken_up_walk", 4f, "up_idle"),
    };
    
    public Chicken(Vector2 position) {
        super(position, ANIMATION_ACTIONS);
    }

    @Override
    public void update() {
        super.update();

        if(Math.random() > 0.97){
            Vector2 ran = random();
            if(ran.x > 0){
                setAnimationAction("right_walk");
            }else if(ran.x < 0){
                setAnimationAction("left_walk");
            }else if(ran.y > 0){
                setAnimationAction("up_walk");
            }else if(ran.y < 0){
                setAnimationAction("down_walk");
            }
            position.x += ran.x;
            position.y += ran.y;
        }
    }

    public Vector2 random(){
        if(Math.random() > 0.5){
            return new Vector2((int)(Math.random() * 3) - 1, 0);
        }else{
            return new Vector2(0, (int)(Math.random() * 3) - 1);
        }
    }

}
