package model.entity;

import java.util.Comparator;

import utils.Vector2;

public class EntityDistanceComparator implements Comparator<Entity> {

    private Vector2 position;

    public EntityDistanceComparator(Vector2 position){
        this.position = position;
    }

    @Override
    public int compare(Entity e1, Entity e2) {
        return Double.compare(
            position.distance(e1.getPosition()),
            position.distance(e2.getPosition()));
    }

}
