package model.component;

import java.util.ArrayList;
import java.util.List;

import utils.Vector2;

public abstract class Collider {

    private static final List<Collider> COLLIDERS = new ArrayList<>();

    private static int countID = 0;

    private final int ID;

    protected ColliderObserver colliderObserver;

    protected Vector2 position;

    public Collider(ColliderObserver colliderObserver, Vector2 position){
        this.ID = countID++;
        this.colliderObserver = colliderObserver;
        this.position = position;
        COLLIDERS.add(this);
    }

    public Vector2 getPosition() {
        return position;
    }
    
    public void update() {
        COLLIDERS.forEach(collider -> {
            if (this.ID > collider.ID) {
                boolean collision = false;
                
                if (collider instanceof SphereCollider) {
                    collision = isCollision((SphereCollider)collider);
                } else if(collider instanceof BoxCollider) {
                    collision = isCollision((BoxCollider)collider);
                }
                
                if(collision){
                    this.colliderObserver.onCollisionStay(collider);
                    collider.colliderObserver.onCollisionStay(this);
                }
            }
        });
    }

    protected abstract boolean isCollision(BoxCollider boxCollider);

    protected abstract boolean isCollision(SphereCollider sphereCollider);

    protected static boolean isCollision(BoxCollider boxCollider, SphereCollider sphereCollider){
        double distanceX = Math.abs(sphereCollider.position.x - boxCollider.position.x);
        double distanceY = Math.abs(sphereCollider.position.y - boxCollider.position.y);
        
        // Vérifier si la distance entre les centres est inférieure à la somme des rayons
        if (distanceX > (boxCollider.width / 2 + sphereCollider.radius) || distanceY > (boxCollider.height / 2 + sphereCollider.radius)) {
            return false;
        }
        
        // Si la distance en X ou Y est inférieure à la moitié de la largeur/hauteur, alors il y a collision
        if (distanceX <= boxCollider.width / 2 || distanceY <= boxCollider.height / 2) {
            return true;
        }
        
        // Sinon, vérifier la collision aux coins de la boîte
        double cornerDistanceSquared = Math.pow(distanceX - boxCollider.width / 2, 2) + Math.pow(distanceY - boxCollider.height / 2, 2);
        return cornerDistanceSquared <= Math.pow(sphereCollider.radius, 2);
    }

}
