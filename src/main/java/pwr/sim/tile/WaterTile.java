package pwr.sim.tile;

public class WaterTile extends Tile {
    public void draw() {
        System.out.print("\u001B[48;5;20m   \u001B[0m");
    }
}
