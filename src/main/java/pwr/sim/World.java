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
        this.toSpawn = new ArrayList<>();
        this.toRemove = new ArrayList<>();
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
        for(Tile tile: this.tiles) {
            tile.changeFlora(3);
        }
        for(Animal animal: this.animals) {
            animal.swap();
        }
        if(!toSpawn.isEmpty()) {
            addToSpawn();
            this.toSpawn.clear();
        }
        if(!toRemove.isEmpty()) {
            removeToRemove();
            this.toRemove.clear();
        }
    }

    public void populate(int numAnimals, AnimalType animalType) {
        for(int i = 0; i < numAnimals; i++) {
            spawnAnimal(animalType);
        }
    }

    private void spawnAnimal(AnimalType animalType) {
        Random rng = new Random();
        Animal animal = null;

        while(animal == null) {
            Position2D pos = newPosition(rng.nextInt(50), rng.nextInt(50));
            animal = spawnAnimal(animalType, pos);
        }
    }
    private Animal spawnAnimal(AnimalType animalType, Position2D position) {
        Animal animal = this.animalFactory.createAnimal(animalType, position);
        if(animal == null) {
            return null;
        }
        this.animals.add(animal);
        return animal;
    }

    private void spawnAnimal(Animal animal, Position2D position) {
        Animal other = this.animalFactory.createAnimal(animal, position);
        this.animals.add(other);
    }

    public void toSpawn(Animal animal, Position2D position) {
        Animal other = this.animalFactory.createAnimal(animal, position);
        this.toSpawn.add(other);
    }

    private void addToSpawn() {
        this.animals.addAll(this.toSpawn);
    }

    public void toRemove(Animal animal) {
        this.toRemove.add(animal);
    }

    private void removeToRemove() {
        this.animals.removeAll(this.toRemove);
    }

    public void draw() {
        Renderer.clearScreen();
        Renderer.setCursorPosition(1,1);
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                getTile(x, y).draw();
            }
            System.out.println();
        }
        for(Animal animal: this.animals) {
            animal.draw();
        }

        Renderer.drawUi(this);

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
        if(x >= width || x < 0 || y >= height || y < 0) {
            return null;
        }
        return tiles[y * width + x];
    }

    public Tile getTile(Position2D position) {
        int x = position.getX();
        int y = position.getY();
        return getTile(x, y);
    }

    public void breed(Animal animal) {
        Position2D pos = new Position2D(animal.getPosition());
        spawnAnimal(animal, pos);
    }

    public Position2D findNearestPlants(Position2D position) {
        boolean[] visitedMap = new boolean[width * height];

        return bfsPlants(position, visitedMap);
    }

    private Position2D bfsPlants(Position2D pos, boolean[] visitedMap) {
        int x = pos.getX();
        int y = pos.getY();

        visitedMap[y * width + x] = true;

        if(getTile(pos).getFlora() > 20) {
            return pos;
        }

        Position2D[] neighbours = new Position2D[] {
                newPosition(x+1, y),
                newPosition(x, y+1),
                newPosition(x-1, y),
                newPosition(x, y-1)
        };

        for(Position2D neighbour: neighbours) {
            if(neighbour == null) {
                continue;
            }
            int i = neighbour.getY() * width + neighbour.getX();
            if(visitedMap[i]) {
                continue;
            }

            bfsPlants(neighbour, visitedMap);
        }
        return null;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public Position2D newPosition(int x, int y) {
        if(x >= width || x < 0 || y >= height || y < 0) {
            return null;
        }
        return new Position2D(x, y, this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private final AnimalFactory animalFactory;
    private final Tile[] tiles;
    private final List<Animal> animals;
    private final List<Animal> toSpawn;
    private final List<Animal> toRemove;
    private final int width;
    private final int height;
}
