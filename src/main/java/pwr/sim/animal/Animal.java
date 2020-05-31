package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;
import pwr.sim.tile.Tile;
import pwr.sim.tile.WaterTile;

public abstract class Animal {
    public void update() {
        aiBehaviour.update();
    }

    // this method does the tile lookup twice:
    // first we check the tile type by using getTile(x, y) and return if given tile is a water tile
    // then we use position.move(x, y) which uses getTile a second time to check if tile we want is within bounds
    // what should happen is:
    // 1. get the desired tile, return null if outside bounds
    // 2. move to this tile if tile != null AND is not a water tile without the second lookup
    // TODO make move method not check if tile is valid twice
    public void move(int x, int y) {
        int newx = this.position.getX() + x;
        int newy = this.position.getY() + y;
        Tile tile = this.world.getTile(newx, newy);
        if(tile instanceof WaterTile) {
            return;
        }
        position.move(x, y);
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

    public void setPosition(Position2D position) throws Exception {
        if(this.world.getTile(position) instanceof WaterTile) {
            throw new Exception("Can't place " + this.getClass().toString() + " on water");
        }
        this.position = new Position2D(position);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    abstract public char getAnimalChar();


    private AiBehaviour aiBehaviour;
    protected World world;
    private int health;
    public Position2D position;
}
