package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.tile.DesertTile;
import pwr.sim.tile.Tile;

public class Crocodile extends Animal {
    @Override
    public void move(int x, int y) {
        int newx = this.nextPosition.getX() + x;
        int newy = this.nextPosition.getY() + y;
        Tile tile = super.world.getTile(newx, newy);
        if(tile instanceof DesertTile) {
            return;
        }
        nextPosition.move(x, y);
    }

    @Override
    public void setPosition(Position2D position) throws Exception {
        if(this.world.getTile(position) instanceof DesertTile) {
            throw new Exception("Can't place " + this.getClass().toString() + " on desert");
        }
        this.position = new Position2D(position);
        this.nextPosition = new Position2D(position);
    }

    @Override
    public char getAnimalChar() {
        return 'C';
    }
}
