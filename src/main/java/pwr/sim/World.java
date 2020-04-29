package pwr.sim;

import pwr.sim.animal.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width * height];
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.tiles[y * width + x] = new Tile();
            }
        }
        this.animals = new ArrayList<>();
    }

    public void update() {
        for(Animal animal: this.animals) {
            animal.update();
        }
    }

    // TODO: to properly configure how many animals of each species should be generated, we should use factory pattern
    public void populate(int numAnimals) {
        for(int i = 0; i < numAnimals; i++) {
            this.animals.add(new Wolf());
        }
    }

    public void draw() {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.tiles[y * width + x].draw();
            }
            System.out.println();
        }
    }

    private Tile[] tiles;
    private List<Animal> animals;
    private int width;
    private int height;
}
