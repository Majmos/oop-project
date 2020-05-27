package pwr.sim.tile;

import pwr.sim.renderer.Renderer;

public class WaterTile extends Tile {
    public void draw() {
        Renderer.drawColouredText("   ", 20, 20);
    }

    @Override
    public int getColor() {
        return 20;
    }
}
