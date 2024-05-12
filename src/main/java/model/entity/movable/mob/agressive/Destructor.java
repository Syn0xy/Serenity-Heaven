package model.entity.movable.mob.agressive;

import model.component.SphereCollider;
import utils.Vector2;

public class Destructor extends Slime {
    
    private static final float SPEED = 200;

    private static final int VISIBLE_RANGE = 99999;

    private static final int MAX_HEALTH = 10;

    private static final float ATTACK_RANGE = 1;

    private static final int ATTACK_DAMAGE = 1000;

    public Destructor(Vector2 position) {
        super(position);
        this.collider = new SphereCollider(this, position, 0.5f);
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

    // @Override
    // protected void move() {
    //     List<Entity> visibleEntities = GameScene.getInstance().getEntities(this, visibleRange);
    //     if (visibleEntities.isEmpty()) {
    //         return;
    //     }
        
    //     if (target == null) {
    //         target = (MovableEntity)visibleEntities.get((int)(Math.random() * visibleEntities.size()));
    //     } else {
    //         Vector2 tpos = target.getPosition();
    //         if(target.isDeath()){
    //             target = null;
    //             return;
    //         }
    //         force(new Vector2(tpos.x - position.x, tpos.y - position.y));
            
    //         if(tpos.distance(position) < attackRange){
    //             target.damage(damage);
    //             target.force(new Vector2(tpos.x - position.x, tpos.y - position.y), 150);
    //         }
    //     }
    // }
    
}
