package model.scene;

import java.util.Timer;
import java.util.TimerTask;

import utils.Subject;
import utils.Time;
import utils.Updatable;

public abstract class Scene extends Subject implements Updatable {
    
    private long prevTime;
    
    private long crntTime;
    
    public void start(int framesPerSecond){
        prevTime = System.currentTimeMillis();
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                crntTime = System.currentTimeMillis();
                Time.deltaTime = (crntTime - prevTime) / 1000.0;
                prevTime = crntTime;
                update();
                notifyObservers();
            }
        },0, 1000 / framesPerSecond);
    }

}
