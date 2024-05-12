package model.scene;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import input.Input;
import input.KeyCode;
import model.EndlessTerrain;
import model.TerrainChunk;
import model.cell.Carrot;
import model.cell.Cell;
import model.cell.Onion;
import model.cell.Potato;
import model.cell.Radish;
import model.cell.Spinach;
import model.cell.Sprout;
import model.cell.Turnip;
import model.entity.Entity;
import model.entity.movable.mob.agressive.Destructor;
import model.entity.movable.mob.agressive.Slime;
import model.entity.movable.mob.agressive.TestSlime;
import model.entity.movable.mob.pacify.Chicken;
import model.entity.movable.player.Player;
import utils.Updatable;
import utils.Vector2;
import view.GameView;

public class GameScene extends Scene {
    
    private static GameScene singleton;

    public static GameScene getInstance(){
        if(singleton == null) singleton = new GameScene();
        return singleton;
    }

    private Vector2 viewer;
    
    private List<Entity> entities;

    private EndlessTerrain endlessTerrain;

    private GameScene() {
        this.entities = new CopyOnWriteArrayList<>();
        Entity player = new Player(new Vector2(-15, 0));
        this.viewer = player.getPosition();
        this.entities.add(player);
        this.endlessTerrain = new EndlessTerrain(viewer);
    }
    
    public Vector2 getViewer() {
        return viewer;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }

    public List<TerrainChunk> getVisibleTerrainChunks() {
        return endlessTerrain.getVisibleTerrainChunks();
    }

    public void killEntity(Entity entity) {
        entities.remove(entity);
    }

    // public List<Entity> getEntities(Entity entity, int range){
    //     List<Entity> visible = new ArrayList<>();
    //     for(Entity e : entities){
    //         if(e != entity && e.getEntityType() != entity.getEntityType() && entity.getPosition().distance(e.getPosition()) <= range){
    //             visible.add(e);
    //         }
    //     }
    //     return visible;
    // }

    @Override
    public void start(int framesPerSecond) {
        super.start(framesPerSecond);
        new GameView(this);
    }

    @Override
    public void update() {
        endlessTerrain.updateChunks();
        this.entities.forEach(Updatable::update);
        
        // for (int i = 0; i < entities.size(); i++) {
        //     Entity e1 = entities.get(i);
        //     for (int j = 0; j < entities.size(); j++) {
        //         Entity e2 = entities.get(j);
        //         if (e1 != e2) {
        //             e1.update(e2);
        //         }
        //     }
        //     e1.endUpdate();
        // }

        if(Input.getKeyDown(KeyCode.A)) {
            int range = 3;
            for(int x = 0; x < range; x++){
                for (int y = 0; y < range; y++) {
                    Vector2 pos = new Vector2(viewer.x + (x - range / 2) * range, viewer.y + (y - range / 2) * range);
                    GameScene.singleton.entities.add(new Slime(pos));
                }
            }
        }

        if(Input.getKeyDown(KeyCode.W)) { placeCell(viewer.copy(), new Carrot()); }
        if(Input.getKeyDown(KeyCode.X)) { placeCell(viewer.copy(), new Spinach()); }
        if(Input.getKeyDown(KeyCode.C)) { placeCell(viewer.copy(), new Potato()); }
        if(Input.getKeyDown(KeyCode.V)) { placeCell(viewer.copy(), new Turnip()); }
        if(Input.getKeyDown(KeyCode.B)) { placeCell(viewer.copy(), new Onion()); }
        if(Input.getKeyDown(KeyCode.N)) { placeCell(viewer.copy(), new Radish()); }
        if(Input.getKeyDown(KeyCode.E)) { this.entities.add(new TestSlime(viewer.copy(), viewer)); }
        if(Input.getKeyDown(KeyCode.T)) { this.entities.add(new Chicken(viewer.copy())); }
        if(Input.getKeyDown(KeyCode.U)) { this.entities.add(new Destructor(new Vector2())); }
        
        if(Input.getKeyDown(KeyCode.Y)) {
            int range = 10;
            this.entities.add(new Slime(new Vector2((int)(viewer.x + range), (int)(viewer.y + range))));
            this.entities.add(new Slime(new Vector2((int)(viewer.x - range), (int)(viewer.y + range))));
            this.entities.add(new Slime(new Vector2((int)(viewer.x + range), (int)(viewer.y - range))));
            this.entities.add(new Slime(new Vector2((int)(viewer.x - range), (int)(viewer.y - range))));
        }
        
        if(Input.getKeyDown(KeyCode.R)){
            int range = 11;
            for(int x = 0; x < range; x++){
                for (int y = 0; y < range; y++) {
                    Vector2 pos = new Vector2(viewer.x + x - range / 2, viewer.y + y - range / 2);
                    interact(pos);
                }
            }
        }
        
        if(Input.getKeyDown(KeyCode.TAB)){
            int range = (int)Math.sqrt(1000) + 1;
            for(int x = 0; x < range; x++){
                for (int y = 0; y < range; y++) {
                    Vector2 pos = new Vector2((int)viewer.x + x - range / 2, (int)viewer.y + y - range / 2);
                    GameScene.singleton.entities.add(new Chicken(pos));
                }
            }
        }
        
        Input.update();
    }

    private void placeCell(Vector2 vec2, Cell cell) {
        try {
            endlessTerrain.placeCell(vec2, cell);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void interact(Vector2 vec2){
        System.out.println("Interact: " + vec2);
        Sprout sprout = null;
        double rand = Math.random();
        if(rand < 0.25) sprout = new Carrot();
        else if(rand < 0.5) sprout = new Spinach();
        else if(rand < 0.75) sprout = new Potato();
        else if(rand < 0.9) sprout = new Onion();
        else sprout = new Turnip();
        placeCell(vec2, sprout);
        // entities.add(new Chicken(vec2));
    }

}
