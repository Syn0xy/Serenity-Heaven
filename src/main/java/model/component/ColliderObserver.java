package model.component;

public interface ColliderObserver {
    
    default public void onCollisionEnter(Collider collider) {}
    
    default public void onCollisionStay(Collider collider) {}
    
    default public void onCollisionExit(Collider collider) {}

}
