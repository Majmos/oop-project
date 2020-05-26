package pwr.sim.tile;

import pwr.sim.renderer.Renderer;

public class DesertTile extends Tile {
    public void draw() {
        Renderer.drawColouredText("   ", 220, 220);
    }
}
