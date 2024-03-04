package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;

import entity.Entity;
import entity.FollowingCamera;
import manager.Sprite;
import model.Cell;
import model.GameScene;
import model.TerrainChunk;
import util.Vector2;

public class GameCanvas extends JPanel {

    private static final int ZOOM_SIZE = 560;

    private static final int CELL_PIXEL = 16;
    
    private int width;

    private int height;
    
    private int cellSize;
    
    private Vector2 viewer;
    
    private FollowingCamera followViewer;
    
    private List<Entity> entities;
    
    private List<TerrainChunk> visibleTerrainChunks;

    private Vector2 currentSelected;
    
    protected GameCanvas(GameScene gameScene){
        this.viewer = gameScene.getViewer();
        this.followViewer = new FollowingCamera(viewer);
        this.entities = gameScene.getEntities();
        this.visibleTerrainChunks = gameScene.getVisibleTerrainChunks();
        
        addMouseListener(
            new MouseListener() {

                @Override public void mouseClicked(MouseEvent e) {}

                @Override public void mousePressed(MouseEvent e) {
                    if(currentSelected != null){
                        gameScene.interact(currentSelected);
                    }
                }

                @Override public void mouseReleased(MouseEvent e) {}

                @Override public void mouseEntered(MouseEvent e) {}

                @Override public void mouseExited(MouseEvent e) {}
                
            }
        );
    }

    private void refreshInteract(){
        Point mouse = getMousePosition();
        Vector2 target = followViewer.getPosition();
        currentSelected = new Vector2(
            // Math.round(target.x) + (int)((mouse.x + cellSize / 2) / cellSize) - getWidth() / (2 * cellSize),
            (int)Math.floor(target.x + (int)(((mouse.x + cellSize / 2) / cellSize)) - getWidth() / (2 * cellSize)),
            (int)Math.floor(target.y - (int)(mouse.y / cellSize) + (getHeight() - cellSize) / (2 * cellSize))
        );
    }

    @Override
    public void paint(Graphics g){
        followViewer.update();
        width = getWidth();
        height = getHeight();
        cellSize = (width / ZOOM_SIZE) * CELL_PIXEL;
        clearScreen(g);
        drawVisibleChunks(g);
        drawEntities(g);

        try {
            refreshInteract();
            drawSelected(g);
        } catch (Exception e) {}
    }

    private void clearScreen(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    private void drawVisibleChunks(Graphics g){
        for (TerrainChunk chunk : visibleTerrainChunks) {
            drawChunk(g, chunk);
        }
    }

    private void drawChunk(Graphics g, TerrainChunk chunk){
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

    private void drawEntities(Graphics g){
        for (Entity entity : entities) {
            placeImage(g, entity);
        }
    }

    private void placeImage(Graphics g, Entity entity){
        placeImage(g, entity.getSprite(), entity.getPosition());
    }
    
    private void placeImage(Graphics g, Sprite sprite, Vector2 position){
        placeImage(g, sprite, position.x, position.y);
    }

    private void placeImage(Graphics g, Sprite sprite, float x, float y){
        g.drawImage(
            sprite.getImage(),
            (int)(width / 2 + (x - followViewer.getPosition().x - sprite.getWidth() + 0.5) * cellSize),
            (int)(height / 2 + (followViewer.getPosition().y - y - sprite.getHeight() + 0.5) * cellSize),
            cellSize * sprite.getWidth(),
            cellSize * sprite.getHeight(),
            null);
    }
    
    private void drawSelected(Graphics g){
        if(currentSelected != null){
            int x = (int)(width / 2 + (currentSelected.x - followViewer.getPosition().x - 0.5) * cellSize);
            int y = (int)(height / 2 + (followViewer.getPosition().y - currentSelected.y - 0.5) * cellSize);
            g.setColor(Color.WHITE);
            g.drawRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, cellSize + 1, cellSize + 1);
        }
    }

}
