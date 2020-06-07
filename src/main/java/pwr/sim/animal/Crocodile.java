package pwr.sim.animal;

import pwr.sim.tile.DesertTile;
import pwr.sim.tile.Tile;

public class Crocodile extends Animal {
    @Override
    public boolean canPassTile(Tile tile) {
        return !(tile instanceof DesertTile);
    }

    @Override
    public char getAnimalChar() {
        return 'C';
    }

    @Override
    public int getStrength() {
        return 100;
    }
}
