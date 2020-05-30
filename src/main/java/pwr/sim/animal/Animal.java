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
        return new Position2D(this.position);
    }

    public void setPosition(Position2D position) {
        this.position = new Position2D(position);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    abstract public char getAnimalChar();


    private AiBehaviour aiBehaviour;
    private World world;
    private int health;
    public Position2D position;
}
