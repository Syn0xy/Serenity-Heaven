package model.entity.movable.mob.agressive;

import utils.Vector2;

public class TestSlime extends Slime {

    private static final int DISTANCE_TO_TARGET = 5;

    private Vector2 target;

    private double currentDirection;

    private double cur;

    public TestSlime(Vector2 position, Vector2 target) {
        super(position);
        this.target = target;
    }

    @Override
    public void move() {
        super.move();
        currentDirection = Math.PI * 2 * cur;
        cur = (cur + 0.005) % 1;
        position.x = (float)(target.x + Math.cos(currentDirection) * DISTANCE_TO_TARGET);
        position.y = (float)(target.y + Math.sin(currentDirection) * DISTANCE_TO_TARGET);
    }
    
}
