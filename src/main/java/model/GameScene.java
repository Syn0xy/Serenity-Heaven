package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Entity;
import entity.Player;
import entity.mob.Chicken;
import entity.mob.Slime;
import entity.mob.TestSlime;
import input.Input; 
import input.KeyCode;
import model.cell.Carrot;
import model.cell.Onion;
import model.cell.Potato;
import model.cell.Radish;
import model.cell.Spinach;
import model.cell.Sprout;
import model.cell.Turnip;
import util.Vector2;
import view.util.Subject;

public class GameScene extends Subject {

    private static final boolean LIMIT_FRAMES_PER_SECOND = true;
    
    private static final int FRAMES_PER_SECOND = 60;

    private static GameScene singleton;

    public static GameScene getInstance(){
        if(singleton == null) singleton = new GameScene();
        return singleton;
    }

    private Vector2 viewer;
    
    private List<Entity> entities;

    private EndlessTerrain endlessTerrain;

    private GameScene(){
        this.entities = new CopyOnWriteArrayList<>();
        Entity player = new Player(new Vector2(-7, 1));
        this.viewer = player.getPosition();
        this.entities.add(player);
        this.entities.add(new Chicken(new Vector2(-5, 0)));
        this.entities.add(new Slime(new Vector2(-5, 2)));
        this.endlessTerrain = new EndlessTerrain(viewer);
    }
    
    public Vector2 getViewer(){
        return viewer;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }

    public List<TerrainChunk> getVisibleTerrainChunks(){
        return endlessTerrain.getVisibleTerrainChunks();
    }

    public void start() {
        long prevTime = 0;
        long crntTime = 0;
        long timeDiff;
        long maxTime = 1000 / FRAMES_PER_SECOND;
        while(true){
            if(LIMIT_FRAMES_PER_SECOND){
                crntTime = System.currentTimeMillis();
                timeDiff = crntTime - prevTime;
                Time.deltaTime = timeDiff / 1000.0;
                if(timeDiff > maxTime){
                    prevTime = crntTime;
                    update();
                }
            }else{
                update();
            }
        }
    }

    private void update(){
        endlessTerrain.updateChunks();
        for(Entity entity : entities){
            entity.update();
        }

        if(Input.getKeyDown(KeyCode.A)){
            int range = 10;
            this.entities.add(
                new Slime(
                    new Vector2(
                        viewer.x + (int)(Math.random() * range) - range / 2,
                        viewer.y + (int)(Math.random() * range) - range / 2)));
        }

        if(Input.getKeyDown(KeyCode.W)){ placeCell(viewer.copy(), new Carrot()); }
        if(Input.getKeyDown(KeyCode.X)){ placeCell(viewer.copy(), new Spinach()); }
        if(Input.getKeyDown(KeyCode.C)){ placeCell(viewer.copy(), new Potato()); }
        if(Input.getKeyDown(KeyCode.V)){ placeCell(viewer.copy(), new Turnip()); }
        if(Input.getKeyDown(KeyCode.B)){ placeCell(viewer.copy(), new Onion()); }
        if(Input.getKeyDown(KeyCode.N)){ placeCell(viewer.copy(), new Radish()); }
        if(Input.getKeyDown(KeyCode.E)){
            this.entities.add(new TestSlime(viewer.copy(), viewer));
        }
        
        if(Input.getKeyDown(KeyCode.R)){
            int range = 20;
            for(int x = 0; x < range; x++){
                for (int y = 0; y < range; y++) {
                    Vector2 pos = new Vector2(viewer.x + x - range / 2, viewer.y + y - range / 2);
                    interact(pos);
                }
            }
        }
        
        if(Input.getKeyDown(KeyCode.TAB)){
            int range = 10;
            for(int x = 0; x < range; x++){
                for (int y = 0; y < range; y++) {
                    Vector2 pos = new Vector2(viewer.x + x - range / 2, viewer.y + y - range / 2);
                    this.entities.add(new Chicken(pos));
                }
            }
        }
        
        Input.update();
        notifyObservers();
    }

    private void placeCell(Vector2 vec2, Cell cell) {
        try {
            endlessTerrain.placeCell(vec2, cell);
        } catch (Exception e) {
            // System.err.println(e.getMessage());
        }
    }
    
    public void interact(Vector2 vec2){
        // System.out.println("Interact: " + vec2);
        Sprout sprout = null;
        double rand = Math.random();
        if(rand < 0.25) sprout = new Carrot();
        else if(rand < 0.5) sprout = new Spinach();
        else if(rand < 0.75) sprout = new Potato();
        else if(rand < 0.9) sprout = new Onion();
        else sprout = new Turnip();
        placeCell(vec2, sprout);
    }

}
