package pwr.sim;

public class Position2D implements Cloneable {
    public Position2D(int x, int y, World world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }

    public Position2D(Position2D other) {
        this.x = other.x;
        this.y = other.y;
        this.world = other.world;
    }

    public void move(int x, int y) {
        int newx = this.x + x;
        int newy = this.y + y;

        if(world.getTile(newx, newy) != null) {
            this.x = newx;
            this.y = newy;
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("{x: %d, y: %d}", x, y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private final World world;
    private int x;
    private int y;
}
