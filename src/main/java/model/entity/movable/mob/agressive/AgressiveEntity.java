package model.entity.movable.mob.agressive;

import manager.AnimationAction;
import model.component.Collider;
import model.entity.EntityDistanceComparator;
import model.entity.EntityType;
import model.entity.movable.MovableEntity;
import model.entity.movable.mob.BotEntity;
import utils.Vector2;

public abstract class AgressiveEntity extends BotEntity {

    private final EntityDistanceComparator DISTANCE_COMPARATOR;

    protected float attackRange;

    protected int damage;

    protected MovableEntity target;

    protected AgressiveEntity(Vector2 position, AnimationAction[] animationActions) {
        super(position, animationActions);
        this.DISTANCE_COMPARATOR = new EntityDistanceComparator(position);
        this.attackRange = getAttackRange();
        this.damage = getDamage();
    }

    public abstract float getAttackRange();

    public abstract int getDamage();

    @Override
    public EntityType getEntityType() {
        return EntityType.MONSTER;
    }

    @Override
    public void move() {
        // if(!entity.isDead() && isVisible(entity)){
        //     visibleEntities.add(entity);
        // }
    }

    @Override
    public void update() {
        int visibleEntitiesCount = visibleEntities.size();
        if(visibleEntitiesCount > 0) {
            if(visibleEntitiesCount > 1) {
                visibleEntities.sort(DISTANCE_COMPARATOR);
            }
            target = (MovableEntity)visibleEntities.getFirst();
            Vector2 tpos = target.getPosition();
            Vector2 f = new Vector2(tpos.x - position.x, tpos.y - position.y);
            force(f);
            
            if(tpos.distance(position) < attackRange) {}
        }
        super.update();
    }

    @Override
    public void onCollisionStay(Collider collider) {
        // entity.damage(damage);
        // Vector2 tpos = entity.getPosition();
        // Vector2 f = new Vector2(tpos.x - position.x, tpos.y - position.y);
        // ((MovableEntity)entity).force(f, 150);
        super.onCollisionStay(collider);
    }

}
