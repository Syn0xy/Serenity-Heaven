package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import model.cell.Cell;
import utils.Updatable;
import utils.Vector2;

public class EndlessTerrain {

    public static final int CHUNK_SIZE = 8;

    public static final int CHUNK_HEIGHT = 3;

    public static final int MAX_VIEW_DISTANCE = 64;

    private static final int CHUNKS_VISIBLE_IN_VIEW_DISTANCE = Math.round(MAX_VIEW_DISTANCE / CHUNK_SIZE) + 1;

    private static final int VIEWER_MOVE_DISTANCE = MAX_VIEW_DISTANCE / 4;

    private Map<Vector2, TerrainChunk> terrainChunks;

    private List<TerrainChunk> visibleTerrainChunks;

    private Vector2 viewerPosition;

    private Vector2 oldViewerPosition;

    public EndlessTerrain(Vector2 viewerPosition){
        this.terrainChunks = new HashMap<>();
        this.visibleTerrainChunks = new CopyOnWriteArrayList<>();
        this.viewerPosition = viewerPosition;
        this.oldViewerPosition = viewerPosition.copy();
        updateVisibleChunks();
    }

    public List<TerrainChunk> getVisibleTerrainChunks() {
        return visibleTerrainChunks;
    }

    public TerrainChunk getChunk(Vector2 vec2){
        return terrainChunks.getOrDefault(
            new Vector2(
                (int)Math.floor(vec2.x / CHUNK_SIZE),
                (int)Math.floor(vec2.y / CHUNK_SIZE)
            ),
        null);
    }
    
    public void placeCell(Vector2 vec2, Cell cell) {
        TerrainChunk chunk = getChunk(vec2);
        Vector2 cpos = chunk.getPosition();
        chunk.placeCell(
            (int)(vec2.x - cpos.x),
            (int)(vec2.y - cpos.y), cell);
    }
    
    public void updateChunks(){
        if (oldViewerPosition.distance(viewerPosition) > VIEWER_MOVE_DISTANCE) {
            oldViewerPosition = viewerPosition.copy();
            updateVisibleChunks();
        }

        this.visibleTerrainChunks.forEach(Updatable::update);
    }

    private void updateVisibleChunks(){
        unvisibleAllTerrainChunk();
        
        int currentChunkCoordX = Math.round(viewerPosition.x / CHUNK_SIZE);
        int currentChunkCoordY = Math.round(viewerPosition.y / CHUNK_SIZE);
        
        for (int yOffset = -CHUNKS_VISIBLE_IN_VIEW_DISTANCE; yOffset <= CHUNKS_VISIBLE_IN_VIEW_DISTANCE; yOffset++){
            for (int xOffset = -CHUNKS_VISIBLE_IN_VIEW_DISTANCE; xOffset <= CHUNKS_VISIBLE_IN_VIEW_DISTANCE; xOffset++){
                Vector2 viewedChunkCoord = new Vector2(currentChunkCoordX + xOffset, currentChunkCoordY + yOffset);
                
                if(!terrainChunks.containsKey(viewedChunkCoord)){
                    Vector2 viewedChunkPosition = new Vector2(viewedChunkCoord.x * CHUNK_SIZE, viewedChunkCoord.y * CHUNK_SIZE);
                    terrainChunks.put(viewedChunkCoord, new TerrainChunk(viewedChunkPosition));
                }
                
                TerrainChunk chunk = terrainChunks.get(viewedChunkCoord);
                if(chunk.isVisible(viewerPosition)){
                    visibleTerrainChunks.add(chunk);
                }
            }
        }
    }
    
    private void unvisibleAllTerrainChunk(){
        visibleTerrainChunks.clear();
    }

}
