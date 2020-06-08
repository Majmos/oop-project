package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.AiStateSleep;
import pwr.sim.animal.ai.state.IAiState;
import pwr.sim.tile.Tile;
import pwr.sim.tile.WaterTile;

/**
 * Klasa przechowująca statystyki i metody sumulujące jedno zwierze.
 */
public abstract class Animal {
    /**
     * Metoda zmniejsza energie oraz najedzenie zwierzęcia. Sprawdza czy zwierze jest głodne lub zmęczone
     * oraz czy jest dostatecznie najedzone oraz wypoczęte aby kopulować, a także sprawdza czy zwierze jest
     * martwe. Jeśli zwierze jest martwe to zostanie usunięte w następnym kroku i pozostawi po sobie mięso.
     */
    public void update() {
        changeHunger(-1);
        changeEnergy(-2);
        if(hunger > 80 && energy > 80) {
            wantToMate = true;
            isHungry = false;
            isTired = false;
        } else if(hunger < 45 && hunger < energy) {
            isHungry = true;
            isTired = false;
            wantToMate = false;
        } else if(energy < 45) {
            isTired = true;
            isHungry = false;
            wantToMate = false;
        }
        if(health <= 0 || energy <= 0 || hunger <= 0) {
            //Erase animal object
            world.toRemove(this);
            world.getTile(position).changeFlesh(20);
        }
        aiBehaviour.update();
    }

    /**
     * Metoda pobiera lokacje jako parametr i robi jeden krok w jej kierunku. Nie robi kroków diagonalnych.
     * @param dest Określa pozycje na mapie.
     */
    // code object oriented but introduces unnecessary allocations
    public void approach(Position2D dest) {
        Position2D diff = position.delta(dest);
        int x = diff.getX();
        int y = diff.getY();
        int stepX = 0, stepY = 0;
        if(x < 0) {
            stepX = -1;
        } else if(x > 0) {
            stepX = 1;
        }
        if(y < 0) {
            stepY = -1;
        } else if(y > 0) {
            stepY = 1;
        }
        if(Math.abs(x) >= Math.abs(y)) {
            Position2D other = move(stepX, 0);

            if (other == null) {
                move(0, stepY);
            }
        } else {
            Position2D other = move(0, stepY);

            if (other == null) {
                move(stepX, 0);
            }
        }
    }

    /**
     * Metoda pobiera lokacje jako parametr i robi jeden krok w przeciwnym do niej kierunku.
     * Nie robi kroków diagonalnych.
     * @param pos Określa pozycje na mapie.
     */
    public void evade(Position2D pos) {
        Position2D other = position.delta(pos);
        int x = other.getX();
        int y = other.getY();
        int stepX = 0, stepY = 0;
        if(x < 0) {
            stepX = 1;
        } else if(x > 0) {
            stepX = -1;
        }
        if(y < 0) {
            stepY = 1;
        } else if(y > 0) {
            stepY = -1;
        }
        if(Math.abs(x) >= Math.abs(y)) {
            Position2D temp = move(stepX, 0);

            if (temp == null) {
                move(0, stepY);
            }
        } else {
            Position2D temp = move(0, stepY);

            if (temp == null) {
                move(stepX, 0);
            }
        }
    }

    // this method does the tile lookup twice:
    // first we check the tile type by using getTile(x, y) and return if given tile is a water tile
    // then we use position.move(x, y) which uses getTile a second time to check if tile we want is within bounds
    // what should happen is:
    // 1. get the desired tile, return null if outside bounds
    // 2. move to this tile if tile != null AND is not a water tile without the second lookup
    // TODO make move method not check if tile is valid twice

    /**
     * Metoda zmienia pozycje zwierzęcia o zadane wartości. Nowa pozycja będzie widoczna dla innych
     * zwierząt dopiero po wywołaniu metody swap.
     * @param x Określa ruch w prawo i w lewo.
     * @param y Określa ruch w dół i w góre.
     * @return Position2D Zwraca nową pozycje zwierzęcia.
     */
    protected Position2D move(int x, int y) {
        int newx = this.position.getX() + x;
        int newy = this.position.getY() + y;
        Tile tile = this.world.getTile(newx, newy);
        if(tile instanceof WaterTile) {
            return null;
        }
        nextPosition.move(x, y);
        return nextPosition;
    }

    public AiBehaviour getAiBehaviour() {
        return this.aiBehaviour;
    }

    public void setAiBehaviour(AiBehaviour aiBehaviour) {
        this.aiBehaviour = aiBehaviour;
    }

    /**
     * Metoda odpowiada za narysowanie zwierzęcia na mapie.
     */
    public void draw() {
        world.drawAnimal(this);
    }

    public Position2D getPosition() {
        return this.position;
    }

    public void setPosition(Position2D position) throws Exception {
        if(this.world.getTile(position) instanceof WaterTile) {
            throw new Exception("Can't place " + this.getClass().toString() + " on water");
        }
        this.position = new Position2D(position);
        this.nextPosition = new Position2D(position);
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * W zależności od gatunku zwierzęcia zwraca jakim znakiem należy narysować zwierze na mapie.
     * @return char Zwraca znak.
     */
    abstract public char getAnimalChar();

    public int getEnergy() {
        return this.energy;
    }

    /**
     * Zmienia energie o zadaną wartość.
     * @param shift Parametr określający wielkość zmiany.
     */
    public void changeEnergy(int shift) {
        this.energy += shift;
    }

    public int getHunger() {
        return this.hunger;
    }

    /**
     * Zmienia najedzenie o zadaną wartość.
     * @param shift Parametr określający wielkość zmiany.
     */
    public void changeHunger(int shift) {
        this.hunger += shift;
    }

    public int getHealth() { return this.health; }

    /**
     * Zmienia wytrzymałość o zadaną wartość.
     * @param shift Parametr określający wielkość zmiany.
     */
    public void changeHealth(int shift) {
        this.health += shift;
    }

    public int getStrength() {
        return 10;
    }

    /**
     * Metoda aktualizuje pozycje jeżeli została ona zmieniona.
     */
    public void swap() {
        position.setPosition(nextPosition.getX(), nextPosition.getY());
    }

    /**
     * Zwraca String statusu zwierzęcia.
     * @return String Status zwierzęcia.
     */
    public String getStringInfo() {
        return String.format("%s, HP: %d, HUN: %d, ENG: %d, POS: %s, STATE: %s",
            this.getClass().getSimpleName(),
            health,
            hunger,
            energy,
            position.toString(),
            aiBehaviour.debugInfo()
        );
    }

    public boolean wantToMate;
    public boolean isHungry;
    public boolean isTired;
    private AiBehaviour aiBehaviour;
    protected World world;
    private int health = 100;
    protected Position2D position;
    protected Position2D nextPosition;
    private int energy = 80;
    private int hunger = 80;
}
