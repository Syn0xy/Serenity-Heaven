package utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    
    protected List<Updatable> observers;
    
    protected Subject() {
        this.observers = new ArrayList<>();
    }
    
    public void attach(Updatable observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(Updatable observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    protected void notifyObservers() {
        this.observers.forEach(Updatable::update);
    }

}
