package model;

import static model.EndlessTerrain.CHUNK_SIZE;
import static model.EndlessTerrain.MAX_VIEW_DISTANCE;

import java.util.ArrayList;
import java.util.List;

import model.cell.AnimationCell;
import model.cell.Cell;
import model.cell.DeadTree;
import model.cell.Flower;
import model.cell.Grass;
import model.cell.Land;
import model.cell.Water;
import utils.Updatable;
import utils.Vector2;

public class TerrainChunk implements Updatable {

    private Vector2 position;

    private MapData data;

    private List<Cell>[][] cells;

    private List<AnimationCell> updatableCells;

    @SuppressWarnings("unchecked")
    protected TerrainChunk(Vector2 position){
        this.position = position;
        this.data = new MapData(position);
        this.cells = new List[CHUNK_SIZE][CHUNK_SIZE];
        this.updatableCells = new ArrayList<>();
        init();
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public List<Cell>[][] getCells() {
        return cells;
    }

    public boolean isVisible(Vector2 viewerPosition) {
        return position.distance(viewerPosition) <= MAX_VIEW_DISTANCE;
    }

    public void init(){
        double[][] noisemap = data.getNoisemap();

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = new ArrayList<>();
                List<Cell> cellList = cells[x][y];
                cellList.add(new Water());
                
                if(noisemap[x][y] > 0){
                    cellList.add(new Land());

                    double ran = Math.random();
                    
                    if(ran > 0.95){
                        cellList.add(new Flower());
                    }else if(ran > 0.92){
                        cellList.add(new DeadTree());
                    }else if(ran > 0.80){
                        cellList.add(new Grass());
                    }
                }
                
            }
        }
    }

    public void placeCell(int x, int y, Cell cell){
        List<Cell> listCell = cells[x][y];
        Cell lastCell = listCell.get(listCell.size() - 1);
        if(cell.isPlaceable(lastCell)){
            listCell.add(cell);
            if(cell instanceof AnimationCell){
                updatableCells.add((AnimationCell)cell);
            }
        }
    }
    
    @Override
    public void update() {
        this.updatableCells.forEach(Updatable::update);
    }
    
}
