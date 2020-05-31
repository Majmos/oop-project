package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;

public abstract class Animal {
    public void update() {
        aiBehaviour.update();
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

    public void setPosition(Position2D position) {
        this.position = new Position2D(position);
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

    public int getHealth() {
        return this.health;
    }

    public void changeHealth() {

    }

    private AiBehaviour aiBehaviour;
    private World world;
    private int health;
    private Position2D position;
    private int energy = 80;
    private int hunger;
}
