package pwr.sim;

import pwr.sim.animal.*;
import pwr.sim.renderer.Renderer;
import pwr.sim.tile.*;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

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

    /**
     * Metoda wczytuje mape z pliku tekstowego.
     * @param filename Nazwa pliku.
     * @return World Zwraca świat w którym odbędzie się symulacja.
     * @throws Exception Jeśli jest błąd we wczytywaniu pliku lub mapa nie jest kwadratowa.
     */
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

    /**
     * Aktualizuje zmiany.
     */
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

    /**
     * Tworzy daną ilość zwierząt danego gatunku.
     * @param numAnimals Ilość zwierząt którą metoda ma stworzyć.
     * @param animalType Typ zwierzęcia.
     */
    public void populate(int numAnimals, AnimalType animalType) {
        for(int i = 0; i < numAnimals; i++) {
            spawnAnimal(animalType);
        }
    }

    /**
     * Nadaje pozycje na mapie zwierzęciu danego gatunku.
     * @param animalType Typ zwierzęcia.
     */
    private void spawnAnimal(AnimalType animalType) {
        Random rng = new Random();
        Animal animal = null;

        while(animal == null) {
            Position2D pos = newPosition(rng.nextInt(50), rng.nextInt(50));
            animal = spawnAnimal(animalType, pos);
        }
    }

    /**
     * Tworzy zwierze danego gatunku na danej pozycji.
     * @param animalType Typ zwierzęcia.
     * @param position Pozycja zwierzęcia.
     * @return Animal Zwraca zwierze.
     */
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

    /**
     * Metoda tworzy zwierze danego gatunku na danej pozycji i dodaje zwierze do listy zwierząt do dodania.
     * @param animal Obiekt zwierzęcia danego gatunku.
     * @param position Pozycja zwierzęcia.
     */
    public void toSpawn(Animal animal, Position2D position) {
        Animal other = this.animalFactory.createAnimal(animal, position);
        this.toSpawn.add(other);
    }

    private void addToSpawn() {
        this.animals.addAll(this.toSpawn);
    }

    /**
     * Metoda dadaje zwierze do listy zwierząt do usunięcia.
     * @param animal Zwierze do usunięcia.
     */
    public void toRemove(Animal animal) {
        this.toRemove.add(animal);
    }

    private void removeToRemove() {
        this.animals.removeAll(this.toRemove);
    }

    /**
     * Metoda wyświetla mapę oraz zwierzęta na niej, a także statystyki zwierząt.
     */
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

    /**
     * Metoda wyświetla dane zwierze na mapie z kolorem tła komórki na której się aktualnie znajduje.
     * @param animal Zwierze do wyświetlenia.
     */
    public void drawAnimal(Animal animal) {
        Position2D position = animal.getPosition();

        Renderer.setCursorToCell(position);
        char c = animal.getAnimalChar();

        // handle tile background
        int tileBackground = getTile(position).getColor();

        Renderer.drawColouredChar(c, 231, tileBackground);
    }

    /**
     * Metoda zwraca komórke o danej pozycji.
     * @param x Współżędna x.
     * @param y Współżędna y.
     * @return Tile Komórka o pozycji x i y.
     */
    public Tile getTile(int x, int y) {
        if(x >= width || x < 0 || y >= height || y < 0) {
            return null;
        }
        return tiles[y * width + x];
    }

    /**
     * Metoda zwraca komórke o danej pozycji.
     * @param position Pozycja komórki.
     * @return Tile Komórka o pozycji position.
     */
    public Tile getTile(Position2D position) {
        int x = position.getX();
        int y = position.getY();
        return getTile(x, y);
    }

    /**
     * Metoda znajduje najbliższą komórke lasu na której jest roślinność.
     * @param position Pozycja zwierzęcia, które szuka pożywienia.
     * @return Position2D Zwraca pozycje szukanej komórki.
     */
    public Position2D findNearestPlants(Position2D position) {
        boolean[] visitedMap = new boolean[width * height];
        Queue<Position2D> toVisit = new ArrayDeque<>();
        toVisit.add(position);

        while(toVisit.peek() != null) {
            Position2D current = toVisit.poll();
            int x = current.getX();
            int y = current.getY();

            visitedMap[y * width + x] = true;

            if (getTile(current).getFlora() > 15) {
                return current;
            }

            Stream.of(
                    newPosition(x + 1, y),
                    newPosition(x, y + 1),
                    newPosition(x - 1, y),
                    newPosition(x, y - 1)
            )
                .filter(Objects::nonNull)
                .filter((Position2D p) -> {
                    int i = p.getY() * width + p.getX();
                    return !visitedMap[i];
                })
                .forEach(toVisit::add);

        }
        return null;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    /**
     * Tworzy nową pozycje w świecie.
     * @param x Współżędna x.
     * @param y Współżędna y.
     * @return Position2D Zwraca nową pozycje.
     */
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
