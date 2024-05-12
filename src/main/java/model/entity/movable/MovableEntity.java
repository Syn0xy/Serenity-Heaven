package model.entity.movable;

import manager.AnimationAction;
import model.component.Collider;
import model.entity.Entity;
import utils.Time;
import utils.Vector2;

public abstract class MovableEntity extends Entity {

    private static final float L = 0.95f;

    protected float speed;

    private Vector2 intertie;

    protected MovableEntity(Vector2 position, AnimationAction[] animationActions) {
        super(position, animationActions);
        this.speed = getSpeed() * 0.1f;
        this.intertie = new Vector2();
    }

    public abstract float getSpeed();

    protected abstract void move();
    
    @Override
    public void update() {
        if(!isDead()){
            move();
        }
        super.update();
        
        if(!isDead() && (intertie.x != 0 || intertie.y != 0)){
            double r = Math.PI / 2;
            double radian = Math.atan2(intertie.y, intertie.x) + Math.PI - r / 2;
            
            if (radian > 0 && radian < r) {
                down();
            } else if(radian > r && radian < r * 2) {
                right();
            } else if(radian > r * 2 && radian < r * 3) {
                up();
            } else {
                left();
            }
        }
        
        position.plus(intertie);
        intertie.multiply((float)(L - Time.deltaTime));
        stopIntertie();
    }
    
    @Override
    public void onCollisionStay(Collider collider) {
        Vector2 target = collider.getPosition();
        force(new Vector2(position.x - target.x, position.y - target.y));
    }

    private void stopIntertie(){
        if(intertie.x > - 0.0001f && intertie.x < 0.0001f){
            intertie.x = 0;
        }
        if(intertie.y > - 0.0001f && intertie.y < 0.0001f){
            intertie.y = 0;
        }
    }

    protected void left(){}
    protected void right(){}
    protected void up(){}
    protected void down(){}

    public void force(Vector2 force){
        this.force(force.normalize(), speed);
    }

    public void force(Vector2 dir, float force){
        Vector2 vec2 = dir.copy();
        vec2.multiply((float)(force * Time.deltaTime));
        intertie.plus(vec2);
    }
    
}