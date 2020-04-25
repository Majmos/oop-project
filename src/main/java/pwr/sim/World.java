package pwr.sim;

public class World {
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width * height];
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.tiles[y * width + x] = new Tile();
            }
        }
    }

    public void draw() {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.tiles[y * width + x].draw();
            }
            System.out.println();
        }
    }

    private Tile[] tiles;
    private int width;
    private int height;
}
