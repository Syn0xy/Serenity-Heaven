package model.entity.movable.mob;

import java.util.ArrayList;
import java.util.List;

import manager.AnimationAction;
import model.entity.Entity;
import model.entity.movable.MovableEntity;
import utils.Vector2;

public abstract class BotEntity extends MovableEntity {

    protected List<Entity> visibleEntities;

    protected BotEntity(Vector2 position, AnimationAction[] animationActions) {
        super(position, animationActions);
        this.visibleEntities = new ArrayList<>();
    }

    protected abstract int getVisibleRange();

    @Override
    public boolean isVisible(Entity entity) {
        return super.isVisible(entity) && position.distance(entity.getPosition()) <= getVisibleRange();
    }

    @Override
    public void update() {
        super.update();
        visibleEntities.clear();
    }
    
}
