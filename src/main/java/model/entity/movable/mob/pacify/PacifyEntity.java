package model.entity.movable.mob.pacify;

import manager.AnimationAction;
import model.entity.EntityType;
import model.entity.movable.mob.BotEntity;
import utils.Vector2;

public abstract class PacifyEntity extends BotEntity {

    private Vector2 velocity;

    protected PacifyEntity(Vector2 position, AnimationAction[] animationActions) {
        super(position, animationActions);
        this.velocity = new Vector2();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ANIMAL;
    }

    @Override
    public void move() {
        // Vector2 target;
        // if(!entity.isDead() && isVisible(entity)){
        //     target = entity.getPosition();
        //     velocity.plus(position.x - target.x, position.y - target.y);
        // }
    }

    @Override
    public void update() {
        super.update();
        force(velocity);
        velocity.x = 0;
        velocity.y = 0;
    }

}
