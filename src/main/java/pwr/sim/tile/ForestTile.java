package pwr.sim.tile;

import pwr.sim.renderer.Renderer;

public class ForestTile extends Tile {
    public void draw() {
        Renderer.drawColouredText("   ", 22, 22);
    }
}
