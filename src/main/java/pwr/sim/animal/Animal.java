package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.AiStateSleep;
import pwr.sim.animal.ai.state.IAiState;
import pwr.sim.tile.Tile;
import pwr.sim.tile.WaterTile;

public abstract class Animal {
    public void update() {
        changeHunger(-1);
        changeEnergy(-2);
        if(hunger > 80 && energy > 80) {
            wantToMate = true;
            isHungry = false;
            isTired = false;
        } else if(hunger < 35 && hunger < energy) {
            isHungry = true;
            isTired = false;
            wantToMate = false;
        } else if(energy < 35) {
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
    public Position2D move(int x, int y) {
        int newx = this.position.getX() + x;
        int newy = this.position.getY() + y;
        Tile tile = this.world.getTile(newx, newy);
        if(!canPassTile(tile)) {
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

    public void draw() {
        world.drawAnimal(this);
    }

    public Position2D getPosition() {
        return this.position;
    }

    public void setPosition(Position2D position) throws Exception {
        if(!canPassTile(world.getTile(position))) {
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

    abstract public char getAnimalChar();

    public int getEnergy() {
        return this.energy;
    }

    public void changeEnergy(int shift) {
        this.energy += shift;
    }

    public int getHunger() {
        return this.hunger;
    }

    public void changeHunger(int shift) {
        this.hunger += shift;
    }

    public int getHealth() { return this.health; }

    public void changeHealth(int shift) {
        this.health += shift;
    }

    public boolean canPassTile(Tile tile) {
        return !(tile instanceof WaterTile);
    }

    public IAiState checkHungerAndEnergy() {
        if(hunger < 35 && hunger < energy) {
            return new AiStateLookForFood(this);
        }
        if(energy < 35) {
            return new AiStateSleep(this);
        }
        return null;
    }

    public int getStrength() {
        return 10;
    }

    public void swap() {
        position.setPosition(nextPosition.getX(), nextPosition.getY());
    }

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
