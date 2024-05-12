package model;

import static model.EndlessTerrain.CHUNK_SIZE;

import utils.SimplexNoise;
import utils.Vector2;

public class MapData {

    private static final double NOISE_SIZE = 0.015;

    private static final double BIOME_SIZE = 0.005;

    private Vector2 position;

    private double[][] noisemap;

    private double[][] biomemap;

    public MapData(Vector2 position){
        this.position = position;
        this.noisemap = new double[CHUNK_SIZE][CHUNK_SIZE];
        this.biomemap = new double[CHUNK_SIZE][CHUNK_SIZE];
        init();
    }

    public double[][] getNoisemap() {
        return noisemap;
    }

    public double[][] getBiomemap() {
        return biomemap;
    }

    private double noise(int x, int y, double size){
        return SimplexNoise.noise((x + position.x) * size, (y + position.y) * size);
    }

    private void init(){
        for (int x = 0; x < noisemap.length; x++) {
            for (int y = 0; y < noisemap[x].length; y++) {
                noisemap[x][y] = noise(x, y, NOISE_SIZE);
                biomemap[x][y] = noise(x, y, BIOME_SIZE);
            }
        }
    }

}
