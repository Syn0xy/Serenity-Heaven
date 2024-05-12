package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import manager.Sprite;
import model.TerrainChunk;
import model.cell.Cell;
import model.component.BoxCollider;
import model.component.Collider;
import model.component.EllipseCollider;
import model.component.SphereCollider;
import model.entity.Entity;
import model.entity.movable.player.FollowingCamera;
import model.scene.GameScene;
import utils.Vector2;

public class GameCanvas extends Component {

    private static final int ZOOM_SIZE = 512;

    private static final int CELL_PIXEL = 16;

    private GameScene gameScene;
    
    private int width;

    private int height;
    
    private int cellSize;
    
    private Vector2 viewer;
    
    private FollowingCamera followViewer;
    
    private List<Entity> entities;
    
    private List<TerrainChunk> visibleTerrainChunks;

    private Vector2 currentSelected;

    private Vector2 mousePosition;
    
    protected GameCanvas(GameScene gameScene) {
        this.gameScene = gameScene;
        this.viewer = gameScene.getViewer();
        this.followViewer = new FollowingCamera(viewer);
        this.entities = gameScene.getEntities();
        this.visibleTerrainChunks = gameScene.getVisibleTerrainChunks();
    }
    
    public void interact() {
        if (this.currentSelected != null) {
            this.gameScene.interact(currentSelected);
        }
    }

    private boolean containsPoint(int x, int y) {
        return x >= - this.width / 2 - this.cellSize
            && y >= - this.height / 2 - this.cellSize
            && x <= this.width / 2
            && y <= this.height / 2;
    }

    private void refreshInteract() {
        Point mouse = getMousePosition();
        this.mousePosition = new Vector2(mouse.x, mouse.y);
        Vector2 target = followViewer.getPosition();

        Vector2 offset = new Vector2(
            mouse.x - (this.width - this.cellSize) / 2,
            mouse.y - (this.height - this.cellSize) / 2
        );

        System.out.println(offset);

        this.currentSelected = new Vector2(
            (int)Math.floor(target.x + offset.x / this.cellSize),
            (int)Math.floor(target.y - offset.y / this.cellSize)
        );
    }

    @Override
    public void paint(Graphics g) {
        this.width = getWidth();
        this.height = getHeight();
        this.followViewer.update();
        this.cellSize = (this.width / ZOOM_SIZE) * CELL_PIXEL;
        clearScreen(g);
        
        g.translate(this.width / 2, this.height / 2);
        drawVisibleChunks(g);
        drawEntities(g);

        try {
            refreshInteract();
            drawSelected(g);
        } catch (Exception e) {}
        
        g.translate(- this.width / 2, - this.height / 2);
        
        g.setColor(Color.BLACK);
        g.drawString("Entities: " + this.entities.size(), 30, 30);
        g.drawString("Mouse position: " + this.mousePosition, 30, 60);
        g.drawString("Current selected: " + this.currentSelected, 30, 90);
        g.drawString("Player: " + this.entities.get(0).getPosition(), 30, 120);
    }

    private void clearScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.width, this.height);
    }
    
    private void drawVisibleChunks(Graphics g) {
        for (TerrainChunk chunk : this.visibleTerrainChunks) {
            drawChunk(g, chunk);
        }
    }

    private void drawChunk(Graphics g, TerrainChunk chunk) {
        Vector2 position = chunk.getPosition();
        List<Cell>[][] cells = chunk.getCells();
        
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                List<Cell> listCells = cells[x][y];
                for(Cell cell : listCells){
                    Sprite sprite = cell.getSprite();
                    if(sprite != null){
                        placeImage(g, sprite, position.x + x, position.y + y);
                    }
                }
            }
        }
    }

    private void drawEntities(Graphics g) {
        for (Entity entity : this.entities) {
            placeImage(g, entity);
            drawUIHealth(g, entity);
            drawCollider(g, entity.getCollider());
        }
    }

    private void placeImage(Graphics g, Entity entity) {
        placeImage(g, entity.getAnimation(), entity.getPosition());
    }
    
    private void placeImage(Graphics g, Sprite sprite, Vector2 position) {
        placeImage(g, sprite, position.x, position.y);
    }

    private void placeImage(Graphics g, Sprite sprite, float x, float y) {
        int px = (int)((x - this.followViewer.getPosition().x - sprite.getWidth() / 2 - 0.5) * cellSize);
        int py = (int)((this.followViewer.getPosition().y - y - sprite.getHeight() / 2 - 0.5) * cellSize);
        int sx = cellSize * sprite.getWidth();
        int sy = cellSize * sprite.getHeight();
        if (containsPoint(px, py)) {
            g.drawImage(sprite.getImage(), px, py, sx, sy, null);
        }
    }
    
    private void drawSelected(Graphics g) {
        if(this.currentSelected != null){
            int x = (int)((this.currentSelected.x - this.followViewer.getPosition().x - 0.5) * this.cellSize);
            int y = (int)((this.followViewer.getPosition().y - this.currentSelected.y - 0.5) * this.cellSize);
            g.setColor(Color.WHITE);
            g.drawRect(x + 1, y + 1, this.cellSize - 1, this.cellSize - 1);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, this.cellSize + 1, this.cellSize + 1);
        }
    }

    private void drawUIHealth(Graphics g, Entity entity) {
        if(!entity.isFull()) {
            Vector2 pos = entity.getPosition();
            int sx = this.cellSize + this.cellSize / 2;
            int sy = this.cellSize / 8;
            int px = (int)(this.width / 2 + (pos.x - this.followViewer.getPosition().x) * this.cellSize) - sx / 2;
            int py = (int)(this.height / 2 + (this.followViewer.getPosition().y - pos.y - 0.5) * this.cellSize) - sy * 2;
            int crx = (sx * entity.getCurrentHealth()) / entity.getMaxHealth();
            
            if(containsPoint(px, py)) {
                g.setColor(Color.RED);
                g.fillRect(px, py, sx, sy);
                
                if(crx >= 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(px, py, crx, sy);
                }
            }
        }
    }

    private void drawCollider(Graphics g, Collider collider) {
        Vector2 pos = collider.getPosition();
        if(collider instanceof SphereCollider) {
            SphereCollider scollider = (SphereCollider)collider;
            int px = (int)((pos.x - followViewer.getPosition().x - scollider.getRadius()) * cellSize);
            int py = (int)((followViewer.getPosition().y - pos.y - scollider.getRadius()) * cellSize);
            int sx = (int)(cellSize * scollider.getRadius() * 2);
            int sy = (int)(cellSize * scollider.getRadius() * 2);
            if(containsPoint(px, py)) {
                g.setColor(Color.GREEN);
                g.drawOval(px, py, sx - 1, sy - 1);
                g.drawLine(px + sx / 2, py, px + sx / 2 - 1, py + sy - 1);
                g.drawLine(px, py + sy / 2, px + sx - 1, py + sy / 2 - 1);
            }
        } else if(collider instanceof EllipseCollider) {
            EllipseCollider ecollider = (EllipseCollider)collider;
            float rwidth = ecollider.getWidth();
            float rheight = ecollider.getHeight();
            int px = (int)((pos.x - followViewer.getPosition().x - rwidth / 2) * cellSize);
            int py = (int)((followViewer.getPosition().y - pos.y - rheight / 2) * cellSize);
            int sx = (int)(cellSize * rwidth);
            int sy = (int)(cellSize * rheight);
            if(containsPoint(px, py)) {
                g.setColor(Color.GREEN);
                g.drawOval(px, py, sx - 1, sy - 1);
                g.drawLine(px + sx / 2, py, px + sx / 2 - 1, py + sy - 1);
                g.drawLine(px, py + sy / 2, px + sx - 1, py + sy / 2 - 1);
            }
        } else if (collider instanceof BoxCollider) {
            BoxCollider bcollider = (BoxCollider)collider;
            float rwidth = bcollider.getWidth();
            float rheight = bcollider.getHeight();
            int px = (int)((pos.x - followViewer.getPosition().x - rwidth / 2) * cellSize);
            int py = (int)((followViewer.getPosition().y - pos.y - rheight / 2) * cellSize);
            int sx = (int)(cellSize * rwidth);
            int sy = (int)(cellSize * rheight);
            if(containsPoint(px, py)) {
                g.setColor(Color.GREEN);
                g.drawRect(px - 1, py - 1, sx + 1, sy + 1);
                g.drawLine(px + sx / 2, py, px + sx / 2, py + sy);
                g.drawLine(px, py + sy / 2, px + sx, py + sy / 2);
            }
        }
    }

}
