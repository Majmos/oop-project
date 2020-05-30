package pwr.sim;

import pwr.sim.animal.*;
import pwr.sim.renderer.Renderer;
import pwr.sim.tile.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    public World(int width, int height, Tile[] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.animals = new ArrayList<>();
        this.animalFactory = new AnimalFactory(this);
    }

    public static World loadFromFile(String filename) throws Exception {
        ArrayList<Tile> tileList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();

        int width = line.length();

        int y = 0;
        for(; line != null; y++) {
            if(line.length() != width) {
                throw new Exception("Wrong length in line " + (y + 1) + ". Map not rectangular");
            }

            for(int x = 0; x < width; x++) {
                char tile = line.charAt(x);
                if(tile == 'W') {
                    tileList.add(new WaterTile());
                } else if (tile == 'D') {
                    tileList.add(new DesertTile());
                } else if (tile == 'F') {
                    tileList.add(new ForestTile());
                }
            }
            line = reader.readLine();
        }

        int height = y;
        Tile[] tiles = new Tile[width * height];
        tileList.toArray(tiles);

        return new World(width, height, tiles);
    }

    public void update() {
        for(Animal animal: this.animals) {
            animal.update();
        }
    }

    // TODO: to properly configure how many animals of each species should be generated, we should use factory pattern
    public void populate(int numAnimals) {
        Random pos = new Random();
        for(int i = 0; i < numAnimals; i += 2) {
            Position2D position = new Position2D(pos.nextInt(50), pos.nextInt(50), this);
            this.animals.add(this.animalFactory.createAnimal(AnimalType.WOLF, position));
        }
    }

    public void draw() {
        Renderer.setCursorPosition(1,1);
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                getTile(x, y).draw();
            }
            System.out.println();
        }
        for(Animal animal: this.animals){
            animal.draw();
        }
        Renderer.setCursorPosition(0, height + 1);
    }

    public void drawAnimal(Animal animal) {
        Position2D position = animal.getPosition();

        Renderer.setCursorToCell(position);
        char c = animal.getAnimalChar();

        // handle tile background
        int tileBackground = getTile(position).getColor();

        Renderer.drawColouredChar(c, 231, tileBackground);
    }

    public Tile getTile(int x, int y) {
        if(x >= width || y >= height) {
            return null;
        }
        return tiles[y * width + x];
    }

    public Tile getTile(Position2D position) {
        int x = position.getX();
        int y = position.getY();
        return getTile(x, y);
    }

    private final AnimalFactory animalFactory;
    private final Tile[] tiles;
    private final List<Animal> animals;
    private final int width;
    private final int height;
}
